package geek.space.tmmuse.Activity.PostPreview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.ShareActivity.ShareActivity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.LikeDislike.PostLikeDislike;
import geek.space.tmmuse.Model.ViewCound.AddViewCount;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.LikeDislikeDb;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.ShapeType;

public class PostPreviewActivity extends AppCompatActivity {

    private TextView look_txt_counts, promotion_tit_txt, prom_desc_text, like_count_txt, dis_like_count_txt;
    private RoundedImageView promotion_img;
    private ImageView close_promotion_img, dislike_img, like_img;
    private ImageView back_img_promotion;
    private String title = "", desc = "", img = "", view_count = "", percen_count = "", insta = "";
    private Integer id;
    private NeumorphCardView fig_up_card, fig_down_card, share_card, fig_insta_card;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);
        makeStatusbarTransparent();
        initComponents();
        setFonts();
        setListener();
        setLike();
        setRequestPost();

    }

    private void setRequestPost() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String token = "Bearer " + Utils.getSharePreferences(PostPreviewActivity.this, "token");
        Integer user_id = Integer.parseInt(Utils.getSharePreferences(PostPreviewActivity.this, "user_id"));
        String type = "post";
        AddViewCount addViewCount = new AddViewCount(id, type, user_id);
        Call<ResponseBody> responseBodyCall = apiInterface.add_view_count(token, addViewCount);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    look_txt_counts.setText(view_count);
                } else {
                    Utils.showCustomToast(getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            PostPreviewActivity.this,
                            R.color.no_internet_back);
                    Log.e("Error ", response.code() + "");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.showCustomToast(getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        PostPreviewActivity.this,
                        R.color.no_internet_back);
            }
        });
    }

    private void setLike() {
        LikeDislikeDb likeDislikeDb = new LikeDislikeDb(PostPreviewActivity.this);
        Cursor cursor = likeDislikeDb.getCountFirst(id + "", "like");
        if (cursor.getCount() > 0) {
            fig_up_card.setShapeType(ShapeType.PRESSED);
            like_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.aply_text_color)));
        } else {
            fig_up_card.setShapeType(0);
            like_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_color)));
        }

        cursor = likeDislikeDb.getCountFirst(id + "", "dislike");
        if (cursor.getCount() > 0) {
            fig_down_card.setShapeType(ShapeType.PRESSED);
            dislike_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.aply_text_color)));

        } else {
            fig_down_card.setShapeType(0);
            dislike_img.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_color)));
        }
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
        id = getIntentProm.getIntExtra("ID", 0);
        view_count = getIntentProm.getStringExtra("VIEW_COUNT");
        percen_count = getIntentProm.getStringExtra("PROMO_PRECENT");
        insta = getIntentProm.getStringExtra("INSTA");
        prom_desc_text.setText(desc);
        promotion_tit_txt.setText(title);
        look_txt_counts.setText(view_count);
        Glide.with(this).load(img)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(promotion_img);
        Glide.with(this).asBitmap().listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                Bitmap blur = Utils.blurRenderScript(resource, 20, PostPreviewActivity.this);
                back_img_promotion.setImageBitmap(blur);
                return true;
            }
        }).load(img)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(back_img_promotion);

        fig_up_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLikeDislike(id, LikeDislikeDb.LIKE);
            }
        });

        fig_down_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLikeDislike(id, LikeDislikeDb.DISLIKE);
            }
        });

        share_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostPreviewActivity.this, ShareActivity.class);
                intent.putExtra("IMG", img);
                intent.putExtra("TITLE", title);
                intent.putExtra("PROMO_PRECENT", percen_count);
                intent.putExtra("TYPE", "promo");
                startActivity(intent);
            }
        });

        fig_insta_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(insta);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(insta)));
                }
            }
        });


    }

    private void sendLikeDislike(Integer id, String type) {
        LikeDislikeDb likeDislikeDb = new LikeDislikeDb(PostPreviewActivity.this);
        Cursor cursor = likeDislikeDb.getCount(id, type);
        if (cursor.getCount() > 0) {
            return;
        }
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String table_type = "post";
        PostLikeDislike postLikeDislike = new PostLikeDislike(id, type, table_type);
        Call<ResponseBody> responseBodyCall = apiInterface.add_like_dislike(postLikeDislike);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    likeDislikeDb.insert(id, type);
                    setLike();
                } else {
                    Log.e("Error ", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
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
        fig_up_card = findViewById(R.id.fig_up_card);
        fig_down_card = findViewById(R.id.fig_down_card);
        like_img = findViewById(R.id.like_img);
        dislike_img = findViewById(R.id.dislike_img);
        share_card = findViewById(R.id.share_card);
        fig_insta_card = findViewById(R.id.fig_insta_card);
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