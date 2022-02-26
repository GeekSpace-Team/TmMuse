package geek.space.tmmuse.Model.Certificate;

public class InsertCertificate {
    private Integer amount;
    private Integer profile_id;

    public InsertCertificate(Integer amount, Integer profile_id) {
        this.amount = amount;
        this.profile_id = profile_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }
}
