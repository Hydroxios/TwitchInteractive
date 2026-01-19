package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.TwitchInteractive;
import fr.hydroxios.twitchInteractive.twitch.Twitch;
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
        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aRechargement de la configuration..."));
        TwitchInteractive.INSTANCE.reloadConfig();
        Twitch.get().destroy();
        Twitch.get().init();
        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aConfiguration rechargée avec succès !"));
        return true;
    }
}
