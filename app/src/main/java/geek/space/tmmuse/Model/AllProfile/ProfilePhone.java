package geek.space.tmmuse.Model.AllProfile;

public class ProfilePhone {
    private int id;
    private String phone_number;

    public ProfilePhone(int id, String phone_number) {
        this.id = id;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber_profile() {
        return phone_number;
    }

    public void setNumber_profile(String number_profile) {
        this.phone_number = number_profile;
    }
}
