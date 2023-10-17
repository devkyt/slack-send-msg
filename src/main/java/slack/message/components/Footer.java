package slack.message.components;

import org.json.JSONArray;
import org.json.JSONObject;
import static slack.message.components.Common.createText;
import static slack.message.components.Common.createButton;
import static slack.utils.Constants.TextTypes;

public final class Footer {

    public static JSONObject build(String url) {
        JSONObject footer = new JSONObject();
        JSONArray elements = new JSONArray();

        JSONObject text = createText(TextTypes.PLAIN, "Open Job", false);
        JSONObject button = createButton(text, url);

        elements.put(button);

        footer.put("type", "actions");
        footer.put("elements", elements);

        return footer;
    }

}
