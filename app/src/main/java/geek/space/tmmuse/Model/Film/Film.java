package geek.space.tmmuse.Model.Film;

public class Film {
    private int id;
    private String film_name;
    private String film_desc;
    private String film_IMG;
    private String category_film;
    private String film_dis_like;
    private String film_like;
    private String film_phone_number;
    private String film_instagramm;
    private String film_location;
    private String film_web_site;
    private String film_month_day;
    private String film_time;
    private String film_address;
    private String film_price;
    private String film_tm_muse_percent;


    public Film(int id, String film_name, String film_desc, String film_IMG, String category_film,
                String film_dis_like, String film_like, String film_phone_number, String film_instagramm,
                String film_location, String film_web_site, String film_month_day, String film_time,
                String film_address, String film_price, String film_tm_muse_percent) {

        this.id = id;
        this.film_name = film_name;
        this.film_desc = film_desc;
        this.film_IMG = film_IMG;
        this.category_film = category_film;
        this.film_dis_like = film_dis_like;
        this.film_like = film_like;
        this.film_phone_number = film_phone_number;
        this.film_instagramm = film_instagramm;
        this.film_location = film_location;
        this.film_web_site = film_web_site;
        this.film_month_day = film_month_day;
        this.film_time = film_time;
        this.film_address = film_address;
        this.film_price = film_price;
        this.film_tm_muse_percent = film_tm_muse_percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getFilm_desc() {
        return film_desc;
    }

    public void setFilm_desc(String film_desc) {
        this.film_desc = film_desc;
    }

    public String getFilm_IMG() {
        return film_IMG;
    }

    public void setFilm_IMG(String film_IMG) {
        this.film_IMG = film_IMG;
    }

    public String getCategory_film() {
        return category_film;
    }

    public void setCategory_film(String category_film) {
        this.category_film = category_film;
    }

    public String getFilm_dis_like() {
        return film_dis_like;
    }

    public void setFilm_dis_like(String film_dis_like) {
        this.film_dis_like = film_dis_like;
    }

    public String getFilm_like() {
        return film_like;
    }

    public void setFilm_like(String film_like) {
        this.film_like = film_like;
    }

    public String getFilm_phone_number() {
        return film_phone_number;
    }

    public void setFilm_phone_number(String film_phone_number) {
        this.film_phone_number = film_phone_number;
    }

    public String getFilm_instagramm() {
        return film_instagramm;
    }

    public void setFilm_instagramm(String film_instagramm) {
        this.film_instagramm = film_instagramm;
    }

    public String getFilm_location() {
        return film_location;
    }

    public void setFilm_location(String film_location) {
        this.film_location = film_location;
    }

    public String getFilm_web_site() {
        return film_web_site;
    }

    public void setFilm_web_site(String film_web_site) {
        this.film_web_site = film_web_site;
    }

    public String getFilm_month_day() {
        return film_month_day;
    }

    public void setFilm_month_day(String film_month_day) {
        this.film_month_day = film_month_day;
    }

    public String getFilm_time() {
        return film_time;
    }

    public void setFilm_time(String film_time) {
        this.film_time = film_time;
    }

    public String getFilm_address() {
        return film_address;
    }

    public void setFilm_address(String film_address) {
        this.film_address = film_address;
    }

    public String getFilm_price() {
        return film_price;
    }

    public void setFilm_price(String film_price) {
        this.film_price = film_price;
    }

    public String getFilm_tm_muse_percent() {
        return film_tm_muse_percent;
    }

    public void setFilm_tm_muse_percent(String film_tm_muse_percent) {
        this.film_tm_muse_percent = film_tm_muse_percent;
    }
}
