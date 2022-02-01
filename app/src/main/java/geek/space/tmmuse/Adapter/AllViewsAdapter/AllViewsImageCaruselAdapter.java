package geek.space.tmmuse.Adapter.AllViewsAdapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.R;

public class AllViewsImageCaruselAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<AllProfile> allProfiles = new ArrayList<>();

    public AllViewsImageCaruselAdapter(Context context, ArrayList<AllProfile> allProfiles) {
        this.context = context;
        this.allProfiles = allProfiles;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return allProfiles.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.img_carusel_adapter, null);
        AllProfile banner = allProfiles.get(position);
        assert view != null;
        final RoundedImageView imageView = (RoundedImageView) view
                .findViewById(R.id.iv_carousel_image);

        Glide.with(context)
                .load(banner.getImg_profile())
                .into(imageView);

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
