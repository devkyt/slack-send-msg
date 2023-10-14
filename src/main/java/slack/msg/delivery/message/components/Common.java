package slack.msg.delivery.message.components;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public static JSONObject createImage() {
        JSONObject image = new JSONObject();

        image.put("type", "image");
        image.put("image_url",  "https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg");
        image.put("alt_text", "author");

        return image;
    }

    public static JSONObject createButton(JSONObject text) {
        JSONObject button = new JSONObject();

        button.put("type", "button");
        button.put("text", text);
        button.put("value", "click_me_123");
        button.put("action_id", "actionId-0");

        return button;
    }

    public static JSONObject createDivider() {
        JSONObject divider = new JSONObject();
        divider.put("type", "divider");
        return divider;
    }

}
