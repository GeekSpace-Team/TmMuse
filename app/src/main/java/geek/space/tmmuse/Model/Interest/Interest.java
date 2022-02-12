package geek.space.tmmuse.Model.Interest;

import java.util.ArrayList;

public class Interest {
    private Integer id;
    private String titleTM;
    private String titleRU;
    private ArrayList<SubInterest> item;

    public Interest(Integer id, String titleTM, String titleRU, ArrayList<SubInterest> item) {
        this.id = id;
        this.titleTM = titleTM;
        this.titleRU = titleRU;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleTM() {
        return titleTM;
    }

    public void setTitleTM(String titleTM) {
        this.titleTM = titleTM;
    }

    public String getTitleRU() {
        return titleRU;
    }

    public void setTitleRU(String titleRU) {
        this.titleRU = titleRU;
    }

    public ArrayList<SubInterest> getItem() {
        return item;
    }

    public void setItem(ArrayList<SubInterest> item) {
        this.item = item;
    }
}
