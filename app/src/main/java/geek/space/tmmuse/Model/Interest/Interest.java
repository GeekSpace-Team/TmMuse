package geek.space.tmmuse.Model.Interest;

import java.util.ArrayList;

public class Interest {
    private String title;
    private ArrayList<SubInterest> subInterests;

    public Interest(String title, ArrayList<SubInterest> subInterests) {
        this.title = title;
        this.subInterests = subInterests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SubInterest> getSubInterests() {
        return subInterests;
    }

    public void setSubInterests(ArrayList<SubInterest> subInterests) {
        this.subInterests = subInterests;
    }
}
