package slack.notifications.utils;

public class Constants {
    public static class SlackApi {
        public final static String CHAT_ENDPOINT = "https://slack.com/api/chat.postMessage";
    };

    public static class MsgColors {
        public final static String GREEN = "#4CBB17";
        public final static String YELLOW = "#f2c744";
        public final static String RED = "#D70040";
    };

    public static class TextTypes {
        public final static String PLAIN = "plain_text";
        public final static String MD = "mrkdwn";
    }

    public static class ComponentText {
        public final static String FOOTER_BUTTON = "Open Workflow";
    }

    public static class AuthorInfo {
        public final static String AUTHOR_NAME = "s8ckerpunch";
        public final static String SOURCE_REPO_URL = "https://github.com/s8ckerpunch/slack-msg-delivery";
        public final static String SIGNATURE = String.format("Developed by %s", AUTHOR_NAME);
    }

    public static class ResponseErrors {
        public final static String BAD_RESPONSE = "Got error \"%s\" from Slack API. Status code: %s";
    }

    public enum EnvVars {
        CHAT_ID,
        SLACK_TOKEN,
        MSG_TITLE,
        BRANCH,
        BRANCH_URL,
        COMMIT_ID,
        COMMIT_URL,
        COMMIT_MSG,
        CUSTOM_MSG,
        JOB_STATUS,
        JOB_URL,
        USERNAME,
        USER_AVATAR,
        APP_NAME,
        APP_AVATAR,
    }

}
