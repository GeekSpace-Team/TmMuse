package geek.space.tmmuse.Activity.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.InterestPage.InterestAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Interest.GetInterest;
import geek.space.tmmuse.Model.Interest.Interest;
import geek.space.tmmuse.Model.Interest.PostInterest;
import geek.space.tmmuse.Model.Interest.SubInterest;
import geek.space.tmmuse.Model.UserRegister.UserGetRegister;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interest_Activity extends AppCompatActivity {
    private TextView select_int_txt, next_txt;
    private ArrayList<Interest> interests = new ArrayList<>();
    private ArrayList<SubInterest> subInterests1= new ArrayList<>();
    private ArrayList<SubInterest> subInterests2= new ArrayList<>();
    private ArrayList<SubInterest> subInterests3= new ArrayList<>();
    private ArrayList<SubInterest> subInterests4= new ArrayList<>();
    private ArrayList<SubInterest> subInterests5= new ArrayList<>();
    private ArrayList<SubInterest> subInterests6= new ArrayList<>();
    private InterestAdapter interestAdapter;
    private RecyclerView interestRec;
    public static ArrayList<Integer> selectedInterests=new ArrayList<>();
    private ApiInterface apiClient;
    private ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        initComponents();
        setFont();
        getLang();
        getAllInterst();
    }

    private void getAllInterst() {
        String token="Bearer "+ Utils.getSharePreferences(this,"token");
        apiClient = ApiClient.getClient()
                .create(ApiInterface.class);
        Call<GetInterest> getInterestCall = apiClient.get_interest(token);
        getInterestCall.enqueue(new Callback<GetInterest>() {
            @Override
            public void onResponse(Call<GetInterest> call, Response<GetInterest> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    GetInterest getInterest = response.body();
                    interests=getInterest.getBody();
                    setIneterstAdapter();
                } else {
                    Toast.makeText(Interest_Activity.this, "Yalnyslyk yuze cykdy", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetInterest> call, Throwable t) {
                Toast.makeText(Interest_Activity.this, "Internedinizi barlan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setIneterstAdapter() {
        interestAdapter = new InterestAdapter(this, interests);
        interestRec.setLayoutManager(new LinearLayoutManager(this));
        interestRec.setAdapter(interestAdapter);

    }


    private void setFont() {
        select_int_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        select_int_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
    }

    private void initComponents() {
        select_int_txt = findViewById(R.id.select_int_txt);
        next_txt = findViewById(R.id.next_txt);
        interestRec = findViewById(R.id.interestRec);
        progressBar = findViewById(R.id.progress_bar);
    }


    public void go_main_menu(View view) {
        progressBar.setVisibility(View.VISIBLE);
        next_txt.setVisibility(View.GONE);
        String token="Bearer "+ Utils.getSharePreferences(this,"token");
        apiClient = ApiClient.getClient()
                .create(ApiInterface.class);
        PostInterest postInterest = new PostInterest(Utils.getSharePreferences(this, "user_id"), selectedInterests);
        Call<UserGetRegister> postInterestCall = apiClient.add_user_interest(postInterest, token);
        postInterestCall.enqueue(new Callback<UserGetRegister>() {
            @Override
            public void onResponse(Call<UserGetRegister> call, Response<UserGetRegister> response) {
                if (response.isSuccessful() && response.body()!=null){
                    progressBar.setVisibility(View.GONE);
                    next_txt.setVisibility(View.VISIBLE);
                    if (getIntent().getStringExtra("type").equals("0"))
                        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                    finish();
                } else {
                    Toast.makeText(Interest_Activity.this, "Yalnyslyk yuze cykdy", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserGetRegister> call, Throwable t) {
                Toast.makeText(Interest_Activity.this, "Internedinizi barlan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

}