package geek.space.tmmuse.Adapter.Banner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Adapter.ZoomImageAdapter.ImageViewerAdapter;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.R;

public class ImgCaruselAdapter extends RecyclerView.Adapter<ImgCaruselAdapter.ViewHolder> {

    private Context context;
    private ViewPager2 viewPager;
    private ArrayList<Banner> urls = new ArrayList<>();

    public ImgCaruselAdapter(Context context, ViewPager2 viewPager, ArrayList<Banner> urls) {
        this.context = context;
        this.viewPager = viewPager;
        this.urls = urls;
    }

    @NonNull
    @Override
    public ImgCaruselAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_carusel_adapter, parent, false);

        return new ImgCaruselAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgCaruselAdapter.ViewHolder holder, int position) {
        Banner banner = urls.get(position);

        holder.iv_carousel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (banner.getProfile_id()!=null && banner.getProfile_id()>0){
                        Utils.add_click_count(banner.getId(), "banner", context);
                        Intent intent = new Intent(context, AllProductViewsActivity.class);
                        intent.putExtra("ID", banner.getProfile_id());
                        context.startActivity(intent);
                        return;
                    }
                    if (banner.getLink() != null) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(banner.getLink()));
                        context.startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });
        Glide.with(context)
                .load(Constant.BASE_URL_IMAGE + banner.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(holder.iv_carousel_image);
        if (position == urls.size()- 2){
            viewPager.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView iv_carousel_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_carousel_image = itemView.findViewById(R.id.iv_carousel_image);
        }
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            urls.addAll(urls);
            notifyDataSetChanged();
        }
    };
}
