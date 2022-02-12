package geek.space.tmmuse.Model.UserRegister;

public class ResponseUserBody {
    private Integer id;
    private String fullname;
    private String phone_number;
    private String token;

    public ResponseUserBody(Integer id, String fullname, String phone_number, String token) {
        this.id = id;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
