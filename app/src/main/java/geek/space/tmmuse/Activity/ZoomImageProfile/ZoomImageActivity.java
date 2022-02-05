package geek.space.tmmuse.Activity.ZoomImageProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import geek.space.tmmuse.Adapter.ZoomImageAdapter.ZoomImageADpater;
import geek.space.tmmuse.Adapter.ZoomImageAdapter.ZoomViewPagerAdapter;
import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;

public class ZoomImageActivity extends AppCompatActivity {
    public static ArrayList<TestModelViewPager> images=new ArrayList<>();
    private ViewPager zoom_img_profile;
    private RecyclerView zoom_img_rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        initComponents();
        setZoomImageList();
        setZoomImageAdapter();
    }

    private void setZoomImageAdapter() {
        zoom_img_rec.setAdapter(new ZoomImageADpater(this,images,zoom_img_profile));
        zoom_img_rec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
    }

    private void setZoomImageList() {
        zoom_img_profile.setAdapter(new ZoomViewPagerAdapter(this,images,zoom_img_rec));

    }

    private void initComponents() {
        zoom_img_rec = findViewById(R.id.zoom_img_rec);
        zoom_img_profile = findViewById(R.id.zoom_img_profile);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        images=null;
    }

}