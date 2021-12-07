package geek.space.tmmuse.Activity.AboutUs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;

public class AboutUsActivity extends AppCompatActivity {
    private TextView about_us_desc, about_us_txt;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initComponents();
        setFonts();
    }

    private void setFonts() {
        about_us_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        about_us_desc.setTypeface(Font.getInstance(context).getMontserrat_400());
    }

    private void initComponents() {
        about_us_txt = findViewById(R.id.about_us_txt);
        about_us_desc = findViewById(R.id.about_us_desc);

    }

    public void on_back_pressed(View view) {
        onBackPressed();
        finish();
    }
}