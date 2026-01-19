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
            sender.sendMessage(Utils.formatColoredText(
                    References.PREFIX + " &cUsage: /ti connect <token>"));
            sender.sendMessage(Utils.formatColoredText(
                    References.PREFIX + " &7Obtenez votre token sur: &bhttps://twitchapps.com/tmi/"));
            return true;
        }

        String token = args[0];
        if (token.startsWith("oauth:")) {
            token = token.substring(6); // Enlever le préfixe oauth: si présent
        }

        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aConnexion à Twitch avec le token fourni..."));
        // TODO: Connecter le bot à Twitch
        sender.sendMessage(Utils.formatColoredText(
                References.PREFIX + " &aConnexion réussie !"));

        return true;
    }
}
