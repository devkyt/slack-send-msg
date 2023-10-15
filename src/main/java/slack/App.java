package slack;

import slack.message.Message;
import static slack.utils.Utils.loadEnvVars;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        HashMap<String,String> envVars = loadEnvVars();

        Message msg = new Message(envVars.get("channel"),
                envVars.get("title"),
                envVars.get("branch"),
                envVars.get("commitId"),
                envVars.get("commitUrl"),
                envVars.get("commitMsg"),
                envVars.get("jobStatus"),
                envVars.get("jobUrl"),
                envVars.get("userAvatar"));


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://slack.com/api/chat.postMessage"))
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + envVars.get("token"))
                .POST(HttpRequest.BodyPublishers.ofString(msg.getContent()))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

    }
}
