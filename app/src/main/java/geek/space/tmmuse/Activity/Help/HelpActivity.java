package geek.space.tmmuse.Activity.Help;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class HelpActivity extends AppCompatActivity {

    private TextView help_txt, name_txt, number_txt, emil_txt,
            message_txt, after_call_txt, numberBefore;

    private EditText full_name_edit, number_edit, message_edit, emil_edit;
    private NeumorphButton send_btn;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initUnits();
        setFonts();
        setListener();
        getLang();
    }

    private void sendEmailFunc() {
        String to = "wolfdog31051993@gmail.com";
        String message = message_edit.getText().toString();
        String user_name = full_name_edit.getText().toString();
        String user_phone = number_edit.getText().toString();
        String user_email = emil_edit.getText().toString();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_TEXT, "Ady: " + user_name + "\n" + "Telefon belgisi: +993" + user_phone + "\n" +
                "Elektron adresi: " + user_email + "\n" + "Yazan Teklibi: " + message);

        //для того чтобы запросить email клиент устанавливаем тип
        email.setType("message/rfc822");
        Utils.setPressedSendSave(send_btn, 2, R.color.aply_text_color, R.color.card_background, context);
        startActivity(Intent.createChooser(email, "Выберите email клиент :"));
    }

    private void setListener() {
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (full_name_edit.length() == 0) {
                    Toast.makeText(context, "Напишите имя", Toast.LENGTH_SHORT).show();
                } else if (number_edit.length() == 0) {
                    Toast.makeText(context, "Напишите номер телефона", Toast.LENGTH_SHORT).show();
                } else if (message_edit.length() <= 30) {
                    Toast.makeText(context, "Тело сообщений не должно быть меньше 30 знаков", Toast.LENGTH_SHORT).show();
                } else {
                    sendEmailFunc();

                }
            }
        });
    }

    private void setFonts() {
        help_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        name_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        number_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        message_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        emil_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        after_call_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        numberBefore.setTypeface(Font.getInstance(context).getMontserrat_400());

        full_name_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
        number_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
        message_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
        emil_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
    }

    private void initUnits() {
        help_txt = findViewById(R.id.help_txt);
        name_txt = findViewById(R.id.name_txt);
        number_txt = findViewById(R.id.number_txt);
        emil_txt = findViewById(R.id.emil_txt);
        message_txt = findViewById(R.id.message_txt);
        after_call_txt = findViewById(R.id.after_call_txt);
        numberBefore = findViewById(R.id.numberBefore);

        full_name_edit = findViewById(R.id.full_name_edit);
        number_edit = findViewById(R.id.number_edit);
        message_edit = findViewById(R.id.message_edit);
        emil_edit = findViewById(R.id.emil_edit);

        send_btn = findViewById(R.id.send_btn);
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