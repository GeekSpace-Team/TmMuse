package geek.space.tmmuse.Fragment.ProfileFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.AllProdileAdapters.AllProdfileAdapters;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.CategoryFragment.CategoryFragment;
import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.R;


public class Profiles extends Fragment {

    private View view;
    private Context context;
    public String categoryName;
    public Integer categoryID;
    private TextView profile_name, filter_txt;
    private ImageView filter_img, back_to_card;
    public static DrawerLayout draw_profile;
    private NavigationView navigation_filter;
    private RecyclerView profile_rec;

    private ArrayList<AllProfile> allProfiles = new ArrayList<>();
    private AllProdfileAdapters allProdfileAdapters;

    private int animation = R.anim.layout_animation;


    public Profiles(String categoryName, Integer categoryID) {
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
        initComponents();
        setListener();
        setFonts();
        navigatioDrawerSet();
        setProfileList();
        setProfileAdapter();
        profile_name.setText(categoryName);

        return view;
    }

    private void setFonts() {
        profile_name.setTypeface(Font.getInstance(context).getMontserrat_800());
        filter_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
    }

    private void setProfileAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        profile_rec.setLayoutManager(layoutManager);
        if (categoryID == 1) {
            allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
            profile_rec.setAdapter(allProdfileAdapters);
        } else if (categoryID == 2) {
            allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
            profile_rec.setAdapter(allProdfileAdapters);
        } else if (categoryID == 3) {
            allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
            profile_rec.setAdapter(allProdfileAdapters);
        } else if (categoryID == 4) {
            allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
            profile_rec.setAdapter(allProdfileAdapters);
        } else if (categoryID == 5) {
            allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
            profile_rec.setAdapter(allProdfileAdapters);
        } else if (categoryID == 6) {
            allProdfileAdapters = new AllProdfileAdapters(context, allProfiles);
            profile_rec.setAdapter(allProdfileAdapters);
        }

    }

    private void setProfileList() {
        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));
        allProfiles.add(new AllProfile(AllProfile.LayoutTwo, 2, "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));

        allProfiles.add(new AllProfile(AllProfile.LayoutOne, 1, "String name_profile", "String desc_profile", "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg",
                "String address_desc", "String work_time_desc", "String payment_desc", "String delivery_desc",
                "String cuisine_desc", "String average_check_desc", "String own_promotion_desc", "String tm_muse_card_desc",
                "String finger_up_count", "String finger_down_count", "String instagram", "String website",
                "String location", "String img_banner"));
//        allProfiles.add(new AllProfile(AllProfile.LayoutTwo, 2, "https://ae04.alicdn.com/kf/H150cb94b559c4b53a24f4405e2b9ff12I/SMTHMA-2021-New-Autumn-And-Winter-Turtleneck-Sweater-Dress-Women-s-Lantern-Sleeve-Warm-Knitted-Long.jpg_220x220xzq55.jpg"));

    }

    private void navigatioDrawerSet() {

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
        try {
            getFragmentManager().beginTransaction().
                    replace(R.id.frame_filter_layout, new ProfileFilterFragment(), ProfileFilterFragment.class.getSimpleName()).commit();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
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
    }

    private void initComponents() {
        profile_name = view.findViewById(R.id.profile_name);
        filter_img = view.findViewById(R.id.filter_img);

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
}