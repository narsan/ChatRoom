package Requests;

import java.io.Serializable;

public class Message implements Serializable {
    String from;
    String to;
    String message;

    public Message(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
