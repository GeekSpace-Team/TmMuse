package geek.space.tmmuse.Activity.Main_menu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.BronMovie.BronMovieDetailFragment;
import geek.space.tmmuse.Fragment.BronMovie.BroneMovieFragment;
import geek.space.tmmuse.Fragment.CardFragment.CardFragment;
import geek.space.tmmuse.Fragment.CategoryFragment.CategoryFragment;
import geek.space.tmmuse.Fragment.HelpFragment.HelpFragment;
import geek.space.tmmuse.Fragment.HomeFragment.HomeFragment;
import geek.space.tmmuse.Fragment.MessageFragment.MessageFragment;
import geek.space.tmmuse.Fragment.OpenMessage.OpenMessageFragment;
import geek.space.tmmuse.Fragment.ProfileFragment.Profiles;
import geek.space.tmmuse.Fragment.ProfileFragment.UserProfileFragment;
import geek.space.tmmuse.Fragment.PromotionsOffersFragment.PromotionsOffersFragment;
import geek.space.tmmuse.Fragment.SettingsFragment.SettingsFragment;
import geek.space.tmmuse.Model.AllProfile.Popup;
import geek.space.tmmuse.Model.Home.Home;
import geek.space.tmmuse.R;
import io.alterac.blurkit.BlurLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class Main_Menu extends AppCompatActivity implements View.OnClickListener {


    NeumorphImageView main_menu_btn, category_menu_btn, card_menu_btn, message_menu_btn, settings_menu_btn;
    FrameLayout menu_frame;
    public static Fragment firstFragment = new HomeFragment();
    public static Fragment secondFragment = new CategoryFragment();
    public static Fragment thirdFragment = new CardFragment();
    public static Fragment fourthFragment = new MessageFragment();
    public static Fragment fivesFragment = new SettingsFragment();
    Context context = this;
    private BlurLayout blurLayout;
    private static Main_Menu INSTANCE;
    private TextView no_connection_txt;
    private ImageView reload_app_img;
    private RelativeLayout connection_is_not_ok_rel, connection_is_ok_rel;
    private String testParisImg = "https://mayel.ru/wp-content/uploads/2017/06/paris-3296269_1920.jpg";
    private View view;
    private Integer bottomNavIndex = 0;
    boolean doubleBackToExitPressedOnce = false;
    private ApiInterface apiInterface;
    private Integer page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__menu);
        INSTANCE = this;
        intiComponents();
        setListeners();
        setFont();
        getLang();
        InternetConnectionFunction();
    }



    private void setFont() {
        no_connection_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
    }

    public static void setVisibilityBottomNavigation(FragmentActivity activity, int isVisible) {
        NeumorphCardView bottomNavigationView = activity.findViewById(R.id.bottom_menu);
        bottomNavigationView.setVisibility(isVisible);
    }

    public static Main_Menu get() {
        return INSTANCE;
    }

    public BlurLayout getBlurLayout() {
        return blurLayout;
    }

    private void setListeners() {
        main_menu_btn.setOnClickListener(this);
        category_menu_btn.setOnClickListener(this);
        card_menu_btn.setOnClickListener(this);
        message_menu_btn.setOnClickListener(this);
        settings_menu_btn.setOnClickListener(this);
        reload_app_img.setOnClickListener(view -> InternetConnectionFunction());
    }

    private void intiComponents() {
        main_menu_btn = findViewById(R.id.main_menu_btn);
        category_menu_btn = findViewById(R.id.category_menu_btn);
        card_menu_btn = findViewById(R.id.card_menu_btn);
        message_menu_btn = findViewById(R.id.message_menu_btn);
        settings_menu_btn = findViewById(R.id.settings_menu_btn);
        menu_frame = findViewById(R.id.menu_frame);
        blurLayout = findViewById(R.id.blurLayout);
        no_connection_txt = findViewById(R.id.no_connection_txt);
        reload_app_img = findViewById(R.id.reload_app_img);
        connection_is_not_ok_rel = findViewById(R.id.connection_is_not_ok_rel);
        connection_is_ok_rel = findViewById(R.id.connection_is_ok_rel);
        no_connection_txt = findViewById(R.id.no_connection_txt);
        openFragment(0);
    }

    @Override
    public void onBackPressed() {
       try {
            if (Profiles.draw_profile.isDrawerOpen(GravityCompat.END)) {
                Profiles.draw_profile.closeDrawer(GravityCompat.END);
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SettingsFragment settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag(SettingsFragment.class.getSimpleName());
        CardFragment cardFragment = (CardFragment) getSupportFragmentManager().findFragmentByTag(CardFragment.class.getSimpleName());
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        MessageFragment messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(MessageFragment.class.getSimpleName());
        CategoryFragment categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentByTag(CategoryFragment.class.getSimpleName());
        if (homeFragment != null && homeFragment.isVisible()){
            finish();
        }
        if ((settingsFragment != null && settingsFragment.isVisible())
                || (cardFragment != null && cardFragment.isVisible())
                || (messageFragment != null && messageFragment.isVisible())
                || (categoryFragment != null && categoryFragment.isVisible())) {
            openFragment(0);
        }

        Profiles myFragment = (Profiles) getSupportFragmentManager().findFragmentByTag(Profiles.class.getSimpleName());
        if (myFragment != null && myFragment.isVisible()) {
            secondFragment = new CategoryFragment();
            Utils.removeShow(new CategoryFragment(), CategoryFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }

        OpenMessageFragment openMessageFragment = (OpenMessageFragment) getSupportFragmentManager().findFragmentByTag(OpenMessageFragment.class.getSimpleName());
        if (openMessageFragment != null && openMessageFragment.isVisible()) {
            fourthFragment = new MessageFragment();
            Utils.removeShow(new MessageFragment(), MessageFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }

        UserProfileFragment userProfileFragment = (UserProfileFragment) getSupportFragmentManager().findFragmentByTag(UserProfileFragment.class.getSimpleName());
        if (userProfileFragment != null && userProfileFragment.isVisible()) {
            fivesFragment = new SettingsFragment();
            Utils.removeShow(new SettingsFragment(), SettingsFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }

        PromotionsOffersFragment promotionsOffersFragment = (PromotionsOffersFragment) getSupportFragmentManager().findFragmentByTag(PromotionsOffersFragment.class.getSimpleName());
        if (promotionsOffersFragment != null && promotionsOffersFragment.isVisible()) {
            firstFragment = new HomeFragment();
            Utils.removeShow(new HomeFragment(), HomeFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }

        HelpFragment helpFragment = (HelpFragment) getSupportFragmentManager().findFragmentByTag(HelpFragment.class.getSimpleName());
        if (helpFragment != null && helpFragment.isVisible()) {
            fivesFragment = new SettingsFragment();
            Utils.removeShow(new SettingsFragment(), SettingsFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }


        BroneMovieFragment broneMovieFragment = (BroneMovieFragment) getSupportFragmentManager().findFragmentByTag(BroneMovieFragment.class.getSimpleName());
        if (broneMovieFragment != null && broneMovieFragment.isVisible()) {
            fivesFragment = new SettingsFragment();
            Utils.removeShow(new SettingsFragment(), SettingsFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }

        BronMovieDetailFragment bronMovieDetailFragment = (BronMovieDetailFragment) getSupportFragmentManager().findFragmentByTag(BronMovieDetailFragment.class.getSimpleName());
        if (bronMovieDetailFragment != null && bronMovieDetailFragment.isVisible()) {
            fivesFragment = new BroneMovieFragment();
            Utils.removeShow(new BroneMovieFragment(), BroneMovieFragment.class.getSimpleName(), getSupportFragmentManager(), R.id.menu_frame);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_menu_btn:
                openFragment(0);
                break;
            case R.id.category_menu_btn:
                openFragment(1);
                break;
            case R.id.card_menu_btn:
                openFragment(2);
                break;
            case R.id.message_menu_btn:
                openFragment(3);
                break;
            case R.id.settings_menu_btn:
                openFragment(4);
                break;
        }
    }

    private void openFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavIndex = index;
        switch (index) {
            case 0:

                disableAll();
                Utils.setImgPressed(main_menu_btn, 2, R.color.aply_text_color, context);
                Utils.hideAdd(firstFragment, firstFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
                break;
            case 1:
                disableAll();
                Utils.setImgPressed(category_menu_btn, 2, R.color.aply_text_color, context);
                Utils.hideAdd(secondFragment, secondFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
                break;
            case 2:
                disableAll();
                Utils.setImgPressed(card_menu_btn, 2, R.color.aply_text_color, context);
                Utils.hideAdd(thirdFragment, thirdFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
                break;
            case 3:
                disableAll();
                Utils.setImgPressed(message_menu_btn, 2, R.color.aply_text_color, context);
                Utils.hideAdd(fourthFragment, fourthFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
                break;
            case 4:
                disableAll();
                Utils.setImgPressed(settings_menu_btn, 2, R.color.aply_text_color, context);
                Utils.hideAdd(fivesFragment, fivesFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
                break;
        }

    }

    private void disableAll() {
        main_menu_btn.setShapeType(0);
        main_menu_btn.setColorFilter(context.getResources().getColor(R.color.text_color));

        category_menu_btn.setShapeType(0);
        category_menu_btn.setColorFilter(context.getResources().getColor(R.color.text_color));

        card_menu_btn.setShapeType(0);
        card_menu_btn.setColorFilter(context.getResources().getColor(R.color.text_color));

        message_menu_btn.setShapeType(0);
        message_menu_btn.setColorFilter(context.getResources().getColor(R.color.text_color));

        settings_menu_btn.setShapeType(0);
        settings_menu_btn.setColorFilter(context.getResources().getColor(R.color.text_color));
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo networkInfo : info) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public void InternetConnectionFunction() {
        if (isNetworkAvailable(this)) {
            connection_is_ok_rel.setVisibility(View.VISIBLE);
            connection_is_not_ok_rel.setVisibility(View.INVISIBLE);
        } else {
            connection_is_ok_rel.setVisibility(View.INVISIBLE);
            connection_is_not_ok_rel.setVisibility(View.VISIBLE);
        }
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("indexBottomNav", bottomNavIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            Integer indexBottomNav = savedInstanceState.getInt("indexBottomNav");
            if (indexBottomNav != null) {
                openFragment(indexBottomNav);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firstFragment = new HomeFragment();
        secondFragment = new CategoryFragment();
        thirdFragment = new CardFragment();
        fourthFragment = new MessageFragment();
        fivesFragment = new SettingsFragment();
    }
}