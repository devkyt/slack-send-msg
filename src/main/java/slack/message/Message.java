package slack.message;

import org.json.JSONArray;
import org.json.JSONObject;
import slack.delivery.Delivery;
import slack.message.components.Body;
import slack.message.components.Common;
import slack.message.components.Footer;
import slack.message.components.Header;

import java.io.IOException;
import java.util.HashMap;

import static slack.utils.Constants.MsgColors.*;


public class Message {
    private final Delivery deliveryService;

    private final String channel;
    private final String title;
    private final String info;
    private final String color;
    private final String jobUrl;
    private final String username;
    private final String userAvatar;

    private JSONObject[] components;
    private JSONObject content;

    public Message(HashMap<String, String> envVars) {
        this.deliveryService = new Delivery(envVars.get("token"));
        this.channel = envVars.get("channel");
        this.title = setTitle(envVars.get("title"), envVars.get("deployEnv"));
        this.info = setInfo(envVars);
        this.color = setColor(envVars.get("jobStatus"));
        this.jobUrl = envVars.get("jobUrl");
        this.username = envVars.get("username");
        this.userAvatar = envVars.get("userAvatar");

        initComponents();
        setContent();

    }
    private String setTitle(String title, String deployEnv) {
        if (!deployEnv.isEmpty()) {
            return title + " -> " + deployEnv;
        }
        return title;
    }

    private String setInfo(HashMap<String, String> envVars) {
        if (!envVars.get("customMsg").isEmpty()) {
            return envVars.get("customMsg");
        } else {
            return String.format("*Branch*: <%s|%s>\n *Commit*: <%s|%s>\n*Message*: %s\n",
                    envVars.get("branchUrl"),
                    envVars.get("branch"),
                    envVars.get("commitUrl"),
                    envVars.get("commitId"),
                    envVars.get("commitMsg"));
        }
    }

    private String setColor(String jobStatus) {
        switch (jobStatus) {
            case "failure":
                return RED;
            case "cancelled":
                return YELLOW;
            case "success":
            default:
                return GREEN;
        }
    }

    private void initComponents() {
        JSONObject header = Header.build(title);
        JSONObject divider = Common.createDivider();
        JSONObject body = Body.build(info, username, userAvatar);
        JSONObject footer = Footer.build(jobUrl);
        JSONObject context = Common.createContext();
        components = new JSONObject[]{header, divider, body, footer, context};
    }

    private void setContent() {
        JSONObject rawContent = new JSONObject();

        JSONArray blocks = wrapInBlocks();
        JSONArray attachments = wrapInAttachment(blocks);

        rawContent.put("attachments", attachments);
        rawContent.put("channel", channel);
        rawContent.put("icon_url", "https://avatars.githubusercontent.com/u/96535499");

        this.content = rawContent;
    }

    private JSONArray wrapInBlocks() {
        JSONArray blocksSection = new JSONArray();

        for (JSONObject component: components) {
            blocksSection.put(component);
        }

        return blocksSection;
    }

    private JSONArray wrapInAttachment(JSONArray blocks) {
        JSONObject inner = new JSONObject();
        JSONArray attachments = new JSONArray();

        inner.put("color", color);
        inner.put("blocks", blocks);

        attachments.put(inner);

        return attachments;
    }

    public void send() throws IOException, InterruptedException {
        deliveryService.setRequest(content.toString());
        deliveryService.send();
    }

}
