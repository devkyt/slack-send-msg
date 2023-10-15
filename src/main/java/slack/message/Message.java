package slack.message;

import org.json.JSONArray;
import org.json.JSONObject;
import slack.message.components.Body;
import slack.message.components.Common;
import slack.message.components.Footer;
import slack.message.components.Header;


public class Message {
    private String channel;
    private String title;
    private String branch;
    private String commitId;
    private String commitUrl;
    private String commitMessage;
    private String jobStatus;
    private String jobUrl;
    private String userAvatar;
    private String color;

    private JSONObject header;
    private JSONObject body;
    private JSONObject footer;

    private String content;

    public Message(String channel,
                   String title,
                   String branch,
                   String commitId,
                   String commitUrl,
                   String commitMessage,
                   String jobStatus,
                   String jobUrl,
                   String userAvatar) {
        this.channel = channel;
        this.title = title;
        this.branch = branch;
        this.commitId = commitId;
        this.commitUrl = commitUrl;
        this.commitMessage = commitMessage;
        this.jobStatus = jobStatus;
        this.jobUrl = jobUrl;
        this.userAvatar = userAvatar;

        buildComponents();
        buildContent();
    }

    private void buildComponents() {
        header = Header.build(title);
        body = Body.build(branch, commitUrl, commitId, commitMessage, userAvatar);
        footer = Footer.build(jobUrl);
    }

    private void buildContent() {
        JSONObject rawContent = prepareContent();
        content = rawContent.toString();
    }

    private JSONObject prepareContent() {
        JSONObject rawContent = new JSONObject();

        JSONArray blocks = createBlocks();
        JSONArray attachments = wrapInAttachment(blocks);

        rawContent.put("attachments", attachments);
        rawContent.put("channel", channel);

        return rawContent;
    }

    private JSONArray createBlocks() {
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

        inner.put("color", "#f2c744");
        inner.put("blocks", blocks);

        attachments.put(inner);

        return attachments;
    }

    public String getContent() {
        return content;
    }

}
