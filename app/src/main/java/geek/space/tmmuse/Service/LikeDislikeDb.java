package geek.space.tmmuse.Service;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class LikeDislikeDb extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DBNAME = "like_dislike_Db";
    private static final String TBNAME = "like_dislike_Table";
    private static final String ID = "id";
    private static final String PROFILE_ID = "profile_id";
    private static final String TYPE = "type";
    public static final String LIKE = "like";
    public static final String DISLIKE = "dislike";

    public LikeDislikeDb(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_TABLE = "CREATE TABLE " + TBNAME +
                "(" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROFILE_ID + " TEXT,"
                + TYPE + " TEXT"+ ");";
        db.execSQL(QUERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBNAME);
        onCreate(db);
    }

    public boolean insert(Integer p_id,String type) {
        Cursor select = getCount(p_id,type);
        if(select.getCount()>0){
            return true;
        }
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_ID, p_id+"");
        values.put(TYPE, type);
        db.insert(TBNAME, null, values);
        db.close();
        return true;
    }


    public Cursor getAll() {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME +" ORDER BY " + ID + " DESC LIMIT 1000", null);
        return cursor;
    }

    public Cursor getCount(Integer p_id, String type) {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME + " WHERE "+PROFILE_ID+" = '" + p_id + "' and "+TYPE+"='"+type+"'", null);
        if(type.equals(LIKE)){
            Integer res=delete(p_id,DISLIKE);
        } else if(type.equals(DISLIKE)){
            delete(p_id,LIKE);
        }
        return cursor;
    }

    public Cursor getCountFirst(String p_id,String type) {
        db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TBNAME + " WHERE "+PROFILE_ID+" = '" + p_id + "' and "+TYPE+"='"+type+"'", null);
    }

    public Integer delete(Integer p_id, String type) {
        db = this.getWritableDatabase();
        return db.delete(TBNAME, PROFILE_ID+"=? and "+TYPE+"=?", new String[]{p_id+"",type});

    }

}
