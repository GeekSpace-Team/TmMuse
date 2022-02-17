package geek.space.tmmuse.Adapter.TestAdapterViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Model.AllProfile.ImgProfile;
import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;

public class TestAdapterViewPager extends RecyclerView.Adapter<TestAdapterViewPager.ViewHolder> {
    private Context context;
    private ArrayList<ImgProfile> imgProfiles;
    private ViewPager viewPager;
    private boolean isFirst=true;
    private WormDotsIndicator dots_indicator;

    public TestAdapterViewPager(Context context, ArrayList<ImgProfile> imgProfiles, ViewPager viewPager, WormDotsIndicator dots_indicator) {
        this.context = context;
        this.imgProfiles = imgProfiles;
        this.viewPager = viewPager;
        this.dots_indicator = dots_indicator;
        viewPager.setAdapter(new TestViewPagerAdapter(context, imgProfiles));
        dots_indicator.setViewPager(viewPager);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_profile_rec_adapter, parent, false);
        return new TestAdapterViewPager.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        ImgProfile imgProfile = imgProfiles.get(position);
        if (imgProfile.getVR()){
            holder.itemView.setVisibility(View.GONE);
        }
        if(isFirst){
            Glide.with(context).load(Constant.BASE_URL_IMAGE+imgProfile.getLarge_image()).into(holder.product_img);
            viewPager.setAdapter(new TestViewPagerAdapter(context, imgProfiles));
            isFirst=false;
        }
        holder.product_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(position);
            }
        });
        Glide.with(context).load(Constant.BASE_URL_IMAGE+imgProfile.getLarge_image()).into(holder.product_img);
    }

    @Override
    public int getItemCount() {
        return imgProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView product_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_img = itemView.findViewById(R.id.product_img);
        }
    }
}
