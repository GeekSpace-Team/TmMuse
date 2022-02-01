package geek.space.tmmuse.Model.SearchPage;

import java.util.ArrayList;

public class SearchPage {
    private int id;
    private String name;
    private String image;
    private String desc;
    private String catId;
    private String catName;

    public SearchPage(int id, String name, String image,String desc, String catId, String catName) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.catId = catId;
        this.catName = catName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
