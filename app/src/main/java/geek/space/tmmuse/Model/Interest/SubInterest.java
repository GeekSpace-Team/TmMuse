package geek.space.tmmuse.Model.Interest;

public class SubInterest {
    private Integer id;
    private String interest;

    public SubInterest(Integer id, String interest) {
        this.id = id;
        this.interest = interest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
