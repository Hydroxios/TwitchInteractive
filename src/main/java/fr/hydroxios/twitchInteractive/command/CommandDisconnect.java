package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;
import org.bukkit.command.CommandSender;

public class CommandDisconnect implements ICommand {

    @Override
    public String getName() {
        return "disconnect";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aDéconnexion de Twitch..."));
        // TODO: Déconnecter le bot de Twitch
        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aDéconnexion réussie !"));
        return true;
    }
}
