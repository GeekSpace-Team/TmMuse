package geek.space.tmmuse.Common.Font;


import android.content.Context;
import android.graphics.Typeface;

public class Font {
    private static Typeface montserrat_800;
    private static Typeface montserrat_400;
    private static Typeface montserrat_700;
    private static Typeface montserrat_600;
    private static Typeface montserrat_500;


    public Typeface getMontserrat_800() {
        return montserrat_800;
    }

    public Typeface getMontserrat_400() {
        return montserrat_400;
    }

    public Typeface getMontserrat_700() {
        return montserrat_700;
    }


    public static void setMontserrat_800(Typeface font1) {
        Font.montserrat_800 = font1;
    }

    public static void setMontserrat_400(Typeface font2) {
        Font.montserrat_400 = font2;
    }

    public static void setMontserrat_700(Typeface font3) {
        Font.montserrat_700 = font3;
    }

    public static Typeface getMontserrat_600() {
        return montserrat_600;
    }

    public static void setMontserrat_600(Typeface montserrat_600) {
        Font.montserrat_600 = montserrat_600;
    }

    public static Typeface getMontserrat_500() {
        return montserrat_500;
    }

    public static void setMontserrat_500(Typeface montserrat_500) {
        Font.montserrat_500 = montserrat_500;
    }

    private static volatile Font instance;

    private Font() {
    }

    public static Font getInstance(Context activity) {
        Font localInstance = instance;
        if (localInstance == null) {
            synchronized (Font.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Font();
                }
            }
            setMontserrat_800(Typeface.createFromAsset(activity.getAssets(), "fonts/montserrat_extra_bold.ttf"));
            setMontserrat_400(Typeface.createFromAsset(activity.getAssets(), "fonts/montserrat_regular.ttf"));
            setMontserrat_700(Typeface.createFromAsset(activity.getAssets(), "fonts/montserrat_bold.ttf"));
            setMontserrat_500(Typeface.createFromAsset(activity.getAssets(), "fonts/montserrat_medium.ttf"));
            setMontserrat_600(Typeface.createFromAsset(activity.getAssets(), "fonts/montserrat_semi_bold.ttf"));

        }
        return localInstance;
    }
}
