package geek.space.tmmuse.Activity.TermsOfUse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;

public class Terms_of_use extends AppCompatActivity {
    private TextView terms_use_desc, terms_use_txt;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);
        initUnits();
        setFonts();
    }

    private void setFonts() {
        terms_use_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        terms_use_desc.setTypeface(Font.getInstance(context).getMontserrat_400());
    }

    private void initUnits() {
        terms_use_txt = findViewById(R.id.terms_use_txt);
        terms_use_desc = findViewById(R.id.terms_use_desc);
    }


    public void on_back_pressed(View view) {
        onBackPressed();
        finish();
    }
}