package geek.space.tmmuse.Model.Interest;

public class SubInterest {
    private Integer id;
    private String titleTM;
    private String titleRU;

    public SubInterest(Integer id, String titleTM, String titleRU) {
        this.id = id;
        this.titleTM = titleTM;
        this.titleRU = titleRU;
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
}
