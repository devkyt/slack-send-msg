package slack.notifications.delivery;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static slack.notifications.utils.Constants.ResponseErrors.BAD_RESPONSE;

public class Delivery {
    private final String token;
    private final HttpClient client;
    private HttpRequest request;

    public Delivery(String token) {
        this.token = token;
        this.client = HttpClient.newHttpClient();
    }

    public void setRequest(String message, String url) {
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
    }

    public void send() throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        handleErrors(response);
    }

    private void handleErrors(HttpResponse<String> response) {
        JSONObject parsedResponse = new JSONObject(response.body());

        if (response.statusCode() != 200 || !parsedResponse.getBoolean("ok")) {
            throw new IllegalStateException(String.format(BAD_RESPONSE,
                    response.statusCode(),
                    parsedResponse.get("error")));
        }

    }

}
