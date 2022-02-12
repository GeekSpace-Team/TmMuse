package geek.space.tmmuse.Model.Interest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostInterest {
    private String user_id;
    private ArrayList<Integer> items_id = new ArrayList<>();

    public PostInterest(String user_id, ArrayList<Integer> items_id) {
        this.user_id = user_id;
        this.items_id = items_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Integer> getItems_id() {
        return items_id;
    }

    public void setItems_id(ArrayList<Integer> items_id) {
        this.items_id = items_id;
    }
}
