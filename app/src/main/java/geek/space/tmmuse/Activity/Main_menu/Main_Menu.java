package geek.space.tmmuse.Activity.Main_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.CardFragment;
import geek.space.tmmuse.Fragment.CategoryFragment;
import geek.space.tmmuse.Fragment.HomeFragment;
import geek.space.tmmuse.Fragment.MessageFragment;
import geek.space.tmmuse.Fragment.SettingsFragment;
import geek.space.tmmuse.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__menu);
        intiComponents();
        setListeners();

    }

    private void setListeners() {
        main_menu_btn.setOnClickListener(this);
        category_menu_btn.setOnClickListener(this);
        card_menu_btn.setOnClickListener(this);
        message_menu_btn.setOnClickListener(this);
        settings_menu_btn.setOnClickListener(this);
    }


    private void intiComponents() {
        main_menu_btn = findViewById(R.id.main_menu_btn);
        category_menu_btn = findViewById(R.id.category_menu_btn);
        card_menu_btn = findViewById(R.id.card_menu_btn);
        message_menu_btn = findViewById(R.id.message_menu_btn);
        settings_menu_btn = findViewById(R.id.settings_menu_btn);
        menu_frame = findViewById(R.id.menu_frame);
        openFragment(0);
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


}