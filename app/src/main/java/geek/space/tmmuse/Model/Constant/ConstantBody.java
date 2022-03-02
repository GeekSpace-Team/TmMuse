package geek.space.tmmuse.Model.Constant;

public class ConstantBody {
    private Integer id;
    private String titleTM;
    private String titleRU;
    private String contentTM;
    private String contentRU;
    private String contentTM_dark;
    private String contentRU_dark;

    public ConstantBody(Integer id, String titleTM, String titleRU, String contentTM, String contentRU, String contentTM_dark, String contentRU_dark) {
        this.id = id;
        this.titleTM = titleTM;
        this.titleRU = titleRU;
        this.contentTM = contentTM;
        this.contentRU = contentRU;
        this.contentTM_dark = contentTM_dark;
        this.contentRU_dark = contentRU_dark;
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

    public String getContentTM_dark() {
        return contentTM_dark;
    }

    public void setContentTM_dark(String contentTM_dark) {
        this.contentTM_dark = contentTM_dark;
    }

    public String getContentRU_dark() {
        return contentRU_dark;
    }

    public void setContentRU_dark(String contentRU_dark) {
        this.contentRU_dark = contentRU_dark;
    }
}
