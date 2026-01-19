package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;
import org.bukkit.command.CommandSender;

public class CommandConnect implements ICommand {

    @Override
    public String getName() {
        return "connect";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(References.PREFIX + Utils.formatColoredText("&cUsage: /ti connect <token>"));
            sender.sendMessage(References.PREFIX
                    + Utils.formatColoredText("&7Obtenez votre token sur: &bhttps://twitchapps.com/tmi/"));
            return true;
        }

        String token = args[0];
        if (token.startsWith("oauth:")) {
            token = token.substring(6); // Enlever le préfixe oauth: si présent
        }

        sender.sendMessage(References.PREFIX + Utils.formatColoredText("&aConnexion à Twitch avec le token fourni..."));
        // TODO: Connecter le bot à Twitch
        sender.sendMessage(References.PREFIX + Utils.formatColoredText("&aConnexion réussie !"));

        return true;
    }
}
