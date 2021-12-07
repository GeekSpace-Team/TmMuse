package geek.space.tmmuse.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import geek.space.tmmuse.Activity.AboutUs.AboutUsActivity;
import geek.space.tmmuse.Activity.Help.HelpActivity;
import geek.space.tmmuse.Activity.TermsOfUse.Terms_of_use;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphCardView;


public class SettingsFragment extends Fragment {
    private View view;
    private Context context;
    private NeumorphCardView dark_mode_card, profile_card, language_card, help_card,
            feed_back_card, terms_use_card, about_us_card;
    private TextView logout_txt, dark_text, profile_text, language_text, language_chk_text,
            help_text, feed_bac_text, terms_use_text, about_us_text, settings_txt;
    private SwitchCompat switch_dark_mode;
    SharedPref sharedPref;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = getContext();
        initComponents();
        setListener();
        setFonts();

        return view;
    }


    private void setListener() {
        about_us_card.setOnClickListener(view -> startActivity(new Intent(context, AboutUsActivity.class)));
        terms_use_card.setOnClickListener(view -> startActivity(new Intent(context, Terms_of_use.class)));
        help_card.setOnClickListener(view -> startActivity(new Intent(context, HelpActivity.class)));
        language_card.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            View bottomSheetDialogView = LayoutInflater.from(context)
                    .inflate(
                            R.layout.bottom_sheet_language,
                            view.findViewById(R.id.bottom_sheet_language));
            TextView firstLanguage=bottomSheetDialogView.findViewById(R.id.sel_lang_txt);
            firstLanguage.setTypeface(Font.getInstance(context).getMontserrat_700());
            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();

        });
        switch_dark_mode.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            if (isChecked) {
                sharedPref.setNightModeState(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                switch_dark_mode.setChecked(true);

            } else {
                sharedPref.setNightModeState(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                switch_dark_mode.setChecked(false);
            }
        });

    }

    private void setFonts() {
        settings_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        logout_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        dark_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        profile_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        language_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        language_chk_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        help_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        feed_bac_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        terms_use_text.setTypeface(Font.getInstance(context).getMontserrat_400());
        about_us_text.setTypeface(Font.getInstance(context).getMontserrat_400());
    }

    private void initComponents() {
        dark_mode_card = view.findViewById(R.id.dark_mode_card);
        profile_card = view.findViewById(R.id.profile_card);
        language_card = view.findViewById(R.id.language_card);
        help_card = view.findViewById(R.id.help_card);
        feed_back_card = view.findViewById(R.id.feed_back_card);
        terms_use_card = view.findViewById(R.id.terms_use_card);
        about_us_card = view.findViewById(R.id.about_us_card);

        logout_txt = view.findViewById(R.id.logout_txt);
        dark_text = view.findViewById(R.id.dark_text);
        profile_text = view.findViewById(R.id.profile_text);
        language_text = view.findViewById(R.id.language_text);
        language_chk_text = view.findViewById(R.id.language_chk_text);
        help_text = view.findViewById(R.id.help_text);
        feed_bac_text = view.findViewById(R.id.feed_bac_text);
        terms_use_text = view.findViewById(R.id.terms_use_text);
        about_us_text = view.findViewById(R.id.about_us_text);
        settings_txt = view.findViewById(R.id.settings_txt);

        switch_dark_mode = view.findViewById(R.id.switch_dark_mode);
        sharedPref=new SharedPref(context);
    }

}