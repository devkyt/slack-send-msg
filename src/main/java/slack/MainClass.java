package slack;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import slack.message.Message;
import static slack.utils.Utils.loadFromEnv;
import slack.utils.Constants.EnvVars;

public class MainClass {

    public static void main(String[] args) throws IOException, InterruptedException {
        EnvVars[] envVars = EnvVars.values();
        HashMap<String,String> msgData = loadFromEnv(envVars);
        Message message = new Message(msgData);
        message.send();
    }
}
