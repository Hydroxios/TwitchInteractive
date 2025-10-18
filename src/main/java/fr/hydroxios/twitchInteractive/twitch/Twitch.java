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

    String clientId, clientSecret;
    String token;
    String channel;

    private static Twitch INSTANCE;

    private TwitchCommandManager commandManager;

    public Twitch() {
        this.logger = TwitchInteractive.INSTANCE.getLogger();
        INSTANCE = this;
    }

    public void init() {
        boolean success = true;
        FileConfiguration configuration = TwitchInteractive.INSTANCE.getConfig();
        if (!configuration.contains("twitch.client_id")) {
            logger.severe("Twitch client id is missing !");
            success = false;
        }
        if (!configuration.contains("twitch.client_secret")) {
            logger.severe("Twitch client secret is missing !");
            success = false;
        }
        if (!configuration.contains("twitch.token")) {
            logger.severe("Twitch token is missing !");
            success = false;
        }
        if (!configuration.contains("twitch.channel")) {
            logger.severe("No channel defined !");
            success = false;
        }
        if (!success) {
            logger.severe("Twitch failed to initialize !");
            return;
        }
        this.clientId = configuration.getString("twitch.client_id");
        this.clientSecret = configuration.getString("twitch.client_secret");
        this.token = configuration.getString("twitch.token");
        this.channel = configuration.getString("twitch.channel");

        OAuth2Credential credentials = new OAuth2Credential("twitch", "oauth:" + (!token.startsWith("oauth:") ? token : token.replace("oauth:", "")));
        this.client = TwitchClientBuilder.builder()
                .withChatAccount(credentials)
                .withDefaultAuthToken(credentials)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withEnableHelix(true)
                .withEnableChat(true)
                .build();

        this.commandManager = new TwitchCommandManager();

        // this.client.getClientHelper().enableStreamEventListener(this.channel);

        this.client.getClientHelper().enableFollowEventListener(this.channel);
        this.client.getEventManager().onEvent(ChannelMessageEvent.class, TwitchEventListeners::onMessage);

        this.client.getEventManager().onEvent(FollowEvent.class, TwitchEventListeners::onFollow);

        logger.info("Joining " + channel + "'s channel...");
        this.client.getChat().joinChannel(channel);
        logger.info("Channel joined !");
    }

    public static Twitch get() {
        if (INSTANCE == null)
            INSTANCE = new Twitch();
        return INSTANCE;
    }

    public void destroy() {
        getClient().getChat().disconnect();
        getClient().getChat().close();
        getClient().close();
    }

    public TwitchCommandManager getCommandManager() {
        return commandManager;
    }

    public TwitchClient getClient() {
        return client;
    }
}
