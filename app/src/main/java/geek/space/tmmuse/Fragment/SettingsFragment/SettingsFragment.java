package geek.space.tmmuse.Fragment.SettingsFragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

import geek.space.tmmuse.Activity.AboutUs.AboutUsActivity;
import geek.space.tmmuse.Activity.Help.HelpActivity;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Activity.TermsOfUse.Terms_of_use;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.ProfileFragment.UserProfileFragment;
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
    private String language = "";


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
        if (sharedPref.loadNightModeState()) {
            switch_dark_mode.setChecked(true);
        }
        loadLocale();
        setListener();
        setFonts();

        return view;
    }


    private void setListener() {
        about_us_card.setOnClickListener(view -> startActivity(new Intent(context, AboutUsActivity.class)));

        terms_use_card.setOnClickListener(view -> startActivity(new Intent(context, Terms_of_use.class)));

        help_card.setOnClickListener(view -> startActivity(new Intent(context, HelpActivity.class)));

        language_card.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
            View bottomSheetDialogView = LayoutInflater.from(context)
                    .inflate(
                            R.layout.bottom_sheet_language,
                            view.findViewById(R.id.bottom_sheet_language));
            TextView sel_lang_txt = bottomSheetDialogView.findViewById(R.id.sel_lang_txt);
            RadioButton tm_btn = bottomSheetDialogView.findViewById(R.id.tm_btn);
            RadioButton ru_btn = bottomSheetDialogView.findViewById(R.id.ru_btn);
            if (language.equals("tm")) {
                tm_btn.setChecked(true);
                ru_btn.setChecked(false);
            } else {
                tm_btn.setChecked(false);
                ru_btn.setChecked(true);
            }
            tm_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        changeLanguage("tm");
                        bottomSheetDialog.dismiss();
                    }


                }
            });

            ru_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        changeLanguage("ru");
                        bottomSheetDialog.dismiss();
                    }

                }
            });


            sel_lang_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
            tm_btn.setTypeface(Font.getInstance(context).getMontserrat_500());
            ru_btn.setTypeface(Font.getInstance(context).getMontserrat_500());
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

        feed_back_card.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PLAY_MARKET));
            startActivity(intent);
        });

        profile_card.setOnClickListener(view -> {
            UserProfileFragment userProfileFragment = new UserProfileFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Main_Menu.fivesFragment = userProfileFragment;
            Utils.hideAdd(userProfileFragment, userProfileFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
        });

        logout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.log_out_popup, null, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(view);

                TextView pay_attention, pay_attention_desc, cancel_txt, aply_txt;
                pay_attention = dialog.findViewById(R.id.pay_attention);
                pay_attention_desc = dialog.findViewById(R.id.pay_attention_desc);
                cancel_txt = dialog.findViewById(R.id.cancel_txt);
                aply_txt = dialog.findViewById(R.id.aply_txt);

                pay_attention.setTypeface(Font.getInstance(context).getMontserrat_800());
                pay_attention_desc.setTypeface(Font.getInstance(context).getMontserrat_400());
                cancel_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
                aply_txt.setTypeface(Font.getInstance(context).getMontserrat_600());

                cancel_txt.setOnClickListener(view1 -> dialog.dismiss());

                final Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                dialog.show();
            }
        });

    }

    private void changeLanguage(String s) {
        setlocale(s);
        Utils.restart(getActivity(), context);
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
        sharedPref = new SharedPref(context);
    }


    //    Настройка языка
    public void setlocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration,
                context.getResources().getDisplayMetrics());
        sharedPref.setLanguageState(lang);
    }


    public void loadLocale() {
        sharedPref = new SharedPref(context);
        language = sharedPref.getString("My_Lang", "");
        setlocale(language);

        if (language.equals("tm")) {
            language_chk_text.setText("Türkmen - dili");
        } else {
            language_chk_text.setText("Русккий язык");
        }

    }
}