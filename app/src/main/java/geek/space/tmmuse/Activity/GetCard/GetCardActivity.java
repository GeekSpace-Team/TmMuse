package geek.space.tmmuse.Activity.GetCard;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Calendar;
import java.util.List;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Constant.ConstantActivity;
import geek.space.tmmuse.Activity.Sig_Up.Sig_Up_Activity;
import geek.space.tmmuse.Common.AppAlert;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.GetCard.PostGetCard;
import geek.space.tmmuse.Model.GetCard.SendGetCard;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class GetCardActivity extends AppCompatActivity {

    private TextView help_txt, full_name_txt, number_txt, day_of_bth_txt, gender_txt,
            email_txt,  day_birth_edit, number_edit, full_name_edit, reciv_about_card_txt, go_to_sig_up;
    private RadioGroup gender_group, sms_or_email_group;
    private RadioButton male_btn, female_btn, sms_btn,  email_btn;
    private EditText email_edit;
    private CheckBox accept_tex_box;
    private NeumorphButton send_btn;
    private List<String> job_lists;
    private String jynsy = "";
    private String take_info_card = "";
    private NeumorphCardView male_card, female_card, sms_card, email_card, birth_card;
    private ApiInterface apiInterface;
    private Integer status = 0;
    private int gender;
    private Boolean is_smss = true;

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

        if (Utils.getSharePreferences(this, "token").equals("")){
            findViewById(R.id.no_reg_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.if_token_have_scr).setVisibility(View.GONE);
        } else {
            findViewById(R.id.no_reg_layout).setVisibility(View.GONE);
            findViewById(R.id.if_token_have_scr).setVisibility(View.VISIBLE);
        }

        accept_tex_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GetCardActivity.this, ConstantActivity.class)
                        .putExtra("page_type", "about"));
            }
        });

        day_birth_edit.setInputType(InputType.TYPE_NULL);
        birth_card.setOnClickListener(view -> {

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
            if (b) {
                male_card.setShapeType(2);
                female_card.setShapeType(0);
                female_btn.setChecked(false);
                jynsy = male_btn.getText().toString();
                gender = 1;
            }
        });
        female_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                male_card.setShapeType(0);
                female_card.setShapeType(2);
                male_btn.setChecked(false);
                jynsy = female_btn.getText().toString();
                gender = 2;
            }
        });

        sms_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                sms_card.setShapeType(2);
                email_card.setShapeType(0);
                email_btn.setChecked(false);
                take_info_card = sms_btn.getText().toString();
            }
        });
        email_btn.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                sms_card.setShapeType(0);
                email_card.setShapeType(2);
                sms_btn.setChecked(false);
                take_info_card = email_btn.getText().toString();
            }
        });

        number_edit.setText(Utils.getSharePreferences(this, "phone_number"));
        full_name_edit.setText(Utils.getSharePreferences(this, "full_name"));

        send_btn.setOnClickListener(view -> {
            if (day_birth_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, getResources().getString(R.string.chek_brith), Toast.LENGTH_SHORT).show();
            } else if (jynsy.equals("")) {
                Toast.makeText(GetCardActivity.this, getResources().getString(R.string.chek_gender), Toast.LENGTH_SHORT).show();
            } else if (email_edit.length() == 0) {
                Toast.makeText(GetCardActivity.this, getResources().getString(R.string.chek_email), Toast.LENGTH_SHORT).show();
            } else if (!accept_tex_box.isChecked()) {
                Toast.makeText(GetCardActivity.this, getResources().getString(R.string.chek_permission), Toast.LENGTH_SHORT).show();
            } else if (take_info_card.equals("")) {
                Toast.makeText(GetCardActivity.this, getResources().getString(R.string.chek_info_card), Toast.LENGTH_SHORT).show();
            } else {
                KProgressHUD progress = Utils.AppProgressBar(this);
                progress.setLabel(getResources().getString(R.string.wait));
                progress.show();
                apiInterface = ApiClient.getClient()
                        .create(ApiInterface.class);
                String email_user = email_edit.getText().toString();
                String day_birth_edit1 = day_birth_edit.getText().toString();
                String user_token = "Bearer " + Utils.getSharePreferences(this, "token");
                SendGetCard sendGetCard = new SendGetCard(day_birth_edit1, gender, email_user, is_smss, status);
                Call<PostGetCard> postGetCardCall = apiInterface.create_card_user(sendGetCard, user_token);
                findViewById(R.id.getCard_progress).setVisibility(View.VISIBLE);
                send_btn.setVisibility(View.GONE);
                postGetCardCall.enqueue(new Callback<PostGetCard>() {
                    @Override
                    public void onResponse(Call<PostGetCard> call, Response<PostGetCard> response) {
                        if (response.isSuccessful()) {
                            findViewById(R.id.getCard_progress).setVisibility(View.GONE);
                            send_btn.setVisibility(View.VISIBLE);
                            AppAlert alert = new AppAlert(GetCardActivity.this);
                            alert.setTitle(GetCardActivity.this.getResources().getString(R.string.access_get_card));
                            alert.hasCancel(false);
                            alert.setButtonListener(new AppAlert.ButtonListener() {
                                @Override
                                public void onOkListener() {
                                    day_birth_edit.setText("");
                                    male_btn.setChecked(false);
                                    female_btn.setChecked(false);
                                    sms_btn.setChecked(false);
                                    email_btn.setChecked(false);
                                    email_edit.setText("");
                                    alert.dismiss();
                                }

                                @Override
                                public void onCancelListener() {

                                }
                            });
                            alert.show();
                        } else {
                            Utils.showCustomToast(getResources().getString(R.string.check_internet),
                                    R.drawable.ic_wifi_no_connection,
                                    GetCardActivity.this,
                                    R.color.no_internet_back);
                        }
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<PostGetCard> call, Throwable t) {
                        Utils.showCustomToast(getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            GetCardActivity.this,
                            R.color.no_internet_back);
                        progress.dismiss(); findViewById(R.id.getCard_progress).setVisibility(View.VISIBLE);
                        send_btn.setVisibility(View.GONE);
                    }
                });
            }


        });

        go_to_sig_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Sig_Up_Activity.class).putExtra("type", "1"));
            }
        });


    }

    private void setFonts() {
        help_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_800());
        full_name_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
        number_txt.setTypeface(Font.getInstance(getApplication()).getMontserrat_400());
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
        day_of_bth_txt = findViewById(R.id.day_of_bth_txt);
        gender_txt = findViewById(R.id.gender_txt);
        email_txt = findViewById(R.id.email_txt);
        reciv_about_card_txt = findViewById(R.id.reciv_about_card_txt);
        birth_card = findViewById(R.id.birth_card);

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
        go_to_sig_up = findViewById(R.id.go_to_sig_up);


    }

    public void on_back_pressed(View view) {
        onBackPressed();
        finish();
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.getSharePreferences(this, "token").equals("")){
            findViewById(R.id.no_reg_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.if_token_have_scr).setVisibility(View.GONE);
        } else {
            findViewById(R.id.no_reg_layout).setVisibility(View.GONE);
            findViewById(R.id.if_token_have_scr).setVisibility(View.VISIBLE);
        }
    }
}