package geek.space.tmmuse.Adapter.ZoomImageAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.ZoomImageProfile.ZoomImageActivity;
import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;

public class ZoomViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<TestModelViewPager> testModelViewPagers;

    public ZoomViewPagerAdapter(Context context, ArrayList<TestModelViewPager> testModelViewPagers, RecyclerView zoom_img_rec) {
        this.context = context;
        this.testModelViewPagers = testModelViewPagers;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return testModelViewPagers.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.profile_image_carusel_adapter, null);
        TestModelViewPager testModelViewPager = testModelViewPagers.get(position);
        assert view != null;
        final RoundedImageView imageView = (RoundedImageView) view
                .findViewById(R.id.iv_carousel_image);
        Glide.with(context)
                .load(testModelViewPager.getImg_url())
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
