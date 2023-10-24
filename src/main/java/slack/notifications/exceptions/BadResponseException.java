package slack.notifications.exceptions;

public class BadResponseException extends Exception {
    public BadResponseException(String message) {
        super(message);
    }

    public BadResponseException(String message, Throwable cause) {
        super(message, cause);
    }

}
