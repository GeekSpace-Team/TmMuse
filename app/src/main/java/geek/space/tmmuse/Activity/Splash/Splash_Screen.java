package geek.space.tmmuse.Activity.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import geek.space.tmmuse.Activity.Intro_Page.Whats_New;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.R;

public class Splash_Screen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()) {
            sharedPref.setNightModeState(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            sharedPref.setNightModeState(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        new Handler().postDelayed(new Runnable() {
            // Run next activity after 3 sec. We create in this function if user first time
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash_Screen.this, Whats_New.class);
                Splash_Screen.this.startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}