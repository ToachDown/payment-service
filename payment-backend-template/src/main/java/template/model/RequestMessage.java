package template.model;

public class RequestMessage {

    private String type;

    public RequestMessage() {
    }

    public RequestMessage(final String type) {
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
