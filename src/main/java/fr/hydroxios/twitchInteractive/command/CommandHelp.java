package fr.hydroxios.twitchInteractive.command;

import fr.hydroxios.twitchInteractive.utils.References;
import org.bukkit.command.CommandSender;

public class CommandHelp implements ICommand{

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§6=== Aide " + References.PREFIX + " §6===");
        sender.sendMessage("");
        sender.sendMessage("§eCommandes de configuration:");
        sender.sendMessage("§7/ti connect <token> §f- Connecter le bot à Twitch");
        sender.sendMessage("§7/ti disconnect §f- Déconnecter le bot de Twitch");
        sender.sendMessage("§7/ti channel <nom_channel> §f- Définir le channel Twitch à rejoindre");
        sender.sendMessage("");
        sender.sendMessage("§eCommandes générales:");
        sender.sendMessage("§7/ti help §f- Afficher cette aide");
        sender.sendMessage("§7/ti reload §f- Recharger la configuration");
        sender.sendMessage("§7/ti status §f- Afficher le statut de la connexion");
        sender.sendMessage("");
        sender.sendMessage("§eConfiguration initiale:");
        sender.sendMessage("§71. Obtenez un token OAuth sur §bhttps://twitchapps.com/tmi/");
        sender.sendMessage("§72. Utilisez §b/ti connect <votre_token>");
        sender.sendMessage("§73. Définissez votre channel avec §b/ti channel <votre_channel>");
        sender.sendMessage("");
        sender.sendMessage("§6Pour plus d'informations: §ehttps://github.com/hydroxios/TwitchInteractive");

        return true;
    }
}
