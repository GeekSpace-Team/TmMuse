package geek.space.tmmuse.Activity.Sig_Up;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Interest.Interest_Activity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.UserRegister.CheckUserCode;
import geek.space.tmmuse.Model.UserRegister.ResponseCheckUser;
import geek.space.tmmuse.Model.UserRegister.UserGetRegister;
import geek.space.tmmuse.Model.UserRegister.UserPostRegister;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class Sig_Up_Activity extends AppCompatActivity {
    private TextView sgn_up_txt, skip_txt, sign_up_desc, ful_name_txt, number_txt, sms_code_txt, numberBefore, getCode;
    private EditText full_name_edit, number_edit, edit_code_one, edit_code_two, edit_code_three, edit_code_four;
    private NeumorphButton save_btn;
    private Context context = this;
    private String phone_number = "";
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig__up_);

        initComponents();
        setFonts();
        getCodePhoneNumber();
        setListener();
        getLang();
    }

    private void setListener() {
        save_btn.setOnClickListener(view -> {
            String code = edit_code_one.getText().toString() +
                    edit_code_two.getText().toString() +
                    edit_code_three.getText().toString() +
                    edit_code_four.getText().toString();
            if (number_edit.getText().toString().length() < 8 || full_name_edit.getText().toString().trim().isEmpty() ||
                    code.length() < 4) {
                Toast.makeText(context, "Maglumatlary doly girizin", Toast.LENGTH_SHORT).show();
            } else {
                Utils.setPressedSendSave(save_btn, 2, R.color.aply_text_color, R.color.card_background, context);
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                CheckUserCode checkUserCode = new CheckUserCode(full_name_edit.getText().toString(),
                        "+993" + number_edit.getText().toString(), code);
                Call<ResponseCheckUser> responseCheckUserCall = apiInterface.code_verification(checkUserCode);
                responseCheckUserCall.enqueue(new Callback<ResponseCheckUser>() {
                    @Override
                    public void onResponse(Call<ResponseCheckUser> call, Response<ResponseCheckUser> response) {
                        if (response.isSuccessful() && response.body().getError() != true) {
                            if (response.body().getBody() != null) {
                                Utils.setSharePreference(context, "token", response.body().getBody().getToken());
                                Utils.setSharePreference(context,"user_id",response.body().getBody().getId()+"");
//                                Toast.makeText(context, Utils.getSharePreferences(context,"token"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Interest_Activity.class));
                                finish();
                            } else {
                                Toast.makeText(context, "Nadogry kod girizildi", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Yalnyshlyk yuze cykdy", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCheckUser> call, Throwable t) {

                    }
                });
            }
        });
        skip_txt.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Interest_Activity.class));
            finish();
        });
        number_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phone_number = number_edit.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number_edit.getText().toString().length() < 8) {
                    Toast.makeText(context, "Telefon belginizi doly girizin", Toast.LENGTH_SHORT).show();
                    return;
                }
                progress_bar.setVisibility(View.VISIBLE);
                getCode.setVisibility(View.GONE);
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                UserPostRegister body = new UserPostRegister("+993" + phone_number);
                Call<UserGetRegister> userGetRegisterCall = apiInterface.phoneVerification(body);
                userGetRegisterCall.enqueue(new Callback<UserGetRegister>() {
                    @Override
                    public void onResponse(Call<UserGetRegister> call, Response<UserGetRegister> response) {
                        if (response.isSuccessful() && response.body().getError() != true) {
                            progress_bar.setVisibility(View.GONE);
                            getCode.setVisibility(View.VISIBLE);
                            Snackbar.make(view, "Sms arkaly gelen kody ashakdaky oyjuklere girizin!", Snackbar.LENGTH_LONG).show();
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<UserGetRegister> call, Throwable t) {
                    }
                });
            }
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
        getCode.setTypeface(Font.getInstance(this).getMontserrat_400());
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
        getCode = findViewById(R.id.getCode);

        full_name_edit = findViewById(R.id.full_name_edit);
        number_edit = findViewById(R.id.number_edit);
        edit_code_one = findViewById(R.id.edit_code_one);
        edit_code_two = findViewById(R.id.edit_code_two);
        edit_code_three = findViewById(R.id.edit_code_three);
        edit_code_four = findViewById(R.id.edit_code_four);
        numberBefore = findViewById(R.id.numberBefore);
        progress_bar = findViewById(R.id.progress_bar);

        save_btn = findViewById(R.id.save_btn);
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }
}