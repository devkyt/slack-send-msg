package slack.message.components;

import org.json.JSONObject;

public final class Body {

    public static JSONObject build(String branch,
                                   String commitUrl,
                                   String commitId,
                                   String commitMessage,
                                   String username,
                                   String userAvatar) {
        String msg = String.format("*Branch*: %s\n *Commit*: <%s|%s>\n*Message*: %s\n",
                branch, commitUrl, commitId, commitMessage);

        JSONObject image = Common.createImage(userAvatar, username);
        JSONObject text = Common.createText("mrkdwn", msg, false);
        JSONObject body = new JSONObject();

        body.put("type", "section");
        body.put("block_id", "sectionBlockWithImage");
        body.put("text", text);
        body.put("accessory", image);

        return body;
    }

}
