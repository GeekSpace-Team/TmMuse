package geek.space.tmmuse.Activity.ShareActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhy.base.fileprovider.FileProvider7;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;

public class ShareActivity extends AppCompatActivity {
    private ImageView prom_img, icon;
    private TextView prom_name_txt, prom_precent_text;
    private String img = "", title = "", pro_cent = "", type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initComponents();
        setFont();
        setShareList();
    }

    private void setShareList() {
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        pro_cent = intent.getStringExtra("PROMO_PRECENT");
        type = intent.getStringExtra("TYPE");
        img = intent.getStringExtra("IMG");
        Glide.with(this).load(img)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(prom_img);
        if (type.equals("promo")){
            icon.setVisibility(View.VISIBLE);
            prom_name_txt.setText(title);
            prom_precent_text.setText(pro_cent);
        } else {
            icon.setVisibility(View.GONE);
            prom_name_txt.setText(title);
            prom_precent_text.setText(pro_cent + "TMT");
        }
    }

    private void setFont() {
        prom_name_txt.setTypeface(Font.getInstance(ShareActivity.this).getMontserrat_800());
        prom_precent_text.setTypeface(Font.getInstance(ShareActivity.this).getMontserrat_700());
    }

    private void initComponents() {
        prom_img = findViewById(R.id.prom_img);
        prom_name_txt = findViewById(R.id.prom_name_txt);
        prom_precent_text = findViewById(R.id.prom_precent_text);
        icon = findViewById(R.id.icon);
    }

    public void onBack(View view) {
        onBackPressed();
        finish();
    }

    private void saveImage(Bitmap bitmap, @NonNull String name) throws IOException {
        OutputStream fos;
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name + ".jpg");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(Objects.requireNonNull(uri));
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            File image = new File(imagesDir, name + ".jpg");
            fos = new FileOutputStream(image);
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        Objects.requireNonNull(fos).close();

    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    private void save_toMediaStore() {
        Bitmap bitmap = loadBitmapFromView(findViewById(R.id.con));
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);

        try {
            saveImage(bitmap, n + "");
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String type = mime.getMimeTypeFromExtension("image/*");

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), n + ".jpg");
            Uri uri= FileProvider7.getUriForFile(ShareActivity.this, file);
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("image/jpg");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(shareIntent, "Select"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            save_toMediaStore();
        }
    }

    public void share(View v){
        if (ActivityCompat.checkSelfPermission(ShareActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShareActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    11);

        } else {
            save_toMediaStore();
        }
    }

}