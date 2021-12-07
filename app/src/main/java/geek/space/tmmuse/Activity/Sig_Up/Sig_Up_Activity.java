package geek.space.tmmuse.Activity.Sig_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import geek.space.tmmuse.Activity.Interest.Interest_Activity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class Sig_Up_Activity extends AppCompatActivity {
    private TextView sgn_up_txt, skip_txt, sign_up_desc, ful_name_txt, number_txt, sms_code_txt,numberBefore;
    private EditText full_name_edit, number_edit, edit_code_one, edit_code_two, edit_code_three, edit_code_four;
    private NeumorphButton save_btn;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig__up_);

        initComponents();
        setFonts();
        getCodePhoneNumber();
        setListener();
    }

    private void setListener() {
        save_btn.setOnClickListener(view -> {
            if (edit_code_one.length() == 0 | edit_code_two.length() == 0 | edit_code_three.length() == 0 | edit_code_four.length() == 0) {
                Toast.makeText(getApplicationContext(), "The code was entered incorrectly", Toast.LENGTH_SHORT).show();
            } else {
                Utils.setPressedSendSave(save_btn, 2, R.color.aply_text_color, R.color.card_background, context);
                startActivity(new Intent(getApplicationContext(), Interest_Activity.class));
                finish();
            }
        });
        skip_txt.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Interest_Activity.class));
            finish();
        });
    }

    private void getCodePhoneNumber() {
        EditText[] edit = {edit_code_one, edit_code_two, edit_code_three, edit_code_four};

        edit_code_one.addTextChangedListener(new GenericTextWatcher(edit_code_one, edit));
        edit_code_two.addTextChangedListener(new GenericTextWatcher(edit_code_two, edit));
        edit_code_three.addTextChangedListener(new GenericTextWatcher(edit_code_three, edit));
        edit_code_four.addTextChangedListener(new GenericTextWatcher(edit_code_four, edit));

    }

    private void setFonts() {
        sgn_up_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        skip_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
        sign_up_desc.setTypeface(Font.getInstance(this).getMontserrat_400());
        ful_name_txt.setTypeface(Font.getInstance(this).getMontserrat_400());
        number_txt.setTypeface(Font.getInstance(this).getMontserrat_400());
        sms_code_txt.setTypeface(Font.getInstance(this).getMontserrat_400());

        full_name_edit.setTypeface(Font.getInstance(this).getMontserrat_600());
        number_edit.setTypeface(Font.getInstance(this).getMontserrat_600());
        edit_code_one.setTypeface(Font.getInstance(this).getMontserrat_400());
        edit_code_two.setTypeface(Font.getInstance(this).getMontserrat_400());
        edit_code_three.setTypeface(Font.getInstance(this).getMontserrat_400());
        edit_code_four.setTypeface(Font.getInstance(this).getMontserrat_400());

        save_btn.setTypeface(Font.getInstance(this).getMontserrat_700());
        numberBefore.setTypeface(Font.getInstance(this).getMontserrat_600());


    }

    private void initComponents() {
        sgn_up_txt = findViewById(R.id.sgn_up_txt);
        skip_txt = findViewById(R.id.skip_txt);
        sign_up_desc = findViewById(R.id.sign_up_desc);
        ful_name_txt = findViewById(R.id.ful_name_txt);
        number_txt = findViewById(R.id.number_txt);
        sms_code_txt = findViewById(R.id.sms_code_txt);

        full_name_edit = findViewById(R.id.full_name_edit);
        number_edit = findViewById(R.id.number_edit);
        edit_code_one = findViewById(R.id.edit_code_one);
        edit_code_two = findViewById(R.id.edit_code_two);
        edit_code_three = findViewById(R.id.edit_code_three);
        edit_code_four = findViewById(R.id.edit_code_four);
        numberBefore = findViewById(R.id.numberBefore);

        save_btn = findViewById(R.id.save_btn);
    }



}