package slack.msg.delivery.message.components;

import org.json.JSONArray;
import org.json.JSONObject;
import static slack.msg.delivery.message.components.Common.createText;
import static slack.msg.delivery.message.components.Common.createButton;

public final class Footer {

    public static JSONObject cook() {
        JSONObject footer = new JSONObject();
        JSONArray elements = new JSONArray();

        JSONObject text = createText("plain_text", "Open Workflow", false);
        JSONObject button = createButton(text);

        elements.put(button);

        footer.put("type", "actions");
        footer.put("elements", elements);

        return footer;
    }

}
