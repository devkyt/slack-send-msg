package slack;

import java.io.IOException;
import java.util.HashMap;

import slack.message.Message;
import slack.utils.Constants.EnvVars;
import static slack.utils.Utils.loadFromEnv;

public class MainClass {

    public static void main(String[] args) throws IOException, InterruptedException {
        EnvVars[] envVars = EnvVars.values();
        HashMap<String,String> msgData = loadFromEnv(envVars);
        Message message = new Message(msgData);
        message.send();
    }
}
