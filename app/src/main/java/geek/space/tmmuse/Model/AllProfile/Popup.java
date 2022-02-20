package geek.space.tmmuse.Model.AllProfile;

public class Popup {
    private Integer id;
    private String comment_of_admin;
    private String image;
    private String site_url;
    private String titleTM;
    private String titleRU;
    private String descriptionTM;
    private String descriptionRU;
    private Integer profile_id;

    public Popup(Integer id, String comment_of_admin, String image, String site_url, String titleTM, String titleRU, String descriptionTM, String descriptionRU, Integer profile_id) {
        this.id = id;
        this.comment_of_admin = comment_of_admin;
        this.image = image;
        this.site_url = site_url;
        this.titleTM = titleTM;
        this.titleRU = titleRU;
        this.descriptionTM = descriptionTM;
        this.descriptionRU = descriptionRU;
        this.profile_id = profile_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment_of_admin() {
        return comment_of_admin;
    }

    public void setComment_of_admin(String comment_of_admin) {
        this.comment_of_admin = comment_of_admin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
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

    public String getDescriptionTM() {
        return descriptionTM;
    }

    public void setDescriptionTM(String descriptionTM) {
        this.descriptionTM = descriptionTM;
    }

    public String getDescriptionRU() {
        return descriptionRU;
    }

    public void setDescriptionRU(String descriptionRU) {
        this.descriptionRU = descriptionRU;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }
}
