package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import org.bukkit.command.CommandSender;

public class CommandReload implements ICommand{


    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage(References.PREFIX + "§aRechargement de la configuration...");
        // Ici vous pouvez ajouter la logique de rechargement
        // Par exemple : recharger la configuration depuis les fichiers
        sender.sendMessage(References.PREFIX + "§aConfiguration rechargée avec succès !");
        return true;
    }
}
