package fr.hydroxios.twitchInteractive.utils;

import com.github.twitch4j.client.websocket.domain.WebsocketConnectionState;
import org.bukkit.ChatColor;

public class Utils {

    public static ChatColor getColorByState(WebsocketConnectionState state) {
        return switch (state) {
            case DISCONNECTING, RECONNECTING, CONNECTING -> ChatColor.AQUA;
            case DISCONNECTED, LOST -> ChatColor.RED;
            case CONNECTED -> ChatColor.GREEN;
        };
    }

    public static String formatColoredText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
