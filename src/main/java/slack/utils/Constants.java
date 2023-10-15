package slack.utils;

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
}
