package geek.space.tmmuse.Adapter.ZoomImageAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Objects;

import geek.space.tmmuse.R;

public class ImageViewerAdapter extends PagerAdapter {

    private ArrayList<String> images=new ArrayList<>();
    // Layout Inflater
    private LayoutInflater mLayoutInflater;
    private Context context;

    public ImageViewerAdapter(ArrayList<String> images, Context context) {
        this.images = images;
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return images.size();
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml

        View itemView = mLayoutInflater.inflate(R.layout.img, container, false);


        // create Object
        String object=images.get(position);

        // referencing the image view from the item.xml file
        ImageView imageView = itemView.findViewById(R.id.myZoomageView);

        // setting the image in the imageView
        Glide.with(context)
                .load(object)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
