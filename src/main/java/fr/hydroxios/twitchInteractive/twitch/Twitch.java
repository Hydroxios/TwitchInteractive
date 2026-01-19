package fr.hydroxios.twitchInteractive.twitch;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.FollowEvent;
import fr.hydroxios.twitchInteractive.TwitchInteractive;
import fr.hydroxios.twitchInteractive.twitch.manager.TwitchCommandManager;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.logging.Logger;

public class Twitch {

    private TwitchClient client;
    private Logger logger;

    private TwitchConfig config;

    private static Twitch INSTANCE;

    private TwitchCommandManager commandManager;

    public Twitch() {
        this.logger = TwitchInteractive.INSTANCE.getLogger();
        INSTANCE = this;
    }

    public void init() {
        FileConfiguration configuration = TwitchInteractive.INSTANCE.getConfig();

        if (!TwitchConfig.validate(configuration, logger)) {
            logger.severe("Twitch failed to initialize !");
            return;
        }

        this.config = new TwitchConfig(configuration);

        OAuth2Credential credentials = new OAuth2Credential("twitch",
                "oauth:" + (!config.getToken().startsWith("oauth:") ? config.getToken()
                        : config.getToken().replace("oauth:", "")));
        this.client = TwitchClientBuilder.builder()
                .withChatAccount(credentials)
                .withDefaultAuthToken(credentials)
                .withClientId(config.getClientId())
                .withClientSecret(config.getClientSecret())
                .withEnableHelix(true)
                .withEnableChat(true)
                .build();

        this.commandManager = new TwitchCommandManager();

        // this.client.getClientHelper().enableStreamEventListener(config.getChannel());

        this.client.getClientHelper().enableFollowEventListener(config.getChannel());
        this.client.getEventManager().onEvent(ChannelMessageEvent.class, TwitchEventListeners::onMessage);

        this.client.getEventManager().onEvent(FollowEvent.class, TwitchEventListeners::onFollow);

        logger.info("Joining " + config.getChannel() + "'s channel...");
        this.client.getChat().joinChannel(config.getChannel());
        logger.info("Channel joined !");
    }

    public static Twitch get() {
        if (INSTANCE == null)
            INSTANCE = new Twitch();
        return INSTANCE;
    }

    public void destroy() {
        if (client == null)
            return;
        getClient().getChat().disconnect();
        getClient().getChat().close();
        getClient().close();
        this.client = null;
    }

    public TwitchCommandManager getCommandManager() {
        return commandManager;
    }

    public TwitchClient getClient() {
        return client;
    }
}
