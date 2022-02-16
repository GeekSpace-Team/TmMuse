package geek.space.tmmuse.Model.AllProfile;

public class NumbersProfile {
    private Integer id;
    private String phone_number;

    public NumbersProfile(Integer id, String phone_number) {
        this.id = id;
        this.phone_number = phone_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
