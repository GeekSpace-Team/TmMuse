package geek.space.tmmuse.Model.GetPromoCode;

public class GetPromoCodes {
    private Boolean error;
    private Integer body;

    public GetPromoCodes(Boolean error, Integer body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }
}
