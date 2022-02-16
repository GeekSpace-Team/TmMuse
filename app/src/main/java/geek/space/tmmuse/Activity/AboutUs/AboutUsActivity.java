package geek.space.tmmuse.Activity.AboutUs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.AboutUs.Constant;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {
    private TextView about_txt;
    private WebView web_view_const;
    private Context context = this;
    private ApiInterface apiInterface;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        type = getIntent().getStringExtra("page_type");
        initComponents();
        setFonts();
        setWebViewListener(type);
        getLang();
    }

    private void setWebViewListener(String typeConstant) {
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<Constant> constantCall = apiInterface.get_constant(typeConstant);
        constantCall.enqueue(new Callback<Constant>() {
            @Override
            public void onResponse(Call<Constant> call, Response<Constant> response) {
                if (response.isSuccessful() || response.body() != null) {

                    web_view_const.loadData(response.body().getBody().get(0).getContentTM(),
                            "text/html",
                            "UTF-8");

                    about_txt.setText(response.body().getBody().get(0).getTitleTM());
                    Toast.makeText(context, Utils.getLanguage(context), Toast.LENGTH_SHORT).show();
                    if (Utils.getLanguage(context).equals("ru")) {
                        web_view_const.loadData(response.body().getBody().get(0).getContentRU(),
                                "text/html",
                                "UTF-8");
                        about_txt.setText(response.body().getBody().get(0).getTitleRU());
                    }
                } else {
                    Toast.makeText(context, "Yalnyslyk yuze cykdy", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Constant> call, Throwable t) {
                Toast.makeText(context, "Internedinizi barlan", Toast.LENGTH_SHORT).show();
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