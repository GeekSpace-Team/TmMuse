package geek.space.tmmuse.Activity.Splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import geek.space.tmmuse.Activity.Intro_Page.Perrmision_Activity;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Activity.Sig_Up.Sig_Up_Activity;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.R;

public class Splash_Screen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private Handler handler1;
    private Runnable removeCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        SharedPref sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState()) {
            sharedPref.setNightModeState(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            sharedPref.setNightModeState(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.splash_screen);


        // Run next activity after 3 sec. We create in this function if user first time
        removeCallbacks = () -> {
            Intent mainIntent = new Intent(Splash_Screen.this, Main_Menu.class).putExtra("type", "0");
            Splash_Screen.this.startActivity(mainIntent);

            finish();
        };
        handler1 = new Handler();
        handler1.postDelayed(removeCallbacks, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler1.removeCallbacks(removeCallbacks);
    }
}