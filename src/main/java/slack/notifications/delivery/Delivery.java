package slack.notifications.delivery;

import org.json.JSONObject;
import slack.notifications.exceptions.BadRequestException;
import slack.notifications.exceptions.BadResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static slack.notifications.utils.Constants.ResponseErrors.BAD_RESPONSE;

public class Delivery {
    private final String token;
    private final HttpClient client;
    private HttpResponse<String> response;
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

    public void send() throws BadRequestException {
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            throw new BadRequestException(ex.getMessage(), ex);
        }
    }

    public void handleResponse() throws BadResponseException {
        JSONObject parsedResponse = new JSONObject(response.body());

        if (response.statusCode() != 200 || !parsedResponse.getBoolean("ok")) {
            throw new BadResponseException(String.format(BAD_RESPONSE,
                    parsedResponse.get("error"),
                    response.statusCode()));
        }

    }

}
