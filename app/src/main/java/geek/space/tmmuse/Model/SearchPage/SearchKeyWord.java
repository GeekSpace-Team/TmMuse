package geek.space.tmmuse.Model.SearchPage;

public class SearchKeyWord {
    private int id;
    private String key_word;

    public SearchKeyWord(int id, String key_word) {
        this.id = id;
        this.key_word = key_word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }
}
