package geek.space.tmmuse.Activity.SearchActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Adapter.SearchHistoryAdapter.SearchHistoryAdapter;
import geek.space.tmmuse.Adapter.SearchPageAdapter.SearchKeyWordAdapter;
import geek.space.tmmuse.Adapter.SearchPageAdapter.SearchPageAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.SearchHistory.SearchHistory;
import geek.space.tmmuse.Model.SearchPage.SearchKeyWord;
import geek.space.tmmuse.Model.SearchPage.SearchPage;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.SearchHistoryDB;

public class SearchActivity extends AppCompatActivity {
    private EditText full_name_edit;
    private TextView top_key_word_txt, search_result_txt, search_history_txt, delete_all_search_txt;
    private ImageView clear_search_img,search_img;
    private RecyclerView search_rec, top_key_words_rec, search_history_rec;
    private SearchPageAdapter searchPageAdapter;
    private SearchKeyWordAdapter searchKeyWordAdapter;
    private ArrayList<SearchPage> searchPages = new ArrayList<>();
    private ArrayList<SearchKeyWord> searchKeyWords = new ArrayList<>();
    private ArrayList<SearchHistory> searchHistories = new ArrayList<>();
    private SearchHistoryAdapter searchHistoryAdapter;
    private LinearLayout search_history_layout, search_result_layout;
    private SearchHistoryDB searchHistoryDB;
    private LinearLayout delete_all_search_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        setListeners();
        setFont();
        getLang();
        setSearchList();
        setSearchAdapter();
        setSearchResultAdapter();
        setSearchListHistory();

    }

    private void setSearchListHistory() {
        searchHistories.clear();
        Cursor cursor = searchHistoryDB.getAll();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                searchHistories.add(new SearchHistory(cursor.getString(1)));
            }
            searchHistoryAdapter = new SearchHistoryAdapter(this, searchHistories, full_name_edit);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            search_history_rec.setLayoutManager(layoutManager);
            search_history_rec.setAdapter(searchHistoryAdapter);
        }
    }


    private void setSearchResultAdapter() {
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        search_rec.setLayoutManager(layoutManager2);
        searchPageAdapter = new SearchPageAdapter(this, searchPages);
        search_rec.setAdapter(searchPageAdapter);
    }

    private void setSearchResultList(String query) {
        searchPages.clear();
        searchPages.add(new SearchPage(1, "Zenan", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));
        searchPages.add(new SearchPage(2, "Zenan1", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));
        searchPages.add(new SearchPage(3, "Zenan2", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));
        searchPages.add(new SearchPage(4, "Zenan3", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));
        searchPages.add(new SearchPage(5, "Zenan4", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));
        searchPages.add(new SearchPage(6, "Zenan5", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));
        searchPages.add(new SearchPage(7, "Zenan6", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "", "", ""));

    }

    private void setFont() {
        full_name_edit.setTypeface(Font.getInstance(this).getMontserrat_400());
        top_key_word_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        search_result_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        search_history_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        delete_all_search_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
    }

    private void setSearchAdapter() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        top_key_words_rec.setLayoutManager(llm);
        searchKeyWordAdapter = new SearchKeyWordAdapter(this, searchKeyWords, full_name_edit);
        top_key_words_rec.setAdapter(searchKeyWordAdapter);
    }

    private void setSearchList() {
        searchKeyWords.clear();
        searchKeyWords.add(new SearchKeyWord(1, "Woman"));
        searchKeyWords.add(new SearchKeyWord(2, "Man"));
        searchKeyWords.add(new SearchKeyWord(3, "Child"));
        searchKeyWords.add(new SearchKeyWord(4, "Sport"));
        searchKeyWords.add(new SearchKeyWord(5, "Kaffe"));
        searchKeyWords.add(new SearchKeyWord(6, "Restourants"));
        Log.e("1. ", searchKeyWords + "");
    }


    private void setListeners() {
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPages.clear();
                setSearchResultList(full_name_edit.getText().toString());
                SearchHistory result = new SearchHistory( full_name_edit.getText().toString());
                if (searchHistoryDB.insertData(result)) {
                } else {
                }
            }
        });
        full_name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                clear_search_img.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                clear_search_img.setVisibility(View.VISIBLE);
                clear_search_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        full_name_edit.getText().clear();
                    }
                });
            }
        });
        delete_all_search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchHistoryDB.truncate();
                searchHistories.clear();
                search_history_rec.getAdapter()
                        .notifyDataSetChanged();
            }
        });

    }

    private void initViews() {
        full_name_edit = findViewById(R.id.full_name_edit);
        clear_search_img = findViewById(R.id.clear_search_img);
        search_rec = findViewById(R.id.search_rec);
        top_key_words_rec = findViewById(R.id.top_key_words_rec);
        top_key_word_txt = findViewById(R.id.top_key_word_txt);
        search_result_txt = findViewById(R.id.search_result_txt);
        search_history_layout = findViewById(R.id.search_history_layout);
        search_result_layout = findViewById(R.id.search_result_layout);
        search_history_rec = findViewById(R.id.search_history_rec);
        search_history_txt = findViewById(R.id.search_history_txt);
        delete_all_search_layout = findViewById(R.id.delete_all_search_layout);
        delete_all_search_txt = findViewById(R.id.delete_all_search_txt);
        search_img = findViewById(R.id.search_img);
        searchHistoryDB = new SearchHistoryDB(this);
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }
}