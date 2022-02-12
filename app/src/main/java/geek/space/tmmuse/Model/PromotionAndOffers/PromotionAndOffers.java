package geek.space.tmmuse.Model.PromotionAndOffers;

import java.util.ArrayList;

public class PromotionAndOffers {
    private int id;
    private String titleTM;
    private String titleRU;
    private String descriptionTM;
    private String descriptionRU;
    private String image;
    private String promotion;
    private String view_count;
    private String like;
    private String dislike;
    private int profile_id;
    private String instagram;
    private ArrayList<Numbers> numbers = new ArrayList<>();

    public PromotionAndOffers(int id, String titleTM, String titleRU, String descriptionTM, String descriptionRU,
                              String image, String promotion, String view_count, String like, String dislike,
                              int profile_id, String instagram, ArrayList<Numbers> numbers) {
        this.id = id;
        this.titleTM = titleTM;
        this.titleRU = titleRU;
        this.descriptionTM = descriptionTM;
        this.descriptionRU = descriptionRU;
        this.image = image;
        this.promotion = promotion;
        this.view_count = view_count;
        this.like = like;
        this.dislike = dislike;
        this.profile_id = profile_id;
        this.instagram = instagram;
        this.numbers = numbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public ArrayList<Numbers> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Numbers> numbers) {
        this.numbers = numbers;
    }
}
