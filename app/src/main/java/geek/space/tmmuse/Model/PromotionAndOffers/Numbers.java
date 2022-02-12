package geek.space.tmmuse.Model.PromotionAndOffers;

public class Numbers {
    private int id;
    private String phone_number;

    public Numbers(int id, String phone_number) {
        this.id = id;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
