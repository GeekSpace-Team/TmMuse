package geek.space.tmmuse.Model.UserRegister;

public class CheckUserCode {
    private String fullname;
    private String phone_number;
    private String code;

    public CheckUserCode(String fullname, String phone_number, String code) {
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
