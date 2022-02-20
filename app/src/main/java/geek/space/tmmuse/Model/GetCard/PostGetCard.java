package geek.space.tmmuse.Model.GetCard;

public class PostGetCard {
    private Boolean error;
    private String body;

    public PostGetCard(Boolean error, String body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}