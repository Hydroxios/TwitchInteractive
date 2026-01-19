package fr.hydroxios.twitchInteractive.twitch;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.logging.Logger;

public class TwitchConfig {

    private final String clientId;
    private final String clientSecret;
    private final String token;
    private final String channel;

    public TwitchConfig(FileConfiguration configuration) {
        this.clientId = configuration.getString("twitch.client_id");
        this.clientSecret = configuration.getString("twitch.client_secret");
        this.token = configuration.getString("twitch.token");
        this.channel = configuration.getString("twitch.channel");
    }

    public static boolean validate(FileConfiguration configuration, Logger logger) {
        boolean success = true;

        if (isMissing(configuration, "twitch.client_id")) {
            logger.severe("Twitch client id is missing !");
            success = false;
        }
        if (isMissing(configuration, "twitch.client_secret")) {
            logger.severe("Twitch client secret is missing !");
            success = false;
        }
        if (isMissing(configuration, "twitch.token")) {
            logger.severe("Twitch token is missing !");
            success = false;
        }
        if (isMissing(configuration, "twitch.channel")) {
            logger.severe("No channel defined !");
            success = false;
        }

        return success;
    }

    private static boolean isMissing(FileConfiguration config, String path) {
        String value = config.getString(path);
        return value == null || value.isEmpty() || value.equalsIgnoreCase("YOUR_CLIENT_ID")
                || value.equalsIgnoreCase("YOUR_CLIENT_SECRET") || value.equalsIgnoreCase("YOUR_TOKEN");
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getToken() {
        return token;
    }

    public String getChannel() {
        return channel;
    }
}
