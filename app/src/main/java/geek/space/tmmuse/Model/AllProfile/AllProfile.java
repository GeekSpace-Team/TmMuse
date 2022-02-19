package geek.space.tmmuse.Model.AllProfile;

import java.util.ArrayList;

public class AllProfile {

    private Integer id;
    private String nameTM;
    private String nameRU;
    private String short_descTM;
    private String short_descRU;
    private Integer like;
    private Integer dislike;
    private String instagram;
    private String site;
    private Integer status;
    private Integer is_VIP;
    private ImgProfile image;
    private ArrayList<NumbersProfile> phone_numbers;
    private Boolean is_promotion;
    private String promotion_status;
    private String location;
    private String address;
    private String work_hours;
    private Boolean delivery;
    private String cousineTM;
    private String cousineRU;
    private String average_check;
    private Boolean is_active_card;
    private Integer tm_muse_card;
    private Boolean is_certificate;
    private Boolean is_promo;
    private Integer category_id;
    private Integer view_count;
    private Integer promo_count;
    private String descriptionTM;
    private String descriptionRU;
    private Integer order_in_list;
    private String free_time;
    private Boolean is_cash;
    private Boolean is_terminal;
    private Boolean is_wifi;

    public AllProfile(Integer id, String nameTM, String nameRU, String short_descTM, String short_descRU, Integer like, Integer dislike, String instagram, String site, Integer status, Integer is_VIP, ImgProfile image, ArrayList<NumbersProfile> phone_numbers, Boolean is_promotion, String promotion_status, String location, String address, String work_hours, Boolean delivery, String cousineTM, String cousineRU, String average_check, Boolean is_active_card, Integer tm_muse_card, Boolean is_certificate, Boolean is_promo, Integer category_id, Integer view_count, Integer promo_count, String descriptionTM, String descriptionRU, Integer order_in_list, String free_time, Boolean is_cash, Boolean is_terminal, Boolean is_wifi) {
        this.id = id;
        this.nameTM = nameTM;
        this.nameRU = nameRU;
        this.short_descTM = short_descTM;
        this.short_descRU = short_descRU;
        this.like = like;
        this.dislike = dislike;
        this.instagram = instagram;
        this.site = site;
        this.status = status;
        this.is_VIP = is_VIP;
        this.image = image;
        this.phone_numbers = phone_numbers;
        this.is_promotion = is_promotion;
        this.promotion_status = promotion_status;
        this.location = location;
        this.address = address;
        this.work_hours = work_hours;
        this.delivery = delivery;
        this.cousineTM = cousineTM;
        this.cousineRU = cousineRU;
        this.average_check = average_check;
        this.is_active_card = is_active_card;
        this.tm_muse_card = tm_muse_card;
        this.is_certificate = is_certificate;
        this.is_promo = is_promo;
        this.category_id = category_id;
        this.view_count = view_count;
        this.promo_count = promo_count;
        this.descriptionTM = descriptionTM;
        this.descriptionRU = descriptionRU;
        this.order_in_list = order_in_list;
        this.free_time = free_time;
        this.is_cash = is_cash;
        this.is_terminal = is_terminal;
        this.is_wifi = is_wifi;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIs_VIP() {
        return is_VIP;
    }

    public void setIs_VIP(Integer is_VIP) {
        this.is_VIP = is_VIP;
    }

    public ImgProfile getImage() {
        return image;
    }

    public void setImage(ImgProfile image) {
        this.image = image;
    }

    public ArrayList<NumbersProfile> getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(ArrayList<NumbersProfile> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public Boolean getIs_promotion() {
        return is_promotion;
    }

    public void setIs_promotion(Boolean is_promotion) {
        this.is_promotion = is_promotion;
    }

    public String getPromotion_status() {
        return promotion_status;
    }

    public void setPromotion_status(String promotion_status) {
        this.promotion_status = promotion_status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork_hours() {
        return work_hours;
    }

    public void setWork_hours(String work_hours) {
        this.work_hours = work_hours;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getCousineTM() {
        return cousineTM;
    }

    public void setCousineTM(String cousineTM) {
        this.cousineTM = cousineTM;
    }

    public String getCousineRU() {
        return cousineRU;
    }

    public void setCousineRU(String cousineRU) {
        this.cousineRU = cousineRU;
    }

    public String getAverage_check() {
        return average_check;
    }

    public void setAverage_check(String average_check) {
        this.average_check = average_check;
    }

    public Boolean getIs_active_card() {
        return is_active_card;
    }

    public void setIs_active_card(Boolean is_active_card) {
        this.is_active_card = is_active_card;
    }

    public Integer getTm_muse_card() {
        return tm_muse_card;
    }

    public void setTm_muse_card(Integer tm_muse_card) {
        this.tm_muse_card = tm_muse_card;
    }

    public Boolean getIs_certificate() {
        return is_certificate;
    }

    public void setIs_certificate(Boolean is_certificate) {
        this.is_certificate = is_certificate;
    }

    public Boolean getIs_promo() {
        return is_promo;
    }

    public void setIs_promo(Boolean is_promo) {
        this.is_promo = is_promo;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Integer getPromo_count() {
        return promo_count;
    }

    public void setPromo_count(Integer promo_count) {
        this.promo_count = promo_count;
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

    public Integer getOrder_in_list() {
        return order_in_list;
    }

    public void setOrder_in_list(Integer order_in_list) {
        this.order_in_list = order_in_list;
    }

    public String getFree_time() {
        return free_time;
    }

    public void setFree_time(String free_time) {
        this.free_time = free_time;
    }

    public Boolean getIs_cash() {
        return is_cash;
    }

    public void setIs_cash(Boolean is_cash) {
        this.is_cash = is_cash;
    }

    public Boolean getIs_terminal() {
        return is_terminal;
    }

    public void setIs_terminal(Boolean is_terminal) {
        this.is_terminal = is_terminal;
    }

    public Boolean getIs_wifi() {
        return is_wifi;
    }

    public void setIs_wifi(Boolean is_wifi) {
        this.is_wifi = is_wifi;
    }
}
