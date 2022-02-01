package geek.space.tmmuse.Model.Film;

import java.util.ArrayList;

public class MovieTime {
    private String date;
    private ArrayList<String> times=new ArrayList<>();

    public MovieTime(String date, ArrayList<String> times) {
        this.date = date;
        this.times = times;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }
}
