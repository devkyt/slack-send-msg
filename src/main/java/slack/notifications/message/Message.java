package slack.notifications.message;

import org.json.JSONObject;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import slack.notifications.delivery.Delivery;
import slack.notifications.exceptions.BadRequestException;
import slack.notifications.exceptions.BadResponseException;
import slack.notifications.message.components.Body;
import slack.notifications.message.components.Common;
import slack.notifications.message.components.Footer;
import slack.notifications.message.components.Header;

import static slack.notifications.utils.Constants.MsgColors.*;
import static slack.notifications.utils.Constants.SlackApi.CHAT_ENDPOINT;
import static slack.notifications.utils.Constants.AuthorInfo.SIGNATURE;
import static slack.notifications.utils.Constants.ComponentText.FOOTER_BUTTON;


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
        msgContent.put("text", "preview Test");

        content = msgContent;
    }

    private JSONObject[] initComponents() {
        JSONObject header = Header.build(title);
        JSONObject divider = Common.createDivider();
        JSONObject body = Body.build(info, username, userAvatar);
        JSONObject footer = Footer.build(jobUrl, FOOTER_BUTTON);
        JSONObject context = Common.createContext(SIGNATURE);

        return new JSONObject[]{header, divider, body, footer, context};
    }

    private JSONObject[] wrapIntoAttachment(JSONObject[] components) {
        JSONObject attachments = new JSONObject();

        attachments.put("color", color);
        attachments.put("blocks", components);

        return new JSONObject[]{attachments};
    }

    public void send() throws BadRequestException, BadResponseException {
        deliveryService.setRequest(content.toString(), CHAT_ENDPOINT);
        deliveryService.send();
        deliveryService.handleResponse();
    }

}
