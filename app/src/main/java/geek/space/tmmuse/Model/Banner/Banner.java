package geek.space.tmmuse.Model.Banner;

public class Banner {
    private int id;
    private String image;
    private String link;
    private String order;
    private Integer profile_id;


    public Banner(int id, String image, String link, Integer profile_id, String order) {
        this.id = id;
        this.image = image;
        this.link = link;
        this.profile_id = profile_id;
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }
}
