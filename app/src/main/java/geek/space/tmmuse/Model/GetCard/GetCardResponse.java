package geek.space.tmmuse.Model.GetCard;

import java.util.ArrayList;

import geek.space.tmmuse.Model.AllProfile.ImgProfile;

public class GetCardResponse {
    private Boolean error;
    private ArrayList<GetCardBody> body = new ArrayList<>();

    public GetCardResponse(Boolean error, ArrayList<GetCardBody> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<GetCardBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<GetCardBody> body) {
        this.body = body;
    }
}
