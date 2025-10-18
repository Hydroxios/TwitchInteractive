package fr.hydroxios.twitchInteractive;

import fr.hydroxios.twitchInteractive.command.ICommand;
import fr.hydroxios.twitchInteractive.listener.PlayerListener;
import fr.hydroxios.twitchInteractive.manager.CommandManager;
import fr.hydroxios.twitchInteractive.twitch.Twitch;
import fr.hydroxios.twitchInteractive.utils.References;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TwitchInteractive extends JavaPlugin {

    public static TwitchInteractive INSTANCE;

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        this.commandManager = new CommandManager();
        Twitch.get();
    }

    @Override
    public void onDisable() {
        Twitch.get().destroy();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Vérifier si la commande est "ti"
        if (!label.equalsIgnoreCase("ti") || args.length == 0) {
            return super.onCommand(sender, command, label, args);
        }

        // Extraire la sous-commande
        String subCommand = args[0].toLowerCase();
        String[] subArgs = new String[args.length - 1];
        if (args.length > 1) {
            System.arraycopy(args, 1, subArgs, 0, args.length - 1);
        }

        // Chercher la sous-commande dans le gestionnaire
        Optional<ICommand> cmd = commandManager.find((c) -> c.getName().equals(subCommand));
        return cmd.map(value -> value.execute(sender, subArgs)).orElseGet(() -> {
            sender.sendMessage(References.PREFIX + "§cSous-commande inconnue. Utilisez §e/ti help §cpour voir les commandes disponibles.");
            return true;
        });
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        // Vérifier si c'est la commande "ti"
        if (!command.getName().equalsIgnoreCase("ti")) {
            return null;
        }

        // Si aucun argument, suggérer toutes les sous-commandes disponibles
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            commandManager.forEach((c) -> {
                completions.add(c.getName());
            });

            // Filtrer les suggestions selon ce que l'utilisateur tape
            String partialArg = args[0].toLowerCase();
            completions.removeIf(completion -> !completion.startsWith(partialArg));

            return completions;
        }

        return null;
    }
}
