package geek.space.tmmuse.Activity.Interest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;

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
import geek.space.tmmuse.Model.UserRegister.StringResponse;
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
    private Context context=this;

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
        KProgressHUD progress=Utils.AppProgressBar(context);
        progress.setLabel(getResources().getString(R.string.wait));
        progress.show();
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
                    Utils.showCustomToast(getApplicationContext().getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            getApplicationContext(),
                            R.color.no_internet_back);
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<GetInterest> call, Throwable t) {
                Utils.showCustomToast(getApplicationContext().getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        getApplicationContext(),
                        R.color.no_internet_back);
                progress.dismiss();
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
        if(selectedInterests.size()<=0){
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        next_txt.setVisibility(View.GONE);
        KProgressHUD progress=Utils.AppProgressBar(context);
        progress.setLabel(getResources().getString(R.string.wait));
        progress.show();
        String token="Bearer "+ Utils.getSharePreferences(this,"token");
        apiClient = ApiClient.getClient()
                .create(ApiInterface.class);
        PostInterest postInterest = new PostInterest(Utils.getSharePreferences(this, "user_id"), selectedInterests);
        Call<StringResponse> postInterestCall = apiClient.add_user_interest(postInterest, token);
        postInterestCall.enqueue(new Callback<StringResponse>() {
            @Override
            public void onResponse(Call<StringResponse> call, Response<StringResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    progressBar.setVisibility(View.GONE);
                    next_txt.setVisibility(View.VISIBLE);
                    if (getIntent().getStringExtra("type").equals("0"))
                        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                    finish();
                } else {
                    Utils.showCustomToast(getApplicationContext().getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            context,
                            R.color.no_internet_back);
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<StringResponse> call, Throwable t) {
                Utils.showCustomToast(getApplicationContext().getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
            }
        });
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

}