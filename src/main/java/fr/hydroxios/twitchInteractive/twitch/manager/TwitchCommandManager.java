package fr.hydroxios.twitchInteractive.twitch.manager;

import fr.hydroxios.twitchInteractive.TwitchInteractive;
import fr.hydroxios.twitchInteractive.twitch.command.TCommandDiamond;
import fr.hydroxios.twitchInteractive.utils.Manager;
import fr.hydroxios.twitchInteractive.twitch.command.ITwitchCommand;

public class TwitchCommandManager extends Manager<ITwitchCommand> {

    public static final String PREFIX = "!";

    public TwitchCommandManager(){
        add(new TCommandDiamond());

        TwitchInteractive.INSTANCE.getLogger().info(this.size() + " twitch command(s) loaded !");
    }

}
