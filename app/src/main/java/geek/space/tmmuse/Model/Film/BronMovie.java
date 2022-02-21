package geek.space.tmmuse.Model.Film;

public class BronMovie {
    private Integer cinema_id;
    private Integer profile_id;
    private Integer user_id;
    private String movie_date;
    private String movie_time;
    private Integer ticket_count;
    private Double ticket_price;
    private Double ticket_discount;

    public BronMovie(Integer cinema_id, Integer profile_id, Integer user_id, String movie_date, String movie_time, Integer ticket_count, Double ticket_price, Double ticket_discount) {
        this.cinema_id = cinema_id;
        this.profile_id = profile_id;
        this.user_id = user_id;
        this.movie_date = movie_date;
        this.movie_time = movie_time;
        this.ticket_count = ticket_count;
        this.ticket_price = ticket_price;
        this.ticket_discount = ticket_discount;
    }

    public Integer getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Integer cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }

    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
    }

    public Double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Double getTicket_discount() {
        return ticket_discount;
    }

    public void setTicket_discount(Double ticket_discount) {
        this.ticket_discount = ticket_discount;
    }
}
