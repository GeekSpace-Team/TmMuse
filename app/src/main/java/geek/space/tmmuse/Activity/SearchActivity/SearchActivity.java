package geek.space.tmmuse.Activity.SearchActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Adapter.AllProdileAdapters.AllProdfileAdapters;
import geek.space.tmmuse.Adapter.SearchHistoryAdapter.SearchHistoryAdapter;
import geek.space.tmmuse.Adapter.SearchPageAdapter.SearchKeyWordAdapter;
import geek.space.tmmuse.Adapter.SearchPageAdapter.SearchPageAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.Model.SearchHistory.GetSearchHistory;
import geek.space.tmmuse.Model.SearchHistory.SearchHistory;
import geek.space.tmmuse.Model.SearchPage.PostSearchProfile;
import geek.space.tmmuse.Model.SearchPage.SearchPage;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.SearchHistoryDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private EditText full_name_edit;
    private TextView top_key_word_txt, search_result_txt, search_history_txt, delete_all_search_txt;
    private ImageView clear_search_img, search_img;
    private RecyclerView search_rec, top_key_words_rec, search_history_rec;
    private SearchPageAdapter searchPageAdapter;
    private SearchKeyWordAdapter searchKeyWordAdapter;
    private ArrayList<SearchPage> searchPages = new ArrayList<>();
    private ArrayList<SearchHistory> searchKeyWords = new ArrayList<>();
    private ArrayList<SearchHistory> searchHistories = new ArrayList<>();
    private SearchHistoryAdapter searchHistoryAdapter;
    private LinearLayout search_history_layout, search_result_layout;
    private SearchHistoryDB searchHistoryDB;
    private LinearLayout delete_all_search_layout;
    private ApiInterface apiInterface;
    public static Integer limit = 4;
    public static Integer page = 1;
    public static ArrayList<AllProfile> allProfiles = new ArrayList<>();
    private LinearLayout error_search_layout;
    private ProgressBar search_progress;
    private boolean isLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        setListeners();
        setFont();
        getLang();
        setSearchList();

        setSearchResultAdapter();
        setSearchListHistory();
        setSearchResultList(page);

    }

    private void setSearchListHistory() {
        searchHistories.clear();
        Cursor cursor = searchHistoryDB.getAll();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                searchHistories.add(new SearchHistory(1, 1, cursor.getString(1)));
            }
            searchHistoryAdapter = new SearchHistoryAdapter(this, searchHistories, full_name_edit);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            search_history_rec.setLayoutManager(layoutManager);
            search_history_rec.setAdapter(searchHistoryAdapter);
        }
    }


    private void setSearchResultAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        search_rec.setLayoutManager(layoutManager);
        AllProdfileAdapters allProdfileAdapters = new AllProdfileAdapters(this, allProfiles);
        search_rec.setAdapter(allProdfileAdapters);
    }

    private void setSearchResultList(int i) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        isLoading=true;
        search_progress.setVisibility(View.VISIBLE);
        Call<GetSearchHistory> getSearchHistoryCall = apiInterface.get_search_history();
        getSearchHistoryCall.enqueue(new Callback<GetSearchHistory>() {
            @Override
            public void onResponse(Call<GetSearchHistory> call, Response<GetSearchHistory> response) {
                if (response.body().getBody() != null) {
                    searchKeyWords = response.body().getBody();
                    search_rec.setVisibility(View.VISIBLE);
                    error_search_layout.setVisibility(View.GONE);
                    top_key_words_rec.setVisibility(View.VISIBLE);
                    search_result_layout.setVisibility(View.VISIBLE);
                    search_history_layout.setVisibility(View.GONE);
                    search_progress.setVisibility(View.GONE);
                    isLoading=false;
                    setSearchAdapter();
                } else {
                    search_rec.setVisibility(View.GONE);
                    error_search_layout.setVisibility(View.VISIBLE);
                    top_key_words_rec.setVisibility(View.VISIBLE);
                    search_result_layout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GetSearchHistory> call, Throwable t) {
                search_result_layout.setVisibility(View.GONE);
                search_history_layout.setVisibility(View.VISIBLE);
                search_progress.setVisibility(View.GONE);
                isLoading=false;
            }
        });

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
        Log.e("1. ", searchKeyWords + "");
    }


    private void setListeners() {
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSearch(full_name_edit.getText().toString());
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

        full_name_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    getSearch(full_name_edit.getText().toString());
                    return true;
                }
                return false;
            }
        });
        full_name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search_history_layout.setVisibility(View.VISIBLE);
                search_result_layout.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                clear_search_img.setVisibility(View.VISIBLE);
                clear_search_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        full_name_edit.setText("");
                    }
                });
            }
        });

        search_history_rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)){
                    if (!isLoading){
                        loadMore();
                    }
                }
            }
        });

    }

    private void getSearch(String toString) {
        if (toString.trim().isEmpty()) {
            return;
        }
        if (page == 1) {
            allProfiles.clear();
        }
        saveSearchHistory();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        SearchHistory query = new SearchHistory(page, limit, toString.toLowerCase());
        Call<PostSearchProfile> call = apiInterface.search_profile(query);
        call.enqueue(new Callback<PostSearchProfile>() {
            @Override
            public void onResponse(Call<PostSearchProfile> call, Response<PostSearchProfile> response) {
                if (response.isSuccessful()) {
                    Log.e("Res", new Gson().toJson(response.body()));
                    if (response.body().getBody() != null) {
                        allProfiles.addAll(response.body().getBody());
                        if (page == 1)
                            setSearchResultAdapter();
                        else
                            search_rec.getAdapter().notifyDataSetChanged();

                        search_result_layout.setVisibility(View.VISIBLE);
                        error_search_layout.setVisibility(View.GONE);
                    } else {
                        search_result_layout.setVisibility(View.GONE);
                        error_search_layout.setVisibility(View.VISIBLE);
                    }

                } else {
                    Toast.makeText(SearchActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostSearchProfile> call, Throwable t) {
                Log.e("Search error", t.getMessage());
                Toast.makeText(SearchActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        full_name_edit = findViewById(R.id.full_name_edit);
        search_progress = findViewById(R.id.search_progress);
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
        error_search_layout = findViewById(R.id.error_search_layout);
        search_img = findViewById(R.id.search_img);
        searchHistoryDB = new SearchHistoryDB(this);
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

    public void saveSearchHistory() {
        searchPages.clear();
        setSearchResultList(page);
        SearchHistory result = new SearchHistory(1, 1, full_name_edit.getText().toString());
        if (searchHistoryDB.insertData(result)) {
        } else {
        }

    }


    private void loadMore() {
        page++;
        setSearchResultList(page);
    }
}