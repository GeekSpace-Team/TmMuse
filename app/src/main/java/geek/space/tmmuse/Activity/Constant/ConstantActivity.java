package geek.space.tmmuse.Activity.Constant;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.kaopiz.kprogresshud.KProgressHUD;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Constant.Constant;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstantActivity extends AppCompatActivity {
    private TextView about_txt;
    private WebView web_view_const;
    private Context context = this;
    private ApiInterface apiInterface;
    private String type = "";
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        type = getIntent().getStringExtra("page_type");
        sharedPref=new SharedPref(this);
        initComponents();
        setFonts();
        setWebViewListener(type);
        getLang();

    }

    private void setWebViewListener(String typeConstant) {
        KProgressHUD progress = Utils.AppProgressBar(ConstantActivity.this);
        progress.setLabel(getResources().getString(R.string.wait));
        progress.show();
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<Constant> constantCall = apiInterface.get_constant(typeConstant);
        constantCall.enqueue(new Callback<Constant>() {
            @Override
            public void onResponse(Call<Constant> call, Response<Constant> response) {
                if (response.isSuccessful() || response.body() != null) {
                    String webHTML = "";
                    if (sharedPref.loadNightModeState()) {
                        about_txt.setText(response.body().getBody().get(0).getTitleTM());
                        webHTML=response.body().getBody().get(0).getContentTM_dark();
                        if(Utils.getLanguage(context).equals("ru")) {
                            webHTML = response.body().getBody().get(0).getContentRU_dark();
                            about_txt.setText(response.body().getBody().get(0).getTitleRU());
                        }

                    } else {
                        about_txt.setText(response.body().getBody().get(0).getTitleTM());
                        webHTML=response.body().getBody().get(0).getContentTM();
                        if(Utils.getLanguage(context).equals("ru")) {
                            webHTML = response.body().getBody().get(0).getContentRU();
                            about_txt.setText(response.body().getBody().get(0).getTitleRU());

                        }
                    }
                    web_view_const.loadData(webHTML,
                            "text/html",
                            "UTF-8");
                } else {
                    Utils.showCustomToast(getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            ConstantActivity.this,
                            R.color.no_internet_back);
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<Constant> call, Throwable t) {
                Utils.showCustomToast(getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        ConstantActivity.this,
                        R.color.no_internet_back);
                progress.dismiss();
            }
        });
    }

    private void setFonts() {
        web_view_const.setBackgroundColor(0);
        about_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
    }

    private void initComponents() {
        web_view_const = findViewById(R.id.web_view_const);
        about_txt = findViewById(R.id.about_txt);
    }

    public void on_back_pressed(View view) {
        onBackPressed();
        finish();
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }
}