package geek.space.tmmuse.Model.Film;

public class Film {
   private int id;
   private String nameTM;
   private String nameRU;
   private String short_descTM;
   private String short_descRU;
   private String small_image;

    public Film(int id, String nameTM, String nameRU, String short_descTM, String short_descRU, String small_image) {
        this.id = id;
        this.nameTM = nameTM;
        this.nameRU = nameRU;
        this.short_descTM = short_descTM;
        this.short_descRU = short_descRU;
        this.small_image = small_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTM() {
        return nameTM;
    }

    public void setNameTM(String nameTM) {
        this.nameTM = nameTM;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public String getShort_descTM() {
        return short_descTM;
    }

    public void setShort_descTM(String short_descTM) {
        this.short_descTM = short_descTM;
    }

    public String getShort_descRU() {
        return short_descRU;
    }

    public void setShort_descRU(String short_descRU) {
        this.short_descRU = short_descRU;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }
}
