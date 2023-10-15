package slack;

import slack.message.Message;
import static slack.utils.Utils.loadEnvVars;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class MainClass {

    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String,String> envVars = loadEnvVars();
        Message message = new Message(envVars);
        message.send();
    }
}
