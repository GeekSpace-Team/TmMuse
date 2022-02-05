package geek.space.tmmuse.Adapter.ZoomImageAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;

public class ZoomImageADpater extends RecyclerView.Adapter<ZoomImageADpater.ViewHolder> {
    private Context context;
    private ArrayList<TestModelViewPager> testModelViewPagers = new ArrayList<>();

    public ZoomImageADpater(Context context, ArrayList<TestModelViewPager> testModelViewPagers, ViewPager zoom_img_profile) {
        this.context = context;
        this.testModelViewPagers = testModelViewPagers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zoom_img_rec_adapter, parent, false);
        return new ZoomImageADpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TestModelViewPager testModelViewPager = testModelViewPagers.get(position);
        Glide.with(context).load(testModelViewPager.getImg_url()).into(holder.zoom_img_rec);
    }

    @Override
    public int getItemCount() {
        return testModelViewPagers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView zoom_img_rec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            zoom_img_rec = itemView.findViewById(R.id.zoom_img_rec);
        }
    }
}
