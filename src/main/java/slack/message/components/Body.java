package slack.msg.delivery.message.components;

import org.json.JSONObject;

import static slack.msg.delivery.message.components.Common.createText;
import static slack.msg.delivery.message.components.Common.createImage;

public final class Body {

    public static JSONObject cook(String branch, String commit, String author) {
        String msg = "*Branch*: master\n *Commit*: 323dsd\n*Author*: testBot\n";

        JSONObject image = createImage();
        JSONObject text = createText("mrkdwn", msg, false);
        JSONObject body = new JSONObject();

        body.put("type", "section");
        body.put("block_id", "sectionBlockWithImage");
        body.put("text", text);
        body.put("accessory", image);

        return body;
    }

}
