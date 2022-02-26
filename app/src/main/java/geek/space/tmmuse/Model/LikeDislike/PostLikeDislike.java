package geek.space.tmmuse.Model.LikeDislike;

public class PostLikeDislike {
    private Integer id;
    private String column_type;
    private String table_type;

    public PostLikeDislike(Integer id, String column_type, String table_type) {
        this.id = id;
        this.column_type = column_type;
        this.table_type = table_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColumn_type() {
        return column_type;
    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public String getTable_type() {
        return table_type;
    }

    public void setTable_type(String table_type) {
        this.table_type = table_type;
    }
}
