package geek.space.tmmuse.Fragment.ProfileFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.AllProdileAdapters.AllProdfileAdapters;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.CategoryFragment.CategoryFragment;
import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.Model.AllProfile.ResponseAllProfile;
import geek.space.tmmuse.Model.AllProfile.GetProfile;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profiles extends Fragment {

    private View view;
    private Context context;
    public String categoryName;
    public static ArrayList<Integer> categoryID;
    private TextView profile_name, filter_txt;
    private ImageView filter_img, back_to_card;
    public static DrawerLayout draw_profile;
    private NavigationView navigation_filter;
    private RecyclerView profile_rec;
    private ApiInterface apiInterface;
    public static ArrayList<AllProfile> allProfiles = new ArrayList<>();
    private AllProdfileAdapters allProdfileAdapters;
    public static ArrayList<Integer> tags=new ArrayList<>();
    public static Integer limit = 20;
    public static Integer page = 1;
    private static Profiles INSTANCE;
    private ProgressBar progress_bar;
    public static Boolean isLoading=false;
    public static Integer sort=0;
    public static Boolean loadMore=true;


    private int animation = R.anim.layout_animation;


    public Profiles(String categoryName, ArrayList<Integer> categoryID) {
        this.categoryName = categoryName;
        this.categoryID = categoryID;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profiles, container, false);
        context = getContext();
        INSTANCE=this;
        initComponents();
        setListener();
        setFonts();
        setProfileListRequest(page, context, progress_bar);
        navigatioDrawerSet();
        profile_name.setText(categoryName);

        return view;
    }

    public static Profiles get(){
        return INSTANCE;
    }

    public RecyclerView getRec(){
        return profile_rec;
    }

    public FragmentManager getMyFragmentManager(){
        return getFragmentManager();
    }

    public ProgressBar getProgress_bar(){
        return progress_bar;
    }

    public DrawerLayout getDraw_profile(){
        return draw_profile;
    }

    public static void setProfileListRequest(Integer page,Context context, ProgressBar progressBar ) {
        ApiInterface apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);
        GetProfile getProfile = new GetProfile(Profiles.categoryID, Profiles.sort, Profiles.tags, Profiles.limit, page);
        Call<ResponseAllProfile> allProfileCall = apiInterface.get_profile(getProfile);
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
        Profiles.get().getDraw_profile().closeDrawer(GravityCompat.END);
        allProfileCall.enqueue(new Callback<ResponseAllProfile>() {
            @Override
            public void onResponse(Call<ResponseAllProfile> call, Response<ResponseAllProfile> response) {
                if (response.isSuccessful() && response.body().getBody()!=null){
                    ProfileFilterFragment.tags_btns.clear();
                    if(response.body().getBody().getTags()!=null)
                        ProfileFilterFragment.tags_btns=response.body().getBody().getTags();

                    if(response.body().getBody().getProfiles().size()==0){
                        isLoading=false;
                        loadMore=false;
                    }
                    if (page == 1){
                        allProfiles = response.body().getBody().getProfiles();
                        setProfileAdapter(context, Profiles.get().getRec(), response.body().getBody().getProfiles());
                    } else {
                        allProfiles.addAll(response.body().getBody().getProfiles());
                    }

                    try {
                        Profiles.get().getMyFragmentManager().beginTransaction().
                                replace(R.id.frame_filter_layout, new ProfileFilterFragment(), ProfileFilterFragment.class.getSimpleName()).commit();
                    } catch (IllegalStateException ex) {
                        ex.printStackTrace();
                    }
                } else {
                }
                progressBar.setVisibility(View.GONE);
                isLoading = false;
            }

            @Override
            public void onFailure(Call<ResponseAllProfile> call, Throwable t) {
                Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                isLoading=false;
                progressBar.setVisibility(View.GONE);
                Log.e("Error",t.getMessage());
            }
        });
    }

    private void setFonts() {
        profile_name.setTypeface(Font.getInstance(context).getMontserrat_800());
        filter_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
    }

    public static void setProfileAdapter(Context context,RecyclerView recyclerView,ArrayList<AllProfile> allProfiles) {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        AllProdfileAdapters allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
        recyclerView.setAdapter(allProdfileAdapters);

    }



    public void navigatioDrawerSet() {
        navigation_filter.bringToFront();
        navigation_filter.setItemIconTintList(null);
        filter_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (draw_profile.isDrawerVisible(GravityCompat.END)) {
                    draw_profile.closeDrawer(GravityCompat.END);
                } else {
                    draw_profile.openDrawer(GravityCompat.END);
                }
            }
        });

    }

    private void setListener() {

        draw_profile.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Main_Menu.setVisibilityBottomNavigation(getActivity(), View.GONE);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Main_Menu.setVisibilityBottomNavigation(getActivity(), View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        back_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryFragment CategoryFragment = new CategoryFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.secondFragment = new CategoryFragment();
                Utils.removeShow(CategoryFragment, CategoryFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });
        profile_rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)){
                    if (!isLoading && loadMore){
                        loadMore();
                    } else {
                        progress_bar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void initComponents() {
        profile_name = view.findViewById(R.id.profile_name);
        filter_img = view.findViewById(R.id.filter_img);
        progress_bar = view.findViewById(R.id.progress_bar);

        draw_profile = view.findViewById(R.id.draw_profile);
        draw_profile.setScrimColor(getResources().getColor(android.R.color.transparent));
        draw_profile.setDrawerElevation(0);

        navigation_filter = view.findViewById(R.id.navigation_filter);
        back_to_card = view.findViewById(R.id.back_to_card);
        profile_rec = view.findViewById(R.id.profile_rec);
        filter_txt = view.findViewById(R.id.filter_txt);

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(context, animation);
        profile_rec.setLayoutAnimation(animationController);

    }

    private void loadMore() {
        page++;
        setProfileListRequest(page, context, progress_bar);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tags.clear();
        categoryID.clear();
        sort=0;
        page=1;
    }
}