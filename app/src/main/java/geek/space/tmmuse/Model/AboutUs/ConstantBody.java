package geek.space.tmmuse.Model.AboutUs;

public class ConstantBody {
    private Integer id;
    private String titleTM;
    private String titleRU;
    private String contentTM;
    private String contentRU;

    public ConstantBody(Integer id, String titleTM, String titleRU, String contentTM, String contentRU) {
        this.id = id;
        this.titleTM = titleTM;
        this.titleRU = titleRU;
        this.contentTM = contentTM;
        this.contentRU = contentRU;
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

    public String getContentTM() {
        return contentTM;
    }

    public void setContentTM(String contentTM) {
        this.contentTM = contentTM;
    }

    public String getContentRU() {
        return contentRU;
    }

    public void setContentRU(String contentRU) {
        this.contentRU = contentRU;
    }
}
