package slack.message.components;

import org.json.JSONObject;
import static slack.message.components.Common.createText;
import static slack.message.components.Common.createButton;
import static slack.utils.Constants.TextTypes;

public final class Footer {

    public static JSONObject build(String url, String buttonText) {
        JSONObject footer = new JSONObject();

        JSONObject text = createText(TextTypes.PLAIN, buttonText, false);
        JSONObject button = createButton(text, url);

        JSONObject[] elements = new JSONObject[]{button};

        footer.put("type", "actions");
        footer.put("elements", elements);

        return footer;
    }

}
