package geek.space.tmmuse.Model.PromotionAndOffers;

public class PromotionAndOffers {
    private int id;
    private String percent_text;
    private String offers_name;
    private String offers_img;
    private String offers_desc;

    public PromotionAndOffers(int id, String percent_text, String offers_name, String offers_img, String offers_desc) {
        this.id = id;
        this.percent_text = percent_text;
        this.offers_name = offers_name;
        this.offers_img = offers_img;
        this.offers_desc = offers_desc;
    }

    public String getOffers_desc() {
        return offers_desc;
    }

    public void setOffers_desc(String offers_desc) {
        this.offers_desc = offers_desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getPercent_text() {
        return percent_text;
    }

    public void setPercent_text(String percent_text) {
        this.percent_text = percent_text;
    }

    public String getOffers_name() {
        return offers_name;
    }

    public void setOffers_name(String offers_name) {
        this.offers_name = offers_name;
    }

    public String getOffers_img() {
        return offers_img;
    }

    public void setOffers_img(String offers_img) {
        this.offers_img = offers_img;
    }
}
