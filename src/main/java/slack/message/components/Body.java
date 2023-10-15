package slack.message.components;

import org.json.JSONObject;
import static slack.utils.Constants.TextTypes.*;

public final class Body {

    public static JSONObject build(String payload,
                                   String username,
                                   String userAvatar) {


        JSONObject image = Common.createImage(userAvatar, username);
        JSONObject text = Common.createText(MD, payload, false);
        JSONObject body = new JSONObject();

        body.put("type", "section");
        body.put("block_id", "sectionBlockWithImage");
        body.put("text", text);
        body.put("accessory", image);

        return body;
    }

}
