package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.TwitchInteractive;
import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;
import org.bukkit.command.CommandSender;

public class CommandToken implements ICommand {

    @Override
    public String getName() {
        return "token";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Utils.formatColoredText(
                    References.PREFIX + " &cUsage: /ti token <token>"));
            sender.sendMessage(Utils.formatColoredText(
                    References.PREFIX + " &7Obtenez votre token sur: &bhttps://twitchtokengenerator.com/"));
            return true;
        }

        String token = args[0];
        if (token.startsWith("oauth:")) {
            token = token.substring(6);
        }

        TwitchInteractive.INSTANCE.getConfig().set("twitch.token", token);
        TwitchInteractive.INSTANCE.saveConfig();
        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aToken enregistré !"));

        return true;
    }
}
