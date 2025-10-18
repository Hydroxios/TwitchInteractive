package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import org.bukkit.command.CommandSender;

public class CommandDisconnect implements ICommand {


    @Override
    public String getName() {
        return "disconnect";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage(References.PREFIX + "§aDéconnexion de Twitch...");
        // Ici vous pouvez ajouter la logique de déconnexion de Twitch
        sender.sendMessage(References.PREFIX + "§aDéconnexion réussie !");
        return true;
    }
}
