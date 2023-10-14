package slack.msg.delivery;

import slack.msg.delivery.message.Message;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


//xoxb-305242419056-6031903258789-s7keiEPJefSZuj5Wf9Mw9MXt
//C93UJBBJQ

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        Message msg = new Message("title",
                "branch",
                "124sd",
                "testBot",
                "author");

        System.out.println(System.getenv("PATH"));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://slack.com/api/chat.postMessage"))
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer xoxb-305242419056-6031903258789-s7keiEPJefSZuj5Wf9Mw9MXt")
                .POST(HttpRequest.BodyPublishers.ofString(msg.getContent()))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

    }
}
