package geek.space.tmmuse.Fragment.CategoryFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.R;


public class CategoryFragment extends Fragment {
    private View view;
    private Context context;

    private TextView category_txt, afisha_txt, intersting_txt,
            caf_res_txt, shop_txt, beauty_sport_txt, news_txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        context = getContext();
        initComponents();
        setListener();
        setFonts();
        return view;
    }

    private void initComponents() {
        category_txt = view.findViewById(R.id.category_txt);
        afisha_txt = view.findViewById(R.id.afisha_txt);
        intersting_txt = view.findViewById(R.id.intersting_txt);
        caf_res_txt = view.findViewById(R.id.caf_res_txt);
        shop_txt = view.findViewById(R.id.shop_txt);
        beauty_sport_txt = view.findViewById(R.id.beauty_sport_txt);
        news_txt = view.findViewById(R.id.news_txt);

    }

    private void setFonts() {
        category_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        afisha_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        intersting_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        caf_res_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        shop_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        beauty_sport_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        news_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
    }

    private void setListener() {
        view.findViewById(R.id.afish_card).setOnClickListener(view -> gotoProfile(view));
        view.findViewById(R.id.interesting_card).setOnClickListener(view -> gotoProfile(view));
        view.findViewById(R.id.caf_res_card).setOnClickListener(view -> gotoProfile(view));
        view.findViewById(R.id.shop_card).setOnClickListener(view -> gotoProfile(view));
        view.findViewById(R.id.beauty_sport_card).setOnClickListener(view -> gotoProfile(view));
        view.findViewById(R.id.news_card).setOnClickListener(view -> gotoProfile(view));
    }

    public void gotoProfile(View view) {
        switch (view.getId()) {
            case R.id.afish_card:
                Utils.gotoProfiles(getResources().getString(R.string.afisha), 1, getFragmentManager());
                break;
            case R.id.interesting_card:
                Utils.gotoProfiles(getResources().getString(R.string.interesting), 2, getFragmentManager());
                break;
            case R.id.caf_res_card:
                Utils.gotoProfiles(getResources().getString(R.string.cafe_res), 3, getFragmentManager());
                break;
            case R.id.shop_card:
                Utils.gotoProfiles(getResources().getString(R.string.shops), 4, getFragmentManager());
                break;
            case R.id.beauty_sport_card:
                Utils.gotoProfiles(getResources().getString(R.string.beauty_sport), 5, getFragmentManager());
                break;
            case R.id.news_card:
                Utils.gotoProfiles(getResources().getString(R.string.news), 6, getFragmentManager());
                break;

        }
    }


}


