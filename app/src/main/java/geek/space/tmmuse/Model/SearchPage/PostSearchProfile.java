package geek.space.tmmuse.Model.SearchPage;

import java.util.ArrayList;

import geek.space.tmmuse.Model.AllProfile.AllProfile;

public class PostSearchProfile {
    private Boolean error;
    private ArrayList<AllProfile> body = new ArrayList<>();

    public PostSearchProfile(Boolean error, ArrayList<AllProfile> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<AllProfile> getBody() {
        return body;
    }

    public void setBody(ArrayList<AllProfile> body) {
        this.body = body;
    }
}
