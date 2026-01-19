package fr.hydroxios.twitchInteractive.twitch;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.FollowEvent;
import fr.hydroxios.twitchInteractive.twitch.command.ITwitchCommand;
import fr.hydroxios.twitchInteractive.twitch.manager.TwitchCommandManager;
import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.Optional;

public class TwitchEventListeners {

    public static void onMessage(ChannelMessageEvent e) {
        String message = e.getMessage();
        if (message.startsWith(TwitchCommandManager.PREFIX)) {
            String[] parts = message.split(" ");
            String label = parts[0].substring(1);
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            Optional<ITwitchCommand> twitchCommand = Twitch.get().getCommandManager()
                    .find((c) -> c.getName().equalsIgnoreCase(label));
            twitchCommand.ifPresent((c) -> c.execute(e.getUser(), args));
        }
    }

    public static void onFollow(FollowEvent e) {
        Bukkit.broadcastMessage(
                References.PREFIX + Utils.formatColoredText("&b" + e.getUser().getName() + " &fis now following !"));

    }

}
