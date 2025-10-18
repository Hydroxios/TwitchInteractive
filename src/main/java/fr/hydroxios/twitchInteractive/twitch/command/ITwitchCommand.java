package fr.hydroxios.twitchInteractive.twitch.command;

import com.github.twitch4j.common.events.domain.EventUser;

public interface ITwitchCommand {
    String getName();
    void execute(EventUser sender, String[] args);
}
