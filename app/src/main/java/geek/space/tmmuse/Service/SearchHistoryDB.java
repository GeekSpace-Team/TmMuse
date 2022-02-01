package geek.space.tmmuse.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import geek.space.tmmuse.Model.SearchHistory.SearchHistory;

public class SearchHistoryDB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DBNAME = "searchHistoryDb";
    private static final String TBNAME = "searchHistoryTable";
    private static final String KEY_ID = "id";
    private static final String SEARCH_HISTORY = "searchHistory";

    public SearchHistoryDB(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_TABLE = "CREATE TABLE " + TBNAME +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SEARCH_HISTORY + " TEXT" + ");";
        db.execSQL(QUERY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBNAME);
        onCreate(db);
    }

    public boolean insertData(SearchHistory searchHistory) {
        Cursor select=getSelect(searchHistory.getSearch_history_txt());
        if(select.getCount()>0){
            return true;
        }
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("searchHistory", searchHistory.getSearch_history_txt());
        db.insert(TBNAME, null, values);
        db.close();
         return true;
    }

    public Cursor getAll() {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME, null);
        return cursor;
    }

    public Cursor getSelect(String searchHistory) {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBNAME + " WHERE searchHistory = '" + searchHistory + "'", null);
        return cursor;
    }

    public boolean updateData(String id, String searchHistory) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SEARCH_HISTORY, searchHistory);
        db.update(TBNAME, values, "id=?", new String[]{id});
        return true;
    }


    public Integer deleteData(String searchHistory) {
        db = this.getWritableDatabase();
        return db.delete(TBNAME, "searchHistory=?", new String[]{searchHistory});

    }

    public void truncate() {
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TBNAME);
        onCreate(db);
    }
}
