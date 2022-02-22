package geek.space.tmmuse.Model.Film;

import java.util.ArrayList;

public class BronMovieUsersBody {
    private Boolean error;
    private ArrayList<BronMovieUsers> body = new ArrayList<>();

    public BronMovieUsersBody(Boolean error, ArrayList<BronMovieUsers> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<BronMovieUsers> getBody() {
        return body;
    }

    public void setBody(ArrayList<BronMovieUsers> body) {
        this.body = body;
    }
}
