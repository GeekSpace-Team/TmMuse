package geek.space.tmmuse.Model.Message;

import java.util.ArrayList;

public class GetAllMessages {
    private ArrayList<FirstMessage> first = new ArrayList<>();
    private ArrayList<FirstMessage> second =new ArrayList<>();
    private ArrayList<FirstMessage> third = new ArrayList<>();

    public GetAllMessages(ArrayList<FirstMessage> first, ArrayList<FirstMessage> second, ArrayList<FirstMessage> third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public ArrayList<FirstMessage> getFirst() {
        return first;
    }

    public void setFirst(ArrayList<FirstMessage> first) {
        this.first = first;
    }

    public ArrayList<FirstMessage> getSecond() {
        return second;
    }

    public void setSecond(ArrayList<FirstMessage> second) {
        this.second = second;
    }

    public ArrayList<FirstMessage> getThird() {
        return third;
    }

    public void setThird(ArrayList<FirstMessage> third) {
        this.third = third;
    }
}
