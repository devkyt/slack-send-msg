package slack.notifications.message.components;

import slack.notifications.utils.Constants;
import org.json.JSONObject;

public final class Header {

    public static JSONObject build(String title) {
        JSONObject header = new JSONObject();
        JSONObject text = Common.createText(Constants.TextTypes.PLAIN, title, true);

        header.put("type", "header");
        header.put("text", text);

        return header;
    }

}
