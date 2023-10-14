package slack.msg.delivery.message.components;

import org.json.JSONObject;

import static slack.msg.delivery.message.components.Common.createText;

public final class Header {

    public static JSONObject cook(String title) {
        JSONObject header = new JSONObject();
        JSONObject text = createText("plain_text", title, true);

        header.put("type", "header");
        header.put("text", text);

        return header;
    }

}
