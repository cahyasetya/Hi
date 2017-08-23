package id.my.developer.hi.model;

/**
 * Created by light on 16/08/2017.
 */

public class Chat {
    private String name;
    private String message;

    public Chat(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
