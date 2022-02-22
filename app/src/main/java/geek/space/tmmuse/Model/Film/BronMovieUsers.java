package geek.space.tmmuse.Model.Film;

import java.util.ArrayList;

import geek.space.tmmuse.Model.AllProfile.ImgProfile;

public class BronMovieUsers {
    private Integer id;
    private Integer profile_id;
    private String movie_date;
    private String movie_time;
    private Integer ticket_count;
    private Integer ticket_discount;
    private Integer ticket_price;
    private Integer status;
    private String nameTM;
    private String nameRU;
    private String short_descTM;
    private String short_descRU;
    private String descriptionTM;
    private String descriptionRU;
    private String fullname;
    private String phone_number;
    private ArrayList<ImgProfile> image = new ArrayList<>();

    public BronMovieUsers(Integer id, Integer profile_id, String movie_date, String movie_time,
                          Integer ticket_count, Integer ticket_discount, Integer ticket_price,
                          Integer status, String nameTM, String nameRU, String short_descTM,
                          String short_descRU, String descriptionTM, String descriptionRU,
                          String fullname, String phone_number, ArrayList<ImgProfile> image) {
        this.id = id;
        this.profile_id = profile_id;
        this.movie_date = movie_date;
        this.movie_time = movie_time;
        this.ticket_count = ticket_count;
        this.ticket_discount = ticket_discount;
        this.ticket_price = ticket_price;
        this.status = status;
        this.nameTM = nameTM;
        this.nameRU = nameRU;
        this.short_descTM = short_descTM;
        this.short_descRU = short_descRU;
        this.descriptionTM = descriptionTM;
        this.descriptionRU = descriptionRU;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }

    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
    }

    public Integer getTicket_discount() {
        return ticket_discount;
    }

    public void setTicket_discount(Integer ticket_discount) {
        this.ticket_discount = ticket_discount;
    }

    public Integer getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Integer ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public ArrayList<ImgProfile> getImage() {
        return image;
    }

    public void setImage(ArrayList<ImgProfile> image) {
        this.image = image;
    }
}
