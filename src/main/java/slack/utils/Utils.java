package slack.utils;

import java.util.HashMap;
import slack.utils.Constants.EnvVars;

public class Utils {
    public static HashMap<String, String> loadFromEnv(EnvVars[] envVars) {
        HashMap<String, String> msgData = new HashMap<>();

        for (EnvVars var: envVars) {
            String name = var.name();
            msgData.put(toCamelCase(name), System.getenv(name));
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
