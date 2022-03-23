package com.muse.muse.Model.UserRegister;

public class GetUserInfoBody {
    private Integer user_id;
    private String fullname;
    private String phone_number;
    private String date_of_birth;
    private String expired;
    private Integer gender;
    private String email;
    private Boolean is_sms;
    private Integer status;
    private String card_id;

    public GetUserInfoBody(Integer user_id, String fullname, String phone_number, String date_of_birth,
                           String expired, Integer gender, String email, Boolean is_sms, Integer status, String card_id) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.date_of_birth = date_of_birth;
        this.expired = expired;
        this.gender = gender;
        this.email = email;
        this.is_sms = is_sms;
        this.status = status;
        this.card_id = card_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs_sms() {
        return is_sms;
    }

    public void setIs_sms(Boolean is_sms) {
        this.is_sms = is_sms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
}
