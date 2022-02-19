package geek.space.tmmuse.Model.SearchHistory;

import java.util.ArrayList;

public class GetSearchHistory {
    private Boolean error;
    private ArrayList<SearchHistory> body = new ArrayList<>();

    public GetSearchHistory(Boolean error, ArrayList<SearchHistory> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<SearchHistory> getBody() {
        return body;
    }

    public void setBody(ArrayList<SearchHistory> body) {
        this.body = body;
    }
}
