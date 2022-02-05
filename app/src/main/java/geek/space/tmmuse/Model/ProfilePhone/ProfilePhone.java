package geek.space.tmmuse.Model.ProfilePhone;

public class ProfilePhone {
    private int id;
    private String number_profile;

    public ProfilePhone(int id, String number_profile) {
        this.id = id;
        this.number_profile = number_profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber_profile() {
        return number_profile;
    }

    public void setNumber_profile(String number_profile) {
        this.number_profile = number_profile;
    }
}
