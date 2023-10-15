package slack.utils;

import java.util.HashMap;

public class Utils {
    public static HashMap<String, String> loadEnvVars() {
        HashMap<String, String> envVars = new HashMap<>();

        envVars.put("channel", System.getenv("CHAT_ID"));
        envVars.put("token", System.getenv("SLACK_TOKEN"));
        envVars.put("title", System.getenv("MSG_TITLE"));
        envVars.put("branch", "main");
        envVars.put("commitId", System.getenv("COMMIT_SHA"));
        envVars.put("commitUrl", System.getenv("COMMIT_URL"));
        envVars.put("commitMsg", "Testing new notification system");
        envVars.put("jobStatus", System.getenv("JOB_STATUS"));
        envVars.put("jobUrl", System.getenv("JOB_URL"));
        envVars.put("username", System.getenv("USERNAME"));
        envVars.put("userAvatar", System.getenv("USER_AVATAR"));

        return envVars;
    }
}
