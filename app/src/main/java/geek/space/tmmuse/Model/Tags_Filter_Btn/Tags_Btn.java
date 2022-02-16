package geek.space.tmmuse.Model.Tags_Filter_Btn;

public class Tags_Btn {
    private Integer id;
    private String tagTM;
    private String tagRU;

    public Tags_Btn(Integer id, String tagTM, String tagRU) {
        this.id = id;
        this.tagTM = tagTM;
        this.tagRU = tagRU;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagTM() {
        return tagTM;
    }

    public void setTagTM(String tagTM) {
        this.tagTM = tagTM;
    }

    public String getTagRU() {
        return tagRU;
    }

    public void setTagRU(String tagRU) {
        this.tagRU = tagRU;
    }
}
