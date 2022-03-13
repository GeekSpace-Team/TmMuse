package com.muse.muse.Model.AllProfile;

public class GaleriesProfile {
    private Integer id;
    private String medium_image;
    private String large_image;
    private Integer profile_id;

    public GaleriesProfile(Integer id, String medium_image, String large_image, Integer profile_id) {
        this.id = id;
        this.medium_image = medium_image;
        this.large_image = large_image;
        this.profile_id = profile_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedium_image() {
        return medium_image;
    }

    public void setMedium_image(String medium_image) {
        this.medium_image = medium_image;
    }

    public String getLarge_image() {
        return large_image;
    }

    public void setLarge_image(String large_image) {
        this.large_image = large_image;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }
}
