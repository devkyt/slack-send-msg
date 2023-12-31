package slack.notifications;

import java.util.HashMap;

import slack.notifications.exceptions.BadRequestException;
import slack.notifications.exceptions.BadResponseException;
import slack.notifications.message.Message;
import slack.notifications.utils.Constants;
import slack.notifications.utils.Utils;

public class MainClass {

    public static void main(String[] args) throws BadRequestException, BadResponseException {
        Constants.EnvVars[] envVars = Constants.EnvVars.values();
        HashMap<String,String> msgData = Utils.loadFromEnv(envVars);
        Message message = new Message(msgData);
        message.send();
    }
}
