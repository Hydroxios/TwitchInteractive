package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import org.bukkit.command.CommandSender;

public class CommandChannel implements ICommand {

    @Override
    public String getName() {
        return "channel";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(References.PREFIX + "§cUsage: /ti channel <nom_channel>");
            return true;
        }

        String channel = args[0];
        if (channel.startsWith("#")) {
            channel = channel.substring(1); // Enlever le # si présent
        }

        sender.sendMessage(References.PREFIX + "§aChannel défini sur: §e#" + channel);
        // Ici vous pouvez ajouter la logique pour rejoindre le channel
        return true;
    }
}
