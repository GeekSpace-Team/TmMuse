package geek.space.tmmuse.Model.AllProfile;

import java.util.ArrayList;

import geek.space.tmmuse.Model.Home.Body;

public class ResponseAllProfile {
    private Boolean error;
   private AllProfileBody body;

    public ResponseAllProfile(Boolean error, AllProfileBody body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public AllProfileBody getBody() {
        return body;
    }

    public void setBody(AllProfileBody body) {
        this.body = body;
    }
}
