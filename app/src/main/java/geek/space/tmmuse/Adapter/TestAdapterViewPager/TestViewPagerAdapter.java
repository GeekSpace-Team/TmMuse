package geek.space.tmmuse.Adapter.TestAdapterViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.AllProfile.ImgProfile;
import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;

public class TestViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ImgProfile> imgProfiles;

    public TestViewPagerAdapter(Context context, ArrayList<ImgProfile> imgProfiles) {
        this.context = context;
        this.imgProfiles = imgProfiles;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imgProfiles.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.profile_image_carusel_adapter, null);
        ImgProfile imgProfile = imgProfiles.get(position);
        assert view != null;
        final RoundedImageView imageView = (RoundedImageView) view
                .findViewById(R.id.iv_carousel_image);
        try {
            Glide.with(context)
                    .load(Constant.BASE_URL_IMAGE + imgProfile.getLarge_image())
                    .into(imageView);
        } catch (Exception e){
            e.printStackTrace();
        }

        container.addView(view, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> imgs=new ArrayList<>();
                for(ImgProfile m:imgProfiles){
                    imgs.add(Constant.BASE_URL_IMAGE+m.getLarge_image());
                }
                Utils.showImageViewer(context, imgs, imgs);
            }
        });

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
