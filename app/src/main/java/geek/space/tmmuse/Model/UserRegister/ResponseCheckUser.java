package geek.space.tmmuse.Model.UserRegister;

import java.util.ArrayList;

public class ResponseCheckUser {
    private Boolean error;
    private ResponseUserBody body;

    public ResponseCheckUser(Boolean error, ResponseUserBody body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseUserBody getBody() {
        return body;
    }

    public void setBody(ResponseUserBody body) {
        this.body = body;
    }
}
