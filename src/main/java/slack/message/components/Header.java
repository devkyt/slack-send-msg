package slack.message.components;

import org.json.JSONObject;

public final class Header {

    public static JSONObject build(String title) {
        JSONObject header = new JSONObject();
        JSONObject text = Common.createText("plain_text", title, true);

        header.put("type", "header");
        header.put("text", text);

        return header;
    }

}
