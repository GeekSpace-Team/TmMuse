package geek.space.tmmuse.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.Model.SearchHistory.SearchHistory;

public class LikeDislikeDb extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DBNAME = "like_dislike_Db";
    private static final String TBNAME = "like_dislike_Table";
    private static final String ID = "id";
    private static final String CATEGORY_ID_LIKE = "category_id_like";
    private static final String CATEGORY_ID_DISLIKE = "category_id_dis_like";

    public LikeDislikeDb(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_TABLE = "CREATE TABLE " + TBNAME +
                "(" +ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORY_ID_LIKE + " TEXT,"
                + CATEGORY_ID_DISLIKE + " TEXT"+ ");";
        db.execSQL(QUERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBNAME);
        onCreate(db);
    }

    public boolean category_id_dis_like_insertData(AllProfile allProfile) {
        Cursor select = category_id_dislike_getSelect(allProfile.getId() + "");
        if(select.getCount()>0){
            return true;
        }
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category_id_dis_like", allProfile.getId());
        db.insert(TBNAME, null, values);
        db.close();
        return true;
    }

    public boolean category_id_like_getSelect(AllProfile allProfile) {
        Cursor select = category_id_like_getSelect(allProfile.getId() + "");
        if(select.getCount()>0){
            return true;
        }
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category_id_like", allProfile.getId());
        db.insert(TBNAME, null, values);
        db.close();
        return true;
    }


    public Cursor getAll() {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME +" ORDER BY " + ID + " DESC LIMIT 1000", null);
        return cursor;
    }

    public Cursor category_id_like_getSelect(String category_id_like) {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME + " WHERE category_id_like = '" + category_id_like + "'", null);
        return cursor;
    }

    public Cursor category_id_dislike_getSelect(String category_id_dislike) {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME + " WHERE category_id_dislike = '" + category_id_dislike + "'", null);
        return cursor;
    }

    public Integer category_id_dislike_deleteData(String searchHistory) {
        db = this.getWritableDatabase();
        return db.delete(TBNAME, CATEGORY_ID_DISLIKE +"=?", new String[]{searchHistory});

    }

    public Integer category_id_like_deleteData(String searchHistory) {
        db = this.getWritableDatabase();
        return db.delete(TBNAME, CATEGORY_ID_LIKE+"=?", new String[]{searchHistory});

    }

}
