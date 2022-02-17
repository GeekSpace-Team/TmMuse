package geek.space.tmmuse.Model.AllProfile;

import android.nfc.Tag;

import java.util.ArrayList;

import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.Model.Tags_Filter_Btn.Tags_Btn;

public class GetProfileTinyBody {
    private AllProfile profile;
    private ArrayList<ProfilePhone> phone_numbers = new ArrayList<>();
    private ArrayList<ImgProfile> images = new ArrayList<>();
    private ArrayList<PromotionAndOffers> posts = new ArrayList<>();
    private ArrayList<Tags_Btn> tags = new ArrayList<>();

    public GetProfileTinyBody(AllProfile profile, ArrayList<ProfilePhone> phone_numbers, ArrayList<ImgProfile> images, ArrayList<PromotionAndOffers> posts, ArrayList<Tags_Btn> tags) {
        this.profile = profile;
        this.phone_numbers = phone_numbers;
        this.images = images;
        this.posts = posts;
        this.tags = tags;
    }

    public AllProfile getProfile() {
        return profile;
    }

    public void setProfile(AllProfile profile) {
        this.profile = profile;
    }

    public ArrayList<ProfilePhone> getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(ArrayList<ProfilePhone> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public ArrayList<ImgProfile> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImgProfile> images) {
        this.images = images;
    }

    public ArrayList<PromotionAndOffers> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PromotionAndOffers> posts) {
        this.posts = posts;
    }

    public ArrayList<Tags_Btn> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tags_Btn> tags) {
        this.tags = tags;
    }
}
