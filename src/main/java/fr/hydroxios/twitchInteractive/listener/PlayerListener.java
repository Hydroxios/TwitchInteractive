package fr.hydroxios.twitchInteractive.listener;

import com.github.twitch4j.client.websocket.domain.WebsocketConnectionState;
import fr.hydroxios.twitchInteractive.twitch.Twitch;
import fr.hydroxios.twitchInteractive.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.isOp()) return;

        StringBuilder sb = new StringBuilder();
        sb.append("&6===== Twitch Status =====\n\n");
        WebsocketConnectionState chatState = Twitch.get().getClient().getChat().getState();
        sb.append("&bChat: ").append(Utils.getColorByState(chatState)).append(chatState.name()).append("\n\n");
        sb.append("&6======================\n");

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', sb.toString()));
    }



}

