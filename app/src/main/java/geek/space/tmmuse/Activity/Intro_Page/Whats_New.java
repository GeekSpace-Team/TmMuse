package geek.space.tmmuse.Activity.Intro_Page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import geek.space.tmmuse.Adapter.Whats_new_list_adapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.R;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import soup.neumorphism.NeumorphButton;

public class Whats_New extends AppCompatActivity {

    private TextView whats_news_txt, in_func_txt, in_design_txt;
    private NeumorphButton skip_btn;
    private ArrayList<String> first = new ArrayList<>();
    private ArrayList<String> second = new ArrayList<>();
    private NestedScrollView scroll;
    private Context context = this;
    RecyclerView design_list, function_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whats__new);

        initComponents();
        initArrayList();
        setAdapters();
        setListeners();
        setFons();


    }


    private void initComponents() {

        design_list = findViewById(R.id.desig_list_view);
        function_list = findViewById(R.id.function_list_view);
        skip_btn = findViewById(R.id.skip_btn);
        whats_news_txt = findViewById(R.id.whats_news_txt);
        in_design_txt = findViewById(R.id.in_design_txt);
        in_func_txt = findViewById(R.id.in_func_txt);

    }

    private void setListeners() {
        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setPressed(skip_btn, 2, R.color.aply_text_color, context);
                finish();
                startActivity(new Intent(getApplicationContext(), Perrmision_Activity.class));
            }
        });
    }

    private void setAdapters() {
        design_list.setAdapter(new Whats_new_list_adapter(this, first));
        design_list.setLayoutManager(new LinearLayoutManager(this));
        function_list.setAdapter(new Whats_new_list_adapter(this, second));
        function_list.setLayoutManager(new LinearLayoutManager(this));
        design_list.setNestedScrollingEnabled(false);
        function_list.setNestedScrollingEnabled(false);
    }

    private void setFons() {
        whats_news_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        in_design_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        skip_btn.setTypeface(Font.getInstance(this).getMontserrat_500());
    }


    private void initArrayList() {
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");
        first.add("I accept the terms of personal data processing");


        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
        second.add("I accept the terms of personal data processing");
    }





}