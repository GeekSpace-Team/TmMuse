package geek.space.tmmuse.Activity.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.InterestPage.InterestAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Interest.Interest;
import geek.space.tmmuse.Model.Interest.SubInterest;
import geek.space.tmmuse.R;

public class Interest_Activity extends AppCompatActivity {
    private TextView select_int_txt, next_txt;
    private ArrayList<Interest> interests = new ArrayList<>();
    private ArrayList<SubInterest> subInterests1= new ArrayList<>();
    private ArrayList<SubInterest> subInterests2= new ArrayList<>();
    private ArrayList<SubInterest> subInterests3= new ArrayList<>();
    private ArrayList<SubInterest> subInterests4= new ArrayList<>();
    private ArrayList<SubInterest> subInterests5= new ArrayList<>();
    private ArrayList<SubInterest> subInterests6= new ArrayList<>();
    private InterestAdapter interestAdapter;
    private RecyclerView interestRec;
    public static ArrayList<Integer> selectedInterests=new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        initComponents();
        setFont();
        getLang();
        setInterestList();
        setIneterstAdapter();
    }

    private void setInterestList() {
        interests.clear();
        interests.add(new Interest("Kafe", subInterests1));
        interests.add(new Interest("Sport", subInterests2));
        interests.add(new Interest("Restoran", subInterests3));
        interests.add(new Interest("Film", subInterests4));
        interests.add(new Interest("Krasota", subInterests5));
        interests.add(new Interest("Teatr", subInterests6));

        subInterests1.clear();
        subInterests1.add(new SubInterest(1, "Altyn Acar"));
        subInterests1.add(new SubInterest(2, "Kopetdag"));
        subInterests1.add(new SubInterest(3, "AKenar"));
        subInterests1.add(new SubInterest(4, "Altyn Suw"));

        subInterests2.clear();
        subInterests2.add(new SubInterest(5, "Futbol"));
        subInterests2.add(new SubInterest(6, "Hokey"));
        subInterests2.add(new SubInterest(7, "Yenil Atletika"));
        subInterests2.add(new SubInterest(8, "Tennis"));
        subInterests2.add(new SubInterest(9, "Stol Tennis"));

        subInterests3.clear();
        subInterests3.add(new SubInterest(10, "Toy Mekany"));
        subInterests3.add(new SubInterest(11, "Gulzemin"));
        subInterests3.add(new SubInterest(12, "Altyn Yyldyz"));
        subInterests3.add(new SubInterest(13, "SKopetdag"));
        subInterests3.add(new SubInterest(14, "Gul Zaman"));

        subInterests4.clear();
        subInterests4.add(new SubInterest(15, "Altyn Acar"));
        subInterests4.add(new SubInterest(16, "Kopetdag"));
        subInterests4.add(new SubInterest(17, "AKenar"));
        subInterests4.add(new SubInterest(18, "Altyn Suw"));

        subInterests5.clear();
        subInterests5.add(new SubInterest(19, "Futbol"));
        subInterests5.add(new SubInterest(20, "Hokey"));
        subInterests5.add(new SubInterest(30, "Yenil Atletika"));
        subInterests5.add(new SubInterest(40, "Tennis"));
        subInterests5.add(new SubInterest(50, "Stol Tennis"));

        subInterests6.clear();
        subInterests6.add(new SubInterest(180, "Toy Mekany"));
        subInterests6.add(new SubInterest(200, "Gulzemin"));
        subInterests6.add(new SubInterest(300, "Altyn Yyldyz"));
        subInterests6.add(new SubInterest(400, "SKopetdag"));
        subInterests6.add(new SubInterest(500, "Gul Zaman"));
    }

    private void setIneterstAdapter() {
        interestAdapter = new InterestAdapter(this, interests);
        interestRec.setLayoutManager(new LinearLayoutManager(this));
        interestRec.setAdapter(interestAdapter);

    }


    private void setFont() {
        select_int_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        select_int_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
    }

    private void initComponents() {
        select_int_txt = findViewById(R.id.select_int_txt);
        next_txt = findViewById(R.id.next_txt);
        interestRec = findViewById(R.id.interestRec);
    }


    public void go_main_menu(View view) {
        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
        finish();
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

}