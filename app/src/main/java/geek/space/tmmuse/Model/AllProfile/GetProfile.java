package geek.space.tmmuse.Model.AllProfile;

import java.util.ArrayList;

public class GetProfile {
    private ArrayList <Integer> category = new ArrayList<>();
    private Integer sort;
    private ArrayList<Integer> tags_id = new ArrayList<>();
    private Integer limit;
    private Integer page;

    public GetProfile(ArrayList<Integer> category, Integer sort, ArrayList<Integer> tags_id, Integer limit, Integer page) {
        this.category = category;
        this.sort = sort;
        this.tags_id = tags_id;
        this.limit = limit;
        this.page = page;
    }

    public ArrayList<Integer> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Integer> category) {
        this.category = category;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ArrayList<Integer> getTags_id() {
        return tags_id;
    }

    public void setTags_id(ArrayList<Integer> tags_id) {
        this.tags_id = tags_id;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
