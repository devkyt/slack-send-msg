package slack.notifications.utils;

import java.util.HashMap;

import slack.notifications.utils.Constants.EnvVars;

public class Utils {
    public static HashMap<String, String> loadFromEnv(EnvVars[] envVars)  {
        HashMap<String, String> msgData = new HashMap<>();

        for (EnvVars var: envVars) {
            String key = var.name();
            String value = System.getenv(key);
            msgData.put(toCamelCase(key), value);
        }

        return msgData;
    }

    public static String toCamelCase(String snakePhrase) {
        String[] phraseParts = snakePhrase.toLowerCase().split("_");
        String camelPhrase = phraseParts[0];

        for (int i = 1; i < phraseParts.length; i++) {
            camelPhrase += phraseParts[i].substring(0, 1).toUpperCase() + phraseParts[i].substring(1);
        }

        return camelPhrase;
    }

}
