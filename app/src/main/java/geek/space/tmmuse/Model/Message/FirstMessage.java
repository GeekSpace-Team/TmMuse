package geek.space.tmmuse.Model.Message;

import com.google.gson.annotations.SerializedName;

public class FirstMessage {
    private Boolean is_read;
    @SerializedName("title")
    private String tittle;
    private String message;
    private Boolean is_all;
    private String created_at;
    private String updated_at;

    public FirstMessage(Boolean is_read, String tittle, String message, Boolean is_all, String created_at, String updated_at) {
        this.is_read = is_read;
        this.tittle = tittle;
        this.message = message;
        this.is_all = is_all;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIs_all() {
        return is_all;
    }

    public void setIs_all(Boolean is_all) {
        this.is_all = is_all;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
