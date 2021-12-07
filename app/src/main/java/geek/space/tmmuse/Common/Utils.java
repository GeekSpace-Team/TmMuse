package geek.space.tmmuse.Common;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageView;
import soup.neumorphism.ShapeType;

public class Utils {
    public static String getLanguage(Context context){
        return "tm";
    }

    public static void setPressed(NeumorphButton btn, int type,int color,Context context){
        btn.setShapeType(type);
        btn.setTextColor(context.getResources().getColor(color));
    }

    public static void setImgPressed(NeumorphImageView img, int type,int color,Context context){
        img.setShapeType(type);
        img.setColorFilter(context.getResources().getColor(color));
    }

    public static void setPressedSendSave(NeumorphButton btn, int type, int color, int backColor, Context context){
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
}
