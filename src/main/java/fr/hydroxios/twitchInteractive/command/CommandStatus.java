package fr.hydroxios.twitchInteractive.command;

import com.github.twitch4j.client.websocket.domain.WebsocketConnectionState;
import fr.hydroxios.twitchInteractive.twitch.Twitch;
import fr.hydroxios.twitchInteractive.utils.Utils;
import org.bukkit.command.CommandSender;

public class CommandStatus implements ICommand {

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("&6===== Twitch Status =====\n\n");
        WebsocketConnectionState chatState = Twitch.get().getClient().getChat().getState();
        sb.append("&bChat: ").append(Utils.getColorByState(chatState)).append(chatState.name()).append("\n");
        sb.append("&bChannel: ").append(Twitch.get().getChannel()).append("\n\n");
        sb.append(" ");

        sender.sendMessage(Utils.formatColoredText(sb.toString()));
        return true;
    }
}
