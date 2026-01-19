package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.TwitchInteractive;
import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;
import org.bukkit.command.CommandSender;

public class CommandChannel implements ICommand {

    @Override
    public String getName() {
        return "channel";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Utils.formatColoredText(
                    References.PREFIX + " &cUsage: /ti channel <nom_channel>"));
            return true;
        }

        String channel = args[0];
        if (channel.startsWith("#")) {
            channel = channel.substring(1);
        }

        TwitchInteractive.INSTANCE.getConfig().set("twitch.channel", channel);
        TwitchInteractive.INSTANCE.saveConfig();

        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aChannel défini sur: &e#" + channel));
        return true;
    }
}
