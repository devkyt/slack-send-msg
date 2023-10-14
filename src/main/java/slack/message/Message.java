package slack.msg.delivery.message;

import org.json.JSONArray;
import org.json.JSONObject;
import slack.msg.delivery.message.components.Body;
import slack.msg.delivery.message.components.Footer;
import slack.msg.delivery.message.components.Header;

import static slack.msg.delivery.message.components.Common.createDivider;


public class Message {
    // Ingredients
    private String title;
    private String branch;
    private String commit;
    private String commit_message;
    private String author;

    // Cooked components
    private JSONObject header;
    private JSONObject body;
    private JSONObject footer;

    // Final Dish
    private String content;

    public Message(String title,
                   String branch,
                   String commit,
                   String commit_message,
                   String author) {
        this.title = title;
        this.branch = branch;
        this.commit = commit;
        this.commit_message = commit_message;
        this.author = author;

        cookComponents();
        cookContent();
    }

    private void cookComponents() {
        header = Header.cook(title);
        body = Body.cook(branch, commit, author);
        footer = Footer.cook();
    }

    private void cookContent() {
        JSONObject rawContent = prepareContent();
        content = rawContent.toString();
    }

    private JSONObject prepareContent() {
        JSONObject rawContent = new JSONObject();

        JSONArray blocks = prepareBlocks();
        JSONArray attachments = wrapInAttachment(blocks);

        rawContent.put("attachments", attachments);
        rawContent.put("channel", "C93UJBBJQ");
        rawContent.put("username", "GitHub");

        return rawContent;
    }

    private JSONArray prepareBlocks() {
        JSONArray blocksSection = new JSONArray();

        blocksSection.put(header);
        blocksSection.put(createDivider());
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
