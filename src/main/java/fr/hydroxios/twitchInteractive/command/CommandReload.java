package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;

import org.bukkit.command.CommandSender;

public class CommandReload implements ICommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage(References.PREFIX + Utils.formatColoredText("&aRechargement de la configuration..."));
        // TODO: Recharger la configuration
        sender.sendMessage(References.PREFIX + Utils.formatColoredText("&aConfiguration rechargée avec succès !"));
        return true;
    }
}
