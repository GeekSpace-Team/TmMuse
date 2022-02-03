package geek.space.tmmuse.Activity.GetCard;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class GetCardActivity extends AppCompatActivity {

    private TextView help_txt, full_name_txt, number_txt, numberBefore, day_of_bth_txt, gender_txt, email_txt, reciv_about_card_txt;
    private RadioGroup gender_group, sms_or_email_group;
    private RadioButton male_btn, female_btn, sms_btn, email_btn;
    private EditText full_name_edit, number_edit, day_birth_edit, passport_edit, email_edit;
    private CheckBox accept_tex_box;
    private NeumorphButton send_btn;
    private List<String> job_lists;
    private String jynsy = "";
    private String take_info_card = "";
    private NeumorphCardView male_card, female_card, sms_card, email_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_card);
        initComponents();
        setListener();
        setFonts();
        getLang();

    }



    private void setListener() {

        day_birth_edit.setInputType(InputType.TYPE_NULL);
        day_birth_edit.setOnClickListener(view -> {

            final Calendar cldr = Calendar.getInstance();

            BottomSheetDialog bsDialog = new BottomSheetDialog(GetCardActivity.this, R.style.CustomBottomSheetDialogTheme);
            View bottomSheetDialogView = LayoutInflater.from(GetCardActivity.this)
                    .inflate(
                            R.layout.bottom_sheet_calendar,
                            view.findViewById(R.id.calendar_rel));
            DatePicker calendar_picker = bottomSheetDialogView.findViewById(R.id.calendar_picker);
            calendar_picker.init(cldr.get(Calendar.DAY_OF_MONTH), cldr.get(Calendar.MONTH), cldr.get(Calendar.YEAR), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    day_birth_edit.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            });
            bsDialog.setContentView(bottomSheetDialogView);
            bsDialog.show();


        });

        male_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                male_card.setShapeType(2);
                female_card.setShapeType(0);
                female_btn.setChecked(false);
                jynsy=male_btn.getText().toString();
            }
        });
        female_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                male_card.setShapeType(0);
                female_card.setShapeType(2);
                male_btn.setChecked(false);
                jynsy=female_btn.getText().toString();
            }
        });

        sms_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                sms_card.setShapeType(2);
                email_card.setShapeType(0);
                email_btn.setChecked(false);
                take_info_card = sms_btn.getText().toString();
            }
        });
        email_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                sms_card.setShapeType(0);
                email_card.setShapeType(2);
                sms_btn.setChecked(false);
                take_info_card = email_btn.getText().toString();
            }
        });

        send_btn.setOnClickListener(view -> {
            if (full_name_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, "Напишите имя", Toast.LENGTH_SHORT).show();
            } else if (number_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, "Напишите номер телефона", Toast.LENGTH_SHORT).show();
            } else if (day_birth_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, "Задайте пожалуйста день рождение", Toast.LENGTH_SHORT).show();
            } else if (jynsy.equals("")) {
                Toast.makeText(GetCardActivity.this, "Выборите пол", Toast.LENGTH_SHORT).show();
            } else if (passport_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, "Напишите паспортные данные", Toast.LENGTH_SHORT).show();
            } else if (email_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, "Напишите email", Toast.LENGTH_SHORT).show();
            } else if (!accept_tex_box.isChecked()) {
                Toast.makeText(GetCardActivity.this, "Вы должны соглашаться что бы отправить данные", Toast.LENGTH_SHORT).show();
            } else if (take_info_card.equals("")) {
                Toast.makeText(GetCardActivity.this, "Выборети пожалуйста как вы хотите получить информацию о карте", Toast.LENGTH_SHORT).show();
            } else {
            String to = "wolfdog31051993@gmail.com";
            String full_name_edit1 = full_name_edit.getText().toString();
            String number_edit1 = number_edit.getText().toString();
            String day_birth_edit1 = day_birth_edit.getText().toString();
            String passport_edit1 = passport_edit.getText().toString();
            String jynsy_user = jynsy;
            String info_card_ = take_info_card;
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            email.putExtra(Intent.EXTRA_TEXT, "Ady " + full_name_edit1 +
                    "\n" + "Telefon belgisi: " + "9936" + number_edit1 +
                    "\n" + "Doglan guni we ayy: " + day_birth_edit1 +
                    "\n" + "Passport belgisi: " + passport_edit1 +
                    "\n" + "Jynsy: " + jynsy_user +
                    "\n" + "Poluceniye informaci o karte: " + info_card_);
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Выберите email клиент :"));
            }


        });


    }

    private void setFonts() {
        help_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_800());
        full_name_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        number_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        numberBefore.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        day_of_bth_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        gender_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        email_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        reciv_about_card_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());

        female_btn.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        male_btn.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        sms_btn.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        email_btn.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());

        full_name_edit.setTypeface(Font.getInstance(getApplication()).getMontserrat_600());
        number_edit.setTypeface(Font.getInstance(getApplication()).getMontserrat_600());
        day_birth_edit.setTypeface(Font.getInstance(getApplication()).getMontserrat_600());
        email_edit.setTypeface(Font.getInstance(getApplication()).getMontserrat_600());

        email_edit.setTypeface(Font.getInstance(getApplication()).getMontserrat_600());

        send_btn.setTypeface(Font.getInstance(getApplication()).getMontserrat_700());

    }

    private void initComponents() {
        help_txt = findViewById(R.id.help_txt);
        full_name_txt = findViewById(R.id.full_name_txt);
        number_txt = findViewById(R.id.number_txt);
        numberBefore = findViewById(R.id.numberBefore);
        day_of_bth_txt = findViewById(R.id.day_of_bth_txt);
        gender_txt = findViewById(R.id.gender_txt);
        email_txt = findViewById(R.id.email_txt);
        reciv_about_card_txt = findViewById(R.id.reciv_about_card_txt);

        gender_group = findViewById(R.id.gender_group);
        sms_or_email_group = findViewById(R.id.sms_or_email_group);

        male_btn = findViewById(R.id.male_btn);
        female_btn = findViewById(R.id.female_btn);
        sms_btn = findViewById(R.id.sms_btn);
        email_btn = findViewById(R.id.email_btn);

        full_name_edit = findViewById(R.id.full_name_edit);
        number_edit = findViewById(R.id.number_edit);
        day_birth_edit = findViewById(R.id.day_birth_edit);
        email_edit = findViewById(R.id.email_edit);

        accept_tex_box = findViewById(R.id.accept_tex_box);
        send_btn = findViewById(R.id.send_btn);

        male_card = findViewById(R.id.male_card);
        female_card = findViewById(R.id.female_card);
        sms_card = findViewById(R.id.sms_card);
        email_card = findViewById(R.id.email_card);


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