package geek.space.tmmuse.Model.AllProfile;

import java.util.ArrayList;

import geek.space.tmmuse.Model.Ads.Ads;
import geek.space.tmmuse.Model.Tags_Filter_Btn.Tags_Btn;

public class AllProfileBody {
    private ArrayList<AllProfile> profiles = new ArrayList<>();
    private ArrayList<Ads> ads = new ArrayList<>();
    private ArrayList<Tags_Btn> tags = new ArrayList<>();

    public AllProfileBody(ArrayList<AllProfile> profiles, ArrayList<Ads> ads, ArrayList<Tags_Btn> tags) {
        this.profiles = profiles;
        this.ads = ads;
        this.tags = tags;
    }

    public ArrayList<AllProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<AllProfile> profiles) {
        this.profiles = profiles;
    }

    public ArrayList<Ads> getAds() {
        return ads;
    }

    public void setAds(ArrayList<Ads> ads) {
        this.ads = ads;
    }

    public ArrayList<Tags_Btn> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tags_Btn> tags) {
        this.tags = tags;
    }
}
