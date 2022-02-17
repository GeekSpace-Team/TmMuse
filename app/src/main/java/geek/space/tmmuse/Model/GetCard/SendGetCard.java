package geek.space.tmmuse.Model.GetCard;

public class SendGetCard {
    private String date;
    private Integer gender;
    private String email;
    private Boolean is_sms;
    private Integer status;

    public SendGetCard(String date, Integer gender, String email, Boolean is_sms, Integer status) {
        this.date = date;
        this.gender = gender;
        this.email = email;
        this.is_sms = is_sms;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
