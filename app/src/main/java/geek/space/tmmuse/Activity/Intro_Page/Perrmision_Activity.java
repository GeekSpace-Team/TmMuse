package geek.space.tmmuse.Activity.Intro_Page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import geek.space.tmmuse.Activity.Sig_Up.Sig_Up_Activity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class Perrmision_Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 1;
    private static final int STORAGE_PERMISSION_CODE = 2;


    TextView desc_permission_txt, perrmision_txt, location_txt, location_desc_txt,
            storage_txt, storage_desc_txt;
    private Context context = this;
    private NeumorphButton skip_btn, storage_allowed_btn, location_allowed_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perrmision);

        initComponents();
        setFonts();
        setListeners();


    }

    private void setListeners() {
        storage_allowed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
            }
        });
        location_allowed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_CODE_PERMISSIONS);
            }
        });
        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setPressed(skip_btn, 2, R.color.aply_text_color, context);
                finish();
                startActivity(new Intent(getApplicationContext(), Sig_Up_Activity.class));
            }
        });
    }

    private void setFonts() {
        perrmision_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        desc_permission_txt.setTypeface(Font.getInstance(this).getMontserrat_400());
        location_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        location_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        storage_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        storage_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        skip_btn.setTypeface(Font.getInstance(this).getMontserrat_500());
        storage_allowed_btn.setTypeface(Font.getInstance(this).getMontserrat_700());
        location_allowed_btn.setTypeface(Font.getInstance(this).getMontserrat_700());
    }

    private void initComponents() {
        perrmision_txt = findViewById(R.id.perrmision_txt);
        desc_permission_txt = findViewById(R.id.desc_permission_txt);
        location_txt = findViewById(R.id.location_txt);
        location_desc_txt = findViewById(R.id.location_desc_txt);
        storage_txt = findViewById(R.id.storage_txt);
        storage_desc_txt = findViewById(R.id.storage_desc_txt);
        skip_btn = findViewById(R.id.skip_btn);
        location_allowed_btn = findViewById(R.id.location_allowed_btn);
        storage_allowed_btn = findViewById(R.id.storage_allowed_btn);
    }

    private void checkPermission(String writeExternalStorage, int storagePermissionCode) {
        if (ContextCompat.checkSelfPermission(Perrmision_Activity.this, writeExternalStorage) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(Perrmision_Activity.this, new String[]{writeExternalStorage}, storagePermissionCode);
        } else {
            if (storagePermissionCode == REQUEST_CODE_PERMISSIONS)
                allowLocation(true);
            if (storagePermissionCode == STORAGE_PERMISSION_CODE)
                allowStorage(true);

        }
    }

    private void allowLocation(boolean b) {
        if (b) {
            location_allowed_btn.setShapeType(2);
            location_allowed_btn.setTextColor(getResources().getColor(R.color.aply_text_color));
        } else {
            location_allowed_btn.setShapeType(0);
            location_allowed_btn.setTextColor(getResources().getColor(R.color.text_color));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                allowStorage(true);
            } else {
                allowStorage(false);
            }
        }

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                allowLocation(true);
            } else {
                allowLocation(false);
            }
        }

    }

    private void allowStorage(boolean b) {
        if (b) {
            storage_allowed_btn.setShapeType(2);
            storage_allowed_btn.setTextColor(getResources().getColor(R.color.aply_text_color));
        } else {
            storage_allowed_btn.setShapeType(0);
            storage_allowed_btn.setTextColor(getResources().getColor(R.color.text_color));
        }
    }



}