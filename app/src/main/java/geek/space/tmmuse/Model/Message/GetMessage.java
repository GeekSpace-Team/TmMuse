package geek.space.tmmuse.Model.Message;

import java.util.ArrayList;

public class GetMessage {
    private Boolean error;
    private GetAllMessages body;

    public GetMessage(Boolean error, GetAllMessages body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public GetAllMessages getBody() {
        return body;
    }

    public void setBody(GetAllMessages body) {
        this.body = body;
    }
}
