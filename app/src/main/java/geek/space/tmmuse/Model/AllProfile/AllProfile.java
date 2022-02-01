package geek.space.tmmuse.Model.AllProfile;

public class AllProfile {
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;
    private int viewType;

    private int id;
    private String name_profile;
    private String desc_profile;
    private String img_profile;
    private String address_desc;
    private String work_time_desc;
    private String payment_desc;
    private String delivery_desc;
    private String cuisine_desc;
    private String average_check_desc;
    private String own_promotion_desc;
    private String tm_muse_card_desc;
    private String finger_up_count;
    private String finger_down_count;
    private String instagram;
    private String website;
    private String location;

    public AllProfile(int viewType, int id, String name_profile, String desc_profile, String img_profile,
                      String address_desc, String work_time_desc, String payment_desc, String delivery_desc,
                      String cuisine_desc, String average_check_desc, String own_promotion_desc, String tm_muse_card_desc,
                      String finger_up_count, String finger_down_count, String instagram, String website,
                      String location, String img_banner) {

        this.viewType = viewType;
        this.id = id;
        this.name_profile = name_profile;
        this.desc_profile = desc_profile;
        this.img_profile = img_profile;
        this.address_desc = address_desc;
        this.work_time_desc = work_time_desc;
        this.payment_desc = payment_desc;
        this.delivery_desc = delivery_desc;
        this.average_check_desc = average_check_desc;
        this.own_promotion_desc = own_promotion_desc;
        this.tm_muse_card_desc = tm_muse_card_desc;
        this.finger_up_count = finger_up_count;
        this.finger_down_count = finger_down_count;
        this.instagram = instagram;
        this.website = website;
        this.location = location;
        this.img_banner = img_banner;
        this.cuisine_desc = cuisine_desc;
    }

    public String getCuisine_desc() {
        return cuisine_desc;
    }

    public void setCuisine_desc(String cuisine_desc) {
        this.cuisine_desc = cuisine_desc;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_profile() {
        return name_profile;
    }

    public void setName_profile(String name_profile) {
        this.name_profile = name_profile;
    }

    public String getDesc_profile() {
        return desc_profile;
    }

    public void setDesc_profile(String desc_profile) {
        this.desc_profile = desc_profile;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }

    public String getAddress_desc() {
        return address_desc;
    }

    public void setAddress_desc(String address_desc) {
        this.address_desc = address_desc;
    }

    public String getWork_time_desc() {
        return work_time_desc;
    }

    public void setWork_time_desc(String work_time_desc) {
        this.work_time_desc = work_time_desc;
    }

    public String getPayment_desc() {
        return payment_desc;
    }

    public void setPayment_desc(String payment_desc) {
        this.payment_desc = payment_desc;
    }

    public String getDelivery_desc() {
        return delivery_desc;
    }

    public void setDelivery_desc(String delivery_desc) {
        this.delivery_desc = delivery_desc;
    }

    public String getAverage_check_desc() {
        return average_check_desc;
    }

    public void setAverage_check_desc(String average_check_desc) {
        this.average_check_desc = average_check_desc;
    }

    public String getOwn_promotion_desc() {
        return own_promotion_desc;
    }

    public void setOwn_promotion_desc(String own_promotion_desc) {
        this.own_promotion_desc = own_promotion_desc;
    }

    public String getTm_muse_card_desc() {
        return tm_muse_card_desc;
    }

    public void setTm_muse_card_desc(String tm_muse_card_desc) {
        this.tm_muse_card_desc = tm_muse_card_desc;
    }

    public String getFinger_up_count() {
        return finger_up_count;
    }

    public void setFinger_up_count(String finger_up_count) {
        this.finger_up_count = finger_up_count;
    }

    public String getFinger_down_count() {
        return finger_down_count;
    }

    public void setFinger_down_count(String finger_down_count) {
        this.finger_down_count = finger_down_count;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String img_banner;

    public AllProfile(int viewType, int id, String img_banner) {
        this.img_banner = img_banner;
        this.id = id;
        this.viewType = viewType;
    }

    public String getImg_banner() {
        return img_banner;
    }

    public void setImg_banner(String img_banner) {
        this.img_banner = img_banner;
    }


}
