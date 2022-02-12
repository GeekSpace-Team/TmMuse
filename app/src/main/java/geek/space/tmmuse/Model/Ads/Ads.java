package geek.space.tmmuse.Model.Ads;

public class Ads {
    private Integer id;
    private String nameTM;
    private String nameRU;
    private String comment_of_admin;
    private String image;
    private Integer profile_id;
    private Boolean is_main;

    public Ads(Integer id, String nameTM, String nameRU, String comment_of_admin, String image, Integer profile_id, Boolean is_main) {
        this.id = id;
        this.nameTM = nameTM;
        this.nameRU = nameRU;
        this.comment_of_admin = comment_of_admin;
        this.image = image;
        this.profile_id = profile_id;
        this.is_main = is_main;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTM() {
        return nameTM;
    }

    public void setNameTM(String nameTM) {
        this.nameTM = nameTM;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
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

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public Boolean getIs_main() {
        return is_main;
    }

    public void setIs_main(Boolean is_main) {
        this.is_main = is_main;
    }
}

