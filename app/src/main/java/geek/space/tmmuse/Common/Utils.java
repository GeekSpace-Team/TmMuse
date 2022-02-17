package geek.space.tmmuse.Common;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.ZoomImageAdapter.ImageViewerAdapter;
import geek.space.tmmuse.Fragment.ProfileFragment.Profiles;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageView;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static geek.space.tmmuse.Common.SharedPref.APP_PREFERENCES;

public class Utils {
    Context context;
    SharedPref sharedPref = new SharedPref(context);

    public static void setPressed(NeumorphButton btn, int type, int color, Context context) {
        btn.setShapeType(type);
        btn.setTextColor(context.getResources().getColor(color));
    }

    public static void setImgPressed(NeumorphImageView img, int type, int color, Context context) {
        img.setShapeType(type);
        img.setColorFilter(context.getResources().getColor(color));
    }

    public static void setPressedSendSave(NeumorphButton btn, int type, int color, int backColor, Context context) {
        btn.setShapeType(type);
        btn.setTextColor(color);
        btn.setBackgroundColor(context.getResources().getColor(backColor));
    }


    public static void hideAdd(Fragment fragment, String tagFragmentName, FragmentManager mFragmentManager, int frame) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(frame, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public static void removeShow(Fragment fragment, String tagFragmentName, FragmentManager mFragmentManager, int frame) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(frame, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public static void restart(FragmentActivity activity, Context context) {
//        Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
//        activity.finish();
        activity.recreate();
        activity.getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        Main_Menu.isFirst=false;

    }

    public static void gotoProfiles(String categoryName, ArrayList<Integer> categoryId, FragmentManager fragmentManager) {
        Profiles profiles = new Profiles(categoryName, categoryId);
        Main_Menu.secondFragment = profiles;
        Utils.hideAdd(profiles, profiles.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
    }

    public static Bitmap blurRenderScript(Bitmap smallBitmap, int radius, Context mContext) {

        try {
            smallBitmap = RGB565toARGB888(smallBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bitmap bitmap = Bitmap.createBitmap(
                smallBitmap.getWidth(), smallBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(mContext);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, smallBitmap);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius); // radius must be 0 < r <= 25
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();

        return bitmap;

    }

    public static Bitmap RGB565toARGB888(Bitmap img) throws Exception {
        int numPixels = img.getWidth() * img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }

// ViewPage scrolling with RecyclerView
    public static void showImageViewer(Context context, ArrayList<String> images, ArrayList<String> largeImages) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.image_viewer, null, false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
//        window.getAttributes().windowAnimations = R.style.DialogAnimation;

        ViewPager pager = dialog.findViewById(R.id.pager);
        ImageView back=dialog.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ImageViewerAdapter adapter = new ImageViewerAdapter(largeImages, context);
        pager.setAdapter(adapter);



        LinearLayout linear = dialog.findViewById(R.id.linear);
        HorizontalScrollView horizontalScrollView=dialog.findViewById(R.id.rec);

        if(images.size()<2){
            horizontalScrollView.setVisibility(View.GONE);
        }



        for (int i=0;i<images.size();i++) {
            String img = images.get(i);
            View selector = LayoutInflater.from(context).inflate(R.layout.image_selector, null, false);
            ImageView image = selector.findViewById(R.id.image);
            LinearLayout unselect = selector.findViewById(R.id.unselect);
            if(i==0){
                unselect.setVisibility(View.GONE);
            }
            Glide.with(context)
                    .load(img)
                    .into(image);
            int finalI = i;
            selector.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pager.setCurrentItem(finalI);
                }
            });
            linear.addView(selector);
        }
        int[] old = {0};
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                View unselectView = linear.getChildAt(position);
                LinearLayout unselect = unselectView.findViewById(R.id.unselect);
                LinearLayout oldUnselectView = linear.getChildAt(old[0]).findViewById(R.id.unselect);
                oldUnselectView.setVisibility(View.VISIBLE);
                old[0] = position;
                unselect.setVisibility(View.GONE);
                horizontalScrollView.scrollTo(unselectView.getLeft(),unselectView.getTop());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog.show();
    }

    public static String getLanguage(Context myContext) {
        SharedPreferences mySharedPref=myContext.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        return mySharedPref.getString("My_Lang", "");
    }

    public static void setSharePreference (Context context, String name, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(name, MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static String getSharePreferences(Context context, String name){
        SharedPreferences preferences = context.getSharedPreferences(name, MODE_PRIVATE);
        String value = preferences.getString(name, "");
        return value;
    }
}
