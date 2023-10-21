package slack.notifications.message.components;

import slack.notifications.utils.Constants;
import org.json.JSONObject;

import static slack.notifications.message.components.Common.createText;
import static slack.notifications.message.components.Common.createButton;

public final class Footer {

    public static JSONObject build(String url, String buttonText) {
        JSONObject footer = new JSONObject();

        JSONObject text = createText(Constants.TextTypes.PLAIN, buttonText, false);
        JSONObject button = createButton(text, url);

        JSONObject[] elements = new JSONObject[]{button};

        footer.put("type", "actions");
        footer.put("elements", elements);

        return footer;
    }

}
