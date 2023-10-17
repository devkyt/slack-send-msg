package slack.message;

import org.json.JSONObject;
import slack.delivery.Delivery;
import slack.message.components.Body;
import slack.message.components.Common;
import slack.message.components.Footer;
import slack.message.components.Header;

import java.io.IOException;
import java.util.HashMap;

import static slack.utils.Constants.MsgColors.*;
import static slack.utils.Constants.SlackApi.CHAT_ENDPOINT;


public class Message {
    private final Delivery deliveryService;

    private final String channel;
    private final String title;
    private final String info;
    private final String color;
    private final String jobUrl;
    private final String username;
    private final String userAvatar;
    private final String appName;
    private final String appAvatar;

    private JSONObject content;

    public Message(HashMap<String, String> msgData) {
        this.deliveryService = new Delivery(msgData.get("slackToken"));

        this.channel = msgData.get("chatId");
        this.title = msgData.get("msgTitle");
        this.jobUrl = msgData.get("jobUrl");
        this.username = msgData.get("username");
        this.userAvatar = msgData.get("userAvatar");
        this.appName = msgData.get("appName");
        this.appAvatar = msgData.get("appAvatar");

        this.info = setInfo(msgData);
        this.color = setColor(msgData.get("jobStatus"));

        createContent();
    }

    private String setInfo(HashMap<String, String> msgData) {
        if (!msgData.get("customMsg").isEmpty()) {
            return msgData.get("customMsg");
        } else {
            return String.format("*Branch*: <%s|%s>\n*Commit*: <%s|%s>\n*Message*: %s\n",
                    msgData.get("branchUrl"),
                    msgData.get("branch"),
                    msgData.get("commitUrl"),
                    msgData.get("commitId"),
                    msgData.get("commitMsg"));
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

    private void createContent() {
        JSONObject msgContent = new JSONObject();

        JSONObject[] components = initComponents();
        JSONObject[] attachments = wrapIntoAttachment(components);

        msgContent.put("attachments", attachments);
        msgContent.put("channel", channel);
        msgContent.put("username", appName);
        msgContent.put("icon_url", appAvatar);

        content = msgContent;
    }

    private JSONObject[] initComponents() {
        JSONObject header = Header.build(title);
        JSONObject divider = Common.createDivider();
        JSONObject body = Body.build(info, username, userAvatar);
        JSONObject footer = Footer.build(jobUrl);
        JSONObject context = Common.createContext();

        return new JSONObject[]{header, divider, body, footer, context};
    }

//    private JSONArray wrapInBlocks() {
//        JSONArray blocksSection = new JSONArray();
//
//        for (JSONObject component: components) {
//            blocksSection.put(component);
//        }
//
//        return blocksSection;
//    }

    private JSONObject[] wrapIntoAttachment(JSONObject[] components) {
        JSONObject attachments = new JSONObject();

        attachments.put("color", color);
        attachments.put("blocks", components);

        return new JSONObject[]{attachments};
    }

    public void send() throws IOException, InterruptedException {
        deliveryService.setRequest(content.toString(), CHAT_ENDPOINT);
        deliveryService.send();
    }

}
