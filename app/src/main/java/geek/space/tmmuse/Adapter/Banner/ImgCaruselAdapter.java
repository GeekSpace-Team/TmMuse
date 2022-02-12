package geek.space.tmmuse.Adapter.Banner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.R;

public class ImgCaruselAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ViewPager viewPager;
    private ArrayList<Banner> urls = new ArrayList<>();

    public ImgCaruselAdapter(Context context, ArrayList<Banner> urls, ViewPager viewPager) {
        this.context = context;
        this.urls = urls;
        this.viewPager = viewPager;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.img_carusel_adapter, null);
        Banner banner = urls.get(position);
        assert view != null;
        final RoundedImageView imageView = (RoundedImageView) view
                .findViewById(R.id.iv_carousel_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (banner.getLink() != null){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(banner.getLink()));
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, AllProductViewsActivity.class));
                }


            }
        });
        Glide.with(context)
                .load(Constant.BASE_URL_IMAGE+banner.getImage())
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
