package geek.space.tmmuse.Model.Home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import geek.space.tmmuse.Model.Ads.Ads;
import geek.space.tmmuse.Model.AllProfile.Popup;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.Model.Film.Film;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;

public class Body {
    @SerializedName("promotions")
    private ArrayList <PromotionAndOffers> promotionAndOffers = new ArrayList<>();
    private ArrayList<Banner> banners = new ArrayList<>();
    private ArrayList<Film> new_movies = new ArrayList<>();
    private ArrayList<Ads> ads = new ArrayList<>();
    private ArrayList<Popup> popup = new ArrayList<>();

    public Body(ArrayList<PromotionAndOffers> promotionAndOffers, ArrayList<Banner> banners, ArrayList<Film> new_movies, ArrayList<Ads> ads, ArrayList<Popup> popup) {
        this.promotionAndOffers = promotionAndOffers;
        this.banners = banners;
        this.new_movies = new_movies;
        this.ads = ads;
        this.popup = popup;
    }

    public ArrayList<PromotionAndOffers> getPromotionAndOffers() {
        return promotionAndOffers;
    }

    public void setPromotionAndOffers(ArrayList<PromotionAndOffers> promotionAndOffers) {
        this.promotionAndOffers = promotionAndOffers;
    }

    public ArrayList<Banner> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<Banner> banners) {
        this.banners = banners;
    }

    public ArrayList<Film> getNew_movies() {
        return new_movies;
    }

    public void setNew_movies(ArrayList<Film> new_movies) {
        this.new_movies = new_movies;
    }

    public ArrayList<Ads> getAds() {
        return ads;
    }

    public void setAds(ArrayList<Ads> ads) {
        this.ads = ads;
    }

    public ArrayList<Popup> getPopup() {
        return popup;
    }

    public void setPopup(ArrayList<Popup> popup) {
        this.popup = popup;
    }
}
