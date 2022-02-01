package geek.space.tmmuse.Activity.VrImage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

import geek.space.tmmuse.R;

public class VrImageActivity extends AppCompatActivity {

    private VrPanoramaView vr_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_image);

        intiComponents();
        showImg();
    }

    private void showImg() {
        try {
            InputStream stream = this.getAssets().open("path/image.jpg");
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            vr_img.loadImageFromBitmap(BitmapFactory.decodeStream(stream), options);
            stream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void intiComponents() {
        vr_img = findViewById(R.id.vr_img);
    }
}