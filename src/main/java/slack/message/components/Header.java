package slack.message.components;

import org.json.JSONObject;
import static slack.utils.Constants.TextTypes.*;

public final class Header {

    public static JSONObject build(String title) {
        JSONObject header = new JSONObject();
        JSONObject text = Common.createText(PLAIN, title, true);

        header.put("type", "header");
        header.put("text", text);

        return header;
    }

}
