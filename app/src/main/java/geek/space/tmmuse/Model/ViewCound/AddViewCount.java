package geek.space.tmmuse.Model.ViewCound;

public class AddViewCount {
    private Integer ads_id;
    private String type;
    private Integer user_id;

    public AddViewCount(Integer ads_id, String type, Integer user_id) {
        this.ads_id = ads_id;
        this.type = type;
        this.user_id = user_id;
    }

    public Integer getAds_id() {
        return ads_id;
    }

    public void setAds_id(Integer ads_id) {
        this.ads_id = ads_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
