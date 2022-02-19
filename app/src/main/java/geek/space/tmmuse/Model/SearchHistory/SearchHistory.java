package geek.space.tmmuse.Model.SearchHistory;

public class SearchHistory {
    private Integer page;
    private Integer limit;
    private String text;

    public SearchHistory(Integer page, Integer limit, String text) {
        this.page = page;
        this.limit = limit;
        this.text = text;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
