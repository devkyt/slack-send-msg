package slack.notifications.message.components;

import slack.notifications.utils.Constants;
import org.json.JSONObject;

public final class Body {

    public static JSONObject build(String payload,
                                   String username,
                                   String userAvatar) {


        JSONObject image = Common.createImage(userAvatar, username);
        JSONObject text = Common.createText(Constants.TextTypes.MD, payload, false);
        JSONObject body = new JSONObject();

        body.put("type", "section");
        body.put("block_id", "sectionBlockWithImage");
        body.put("text", text);
        body.put("accessory", image);

        return body;
    }

}
