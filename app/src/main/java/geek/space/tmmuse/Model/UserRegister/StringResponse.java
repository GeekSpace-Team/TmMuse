package geek.space.tmmuse.Model.UserRegister;

public class StringResponse {
    private Boolean error;
    private String body;

    public StringResponse(Boolean error, String body) {
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
