package geek.space.tmmuse.Activity.Interest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.R;

public class Interest_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
    }








    public void go_main_menu(View view) {
        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
        finish();
    }
}