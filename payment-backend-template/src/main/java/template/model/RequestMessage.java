package template.model;

public class RequestMessage {

    private String type;

    public RequestMessage(final String type) {
        this.type = type;
    }

    public String getType () {
        return type;
    }
}
