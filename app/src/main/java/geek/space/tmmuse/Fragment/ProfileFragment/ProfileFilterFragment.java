package geek.space.tmmuse.Fragment.ProfileFragment;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Adapter.Tags.TagsAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.AllProfile.ResponseAllProfile;
import geek.space.tmmuse.Model.Tags_Filter_Btn.Tags_Btn;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class ProfileFilterFragment extends Fragment {
    private View view;
    private Context context;

    public static ArrayList<Tags_Btn> tags_btns = new ArrayList<>();
    private TagsAdapter tagsAdapter;
    private RecyclerView tags_rec;

    private TextView filt_shop_txt, clear_txt, tags_txt, sort_txt;
    private NeumorphButton latest_button, oldest_button;
    private ApiInterface apiInterface;
    private LinearLayout filterButton;
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
        setListener();
        return view;
    }

    private void setListener() {
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profiles.page=1;
                Profiles.setProfileListRequest(1, context, Profiles.get().getProgress_bar());
            }
        });

        latest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latest_button.setShapeType(2);
                latest_button.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                oldest_button.setShapeType(0);
                oldest_button.setTextColor(context.getResources().getColor(R.color.tex_color_btn_search));
                Profiles.sort = 0;
            }
        });

        oldest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldest_button.setShapeType(2);
                oldest_button.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                latest_button.setShapeType(0);
                latest_button.setTextColor(context.getResources().getColor(R.color.tex_color_btn_search));
                Profiles.sort = 1;
            }
        });
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
        filterButton = view.findViewById(R.id.filterButton);
    }

    private void setTagAdapter() {
        tagsAdapter = new TagsAdapter(context, tags_btns);
        tags_rec.setAdapter(tagsAdapter);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        tags_rec.setLayoutManager(mLayoutManager);
    }

    private void setTagsList() {
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);

    }
}