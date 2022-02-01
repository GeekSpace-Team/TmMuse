package geek.space.tmmuse.Fragment.ProfileFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import geek.space.tmmuse.Adapter.Tags.TagsAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Tags_Filter_Btn.Tags_Btn;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class ProfileFilterFragment extends Fragment {
    private View view;
    private Context context;

    private ArrayList<Tags_Btn> tags_btns = new ArrayList<>();
    private TagsAdapter tagsAdapter;
    private RecyclerView tags_rec;

    private TextView filt_shop_txt, clear_txt, tags_txt, sort_txt;
    private NeumorphButton latest_button, oldest_button;

    public ProfileFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_filter, container, false);
        context = getContext();
        initComponents();
        setFonts();
        setTagsList();
        setTagAdapter();
        return view;
    }

    private void setFonts() {
        filt_shop_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        clear_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        tags_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        sort_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        latest_button.setTypeface(Font.getInstance(context).getMontserrat_700());
        oldest_button.setTypeface(Font.getInstance(context).getMontserrat_700());
    }

    private void initComponents() {
        tags_rec = view.findViewById(R.id.tags_rec);
        filt_shop_txt = view.findViewById(R.id.filt_shop_txt);
        clear_txt = view.findViewById(R.id.clear_txt);
        tags_txt = view.findViewById(R.id.tags_txt);
        sort_txt = view.findViewById(R.id.sort_txt);
        latest_button = view.findViewById(R.id.latest_button);
        oldest_button = view.findViewById(R.id.oldest_button);
    }

    private void setTagAdapter() {
        tagsAdapter = new TagsAdapter(context, tags_btns);
        tags_rec.setAdapter(tagsAdapter);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        tags_rec.setLayoutManager(mLayoutManager);
    }

    private void setTagsList() {
        tags_btns.clear();
        tags_btns.add(new Tags_Btn(1, "Woman"));
        tags_btns.add(new Tags_Btn(2, "Restaurants"));
        tags_btns.add(new Tags_Btn(3, "Sport"));
        tags_btns.add(new Tags_Btn(4, "Cafe"));
        tags_btns.add(new Tags_Btn(5, "Man"));
        tags_btns.add(new Tags_Btn(6, "Restaurant"));
    }
}