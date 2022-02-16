package geek.space.tmmuse.Model.AllProfile;

public class GetProfileTiny {
    private Boolean error;
    private GetProfileTinyBody body;

    public GetProfileTiny(Boolean error, GetProfileTinyBody body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public GetProfileTinyBody getBody() {
        return body;
    }

    public void setBody(GetProfileTinyBody body) {
        this.body = body;
    }
}
