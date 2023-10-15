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
    private final String branch;
    private final String commitId;
    private final String commitUrl;
    private final String commitMessage;
    private final String jobStatus;
    private final String jobUrl;
    private final String username;
    private final String userAvatar;
    private String color;

    private JSONObject header;
    private JSONObject body;
    private JSONObject footer;

    private JSONObject content;

    public Message(HashMap<String, String> envVars) {
        this.deliveryService = new Delivery(envVars.get("token"));

        this.channel = envVars.get("channel");
        this.title = envVars.get("title");
        this.branch = envVars.get("branch");
        this.commitId = envVars.get("commitId");
        this.commitUrl = envVars.get("commitUrl");
        this.commitMessage = envVars.get("commitMsg");
        this.jobStatus = envVars.get("jobStatus");
        this.jobUrl = envVars.get("jobUrl");
        this.username = envVars.get("username");
        this.userAvatar = envVars.get("userAvatar");

        setColor();
        buildComponents();
        createContent();
    }

    private void setColor() {
        switch (jobStatus) {
            case "failure":
                color = RED;
                break;
            case "cancelled":
                color = YELLOW;
                break;
            case "success":
            case "default":
                color = GREEN;
                break;
        }
    }
    private void buildComponents() {
        header = Header.build(title);
        body = Body.build(branch, commitUrl, commitId, commitMessage, username, userAvatar);
        footer = Footer.build(jobUrl);
    }

    private void createContent() {
        JSONObject rawContent = new JSONObject();

        JSONArray blocks = wrapInBlocks();
        JSONArray attachments = wrapInAttachment(blocks);

        rawContent.put("attachments", attachments);
        rawContent.put("channel", channel);

        this.content = rawContent;
    }

    private JSONArray wrapInBlocks() {
        JSONArray blocksSection = new JSONArray();

        blocksSection.put(header);
        blocksSection.put(Common.createDivider());
        blocksSection.put(body);
        blocksSection.put(footer);

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
