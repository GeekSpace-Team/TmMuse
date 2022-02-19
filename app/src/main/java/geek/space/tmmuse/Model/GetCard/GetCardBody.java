package geek.space.tmmuse.Model.GetCard;

import java.util.ArrayList;

import geek.space.tmmuse.Model.AllProfile.ImgProfile;

public class GetCardBody {
    private Integer id;
    private String nameTM;
    private String nameRU;
    private String short_descTM;
    private String short_descRU;
    private Integer tm_muse_card;
    private Integer like;
    private Integer dislike;
    private String instagram;
    private String site;
    private ArrayList<ImgProfile> images = new ArrayList<>();

    public GetCardBody(Integer id, String nameTM, String nameRU, String short_descTM, String short_descRU, Integer tm_muse_card, Integer like, Integer dislike, String instagram, String site, ArrayList<ImgProfile> images) {
        this.id = id;
        this.nameTM = nameTM;
        this.nameRU = nameRU;
        this.short_descTM = short_descTM;
        this.short_descRU = short_descRU;
        this.tm_muse_card = tm_muse_card;
        this.like = like;
        this.dislike = dislike;
        this.instagram = instagram;
        this.site = site;
        this.images = images;
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

    public String getShort_descTM() {
        return short_descTM;
    }

    public void setShort_descTM(String short_descTM) {
        this.short_descTM = short_descTM;
    }

    public String getShort_descRU() {
        return short_descRU;
    }

    public void setShort_descRU(String short_descRU) {
        this.short_descRU = short_descRU;
    }

    public Integer getTm_muse_card() {
        return tm_muse_card;
    }

    public void setTm_muse_card(Integer tm_muse_card) {
        this.tm_muse_card = tm_muse_card;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public ArrayList<ImgProfile> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImgProfile> images) {
        this.images = images;
    }
}
