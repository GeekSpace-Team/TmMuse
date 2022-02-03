package geek.space.tmmuse.Activity.PostPreview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.R;

public class PostPreviewActivity extends AppCompatActivity {

    private TextView look_txt_counts, promotion_tit_txt, prom_desc_text, like_count_txt, dis_like_count_txt;
    private RoundedImageView promotion_img;
    private ImageView close_promotion_img;
    private ImageView back_img_promotion;
    private String title = "", desc = "", img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);
        makeStatusbarTransparent();
        initComponents();
        setFonts();
        setListener();

    }

    private void setListener() {
        close_promotion_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        Intent getIntentProm = getIntent();
        title = getIntentProm.getStringExtra("TITLE");
        desc = getIntentProm.getStringExtra("DESC");
        img = getIntentProm.getStringExtra("IMG");
        prom_desc_text.setText(desc);
        promotion_tit_txt.setText(title);
        Glide.with(this).load(img).into(promotion_img);
        Glide.with(this).asBitmap().listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                Bitmap blur= Utils.blurRenderScript(resource,20,PostPreviewActivity.this);
                back_img_promotion.setImageBitmap(blur);
                return true;
            }
        }).load(img).into(back_img_promotion);




    }

    private void setFonts() {
        look_txt_counts.setTypeface(Font.getInstance(this).getMontserrat_500());
        promotion_tit_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        prom_desc_text.setTypeface(Font.getInstance(this).getMontserrat_500());
    }

    private void initComponents() {
        look_txt_counts = findViewById(R.id.look_txt_counts);
        promotion_tit_txt = findViewById(R.id.promotion_tit_txt);
        prom_desc_text = findViewById(R.id.prom_desc_text);
        promotion_img = findViewById(R.id.promotion_img);
        back_img_promotion = findViewById(R.id.back_img_promotion);
        close_promotion_img = findViewById(R.id.close_promotion_img);
    }

    private void makeStatusbarTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }
}