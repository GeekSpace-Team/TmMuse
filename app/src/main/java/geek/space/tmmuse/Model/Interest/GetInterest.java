package geek.space.tmmuse.Model.Interest;

import java.util.ArrayList;

public class GetInterest {
    private Boolean error;
    private ArrayList<Interest> body = new ArrayList<>();

    public GetInterest(Boolean error, ArrayList<Interest> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<Interest> getBody() {
        return body;
    }

    public void setBody(ArrayList<Interest> body) {
        this.body = body;
    }
}
