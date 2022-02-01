package geek.space.tmmuse.Common;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {
    public static final String APP_PREFERENCES = "ChangeLang";
    public static final String NIGHT_MODE = "NightMode";
    SharedPreferences mySharedPref;

    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
    }

    public void setLanguageState(String lang) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    // Вызов метода с помошью SharedPreferences

    public String getString(String my_lang, String s) {
        return mySharedPref.getString("My_Lang", "");
    }


    public void setNightModeState(Boolean value) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean(NIGHT_MODE, value);
        editor.apply();
    }

    // this method will load the Night Mode State
    public Boolean loadNightModeState() {
        return mySharedPref.getBoolean(NIGHT_MODE, false);
    }

}
