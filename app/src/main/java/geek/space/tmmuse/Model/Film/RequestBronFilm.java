package geek.space.tmmuse.Model.Film;

public class RequestBronFilm {
    private Boolean error;
    private Inserted body;

    public RequestBronFilm(Boolean error, Inserted body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Inserted getBody() {
        return body;
    }

    public void setBody(Inserted body) {
        this.body = body;
    }

    class Inserted{
        private Integer INSERTED_ID;

        public Inserted(Integer INSERTED_ID) {
            this.INSERTED_ID = INSERTED_ID;
        }

        public Integer getINSERTED_ID() {
            return INSERTED_ID;
        }

        public void setINSERTED_ID(Integer INSERTED_ID) {
            this.INSERTED_ID = INSERTED_ID;
        }
    }
}
