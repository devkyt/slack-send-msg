package slack.message.components;

import org.json.JSONObject;
import static slack.utils.Constants.TextTypes;

public final class Common {

    public static JSONObject createText(String type, String text, Boolean hasEmoji) {
        JSONObject textSection = new JSONObject();

        textSection.put("type", type);
        textSection.put("text", text);

        if (hasEmoji) {
            textSection.put("emoji", hasEmoji);
        }

        return textSection;
    }

    public static JSONObject createImage(String url, String alt_text) {
        JSONObject image = new JSONObject();

        image.put("type", "image");
        image.put("image_url", url);
        image.put("alt_text", alt_text);

        return image;
    }

    public static JSONObject createButton(JSONObject text, String url) {
        JSONObject button = new JSONObject();

        button.put("action_id", "actionId-0");
        button.put("type", "button");
        button.put("text", text);
        button.put("url", url);
        button.put("value", "click_me_123");

        return button;
    }

    public static JSONObject createDivider() {
        JSONObject divider = new JSONObject();
        divider.put("type", "divider");
        return divider;
    }

    public static JSONObject createContext(String signature) {
        JSONObject context = new JSONObject();

        JSONObject text = createText(TextTypes.MD, signature, false);

        JSONObject[] elements = new JSONObject[]{text};

        context.put("type", "context");
        context.put("elements", elements);
        return context;
    }


}
