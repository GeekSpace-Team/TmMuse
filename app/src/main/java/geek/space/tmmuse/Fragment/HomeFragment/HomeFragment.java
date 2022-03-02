package geek.space.tmmuse.Fragment.HomeFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhpan.indicator.IndicatorView;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;
import com.zhpan.indicator.option.IndicatorOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Activity.SearchActivity.SearchActivity;
import geek.space.tmmuse.Adapter.Banner.ImgCaruselAdapter;
import geek.space.tmmuse.Adapter.FilimAdapter.FilmAdapter;
import geek.space.tmmuse.Adapter.PromotionsPage.PromotionAndOffersAdapter;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.PromotionsOffersFragment.PromotionsOffersFragment;
import geek.space.tmmuse.Model.AllProfile.Popup;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.Model.Film.Film;
import geek.space.tmmuse.Model.Home.Home;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import soup.neumorphism.NeumorphImageButton;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private View view;
    private Context context;
    private TextView weather_tp, all_promotions_txt;
    private TextView weather_desc, nw_film_txt, promotions_txt;
    private RequestQueue requestQueue;
    private ImageView weather_ic;
    private NeumorphImageButton search_btn;
    private String weather_lang;
    private String weatherUrl;
    private String appId = "d3b23cc239784b8b868f26d4220d6581";

    private Handler sliderHandler = new Handler();
    private IndicatorView dots_indicator;
    private ImgCaruselAdapter imgCaruselAdapter;
    private static ViewPager2 img_carusel_view_pager;
    private int dotsCount;
    private RecyclerView rec_films, rec_promotions;
    private ArrayList<Film> filmList = new ArrayList<>();
    private FilmAdapter filmAdapter;
    private ArrayList<Banner> banners = new ArrayList<>();
    private PromotionAndOffersAdapter promotionAndOffersAdapter;
    private ArrayList<PromotionAndOffers> promotionAndOffers = new ArrayList<>();
    private ScrollView scroll;
    private FrameLayout frameLayout;
    private ApiInterface apiInterface;
    private Integer page = 1;
    private boolean isFirst = true;
    private Dialog popup_in_start;
    private final int CLOSE_POPUP = 15000;
    int currentPage = 0;
    private Integer BANNER_DELAY = 5000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();

        initComponents();
        setFonts();
        getTemp();

        request(page);

        return view;
    }



    private void request(int i) {
        KProgressHUD progress = Utils.AppProgressBar(context);
        progress.setLabel(getResources().getString(R.string.wait));
        progress.show();
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);
        Call<Home> call = apiInterface.getHome(i);
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, retrofit2.Response<Home> response) {
                if (response.isSuccessful()) {
                    Home res = response.body();
                    if (page == 1) {
                        filmList = res.getBody().getNew_movies();
                        banners = res.getBody().getBanners();
                        promotionAndOffers = res.getBody().getPromotionAndOffers();
                        if (res.getBody().getPopup() != null && res.getBody().getPopup().size() > 0)
                            showPopup(res.getBody().getPopup());

                        if (res.getBody().getBanners() != null) {
                            setBanners();
                        }
                        if (res.getBody().getNew_movies() != null) {
                            setFilm();
                        }
                        if (res.getBody().getPromotionAndOffers() != null) {
                            setPromotionsAndOffersAdapter();
                        }
                    } else {

                        promotionAndOffers.addAll(res.getBody().getPromotionAndOffers());
                    }
                } else {
                    Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            context,
                            R.color.no_internet_back);
                    Log.e("Code", response.code() + "");
                    Log.e("Error", response.errorBody().toString());
                }
                progress.dismiss();
                setListener();
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void setPromotionsAndOffersAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        rec_promotions.setLayoutManager(mLayoutManager);
        promotionAndOffersAdapter = new PromotionAndOffersAdapter(context, promotionAndOffers);
        rec_promotions.setAdapter(promotionAndOffersAdapter);
        rec_promotions.setHasFixedSize(true);
        rec_promotions.setNestedScrollingEnabled(false);

    }

    private void setFilm() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rec_films.setLayoutManager(layoutManager);
        filmAdapter = new FilmAdapter(context, filmList);
        rec_films.setAdapter(filmAdapter);

    }


    private void setListener() {

        all_promotions_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PromotionsOffersFragment promotionsOffersFragment = new PromotionsOffersFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.firstFragment = promotionsOffersFragment;
                Utils.hideAdd(promotionsOffersFragment, promotionsOffersFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SearchActivity.class));
            }
        });

    }


    private void setBanners() {
        setIndicators();
        img_carusel_view_pager.setAdapter(new ImgCaruselAdapter(context, img_carusel_view_pager, banners));
        img_carusel_view_pager.setClipToPadding(false);
        img_carusel_view_pager.setClipChildren(false);
        img_carusel_view_pager.setOffscreenPageLimit(3);
        img_carusel_view_pager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        img_carusel_view_pager.setPageTransformer(compositePageTransformer);

        img_carusel_view_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, BANNER_DELAY); // slide duration 2 seconds
                dots_indicator.onPageSelected(banners.get(position).getPosition());
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                dots_indicator.onPageScrolled(banners.get(position).getPosition(), positionOffset, positionOffsetPixels);
            }
        });



    }

    private void setIndicators() {
        Integer pos = 0;
        for (int i = 0; i < banners.size(); i++) {
            Banner banner = banners.get(i);
            banner.setPosition(pos);
            banners.set(i, banner);
            pos++;
        }

        IndicatorOptions options = new IndicatorOptions();
        options.setSliderColor(context.getResources().getColor(R.color.text_color), context.getResources().getColor(R.color.aply_text_color));
        options.setSliderHeight(context.getResources().getDimension(R.dimen.sliderCircle));
        options.setSliderWidth(context.getResources().getDimension(R.dimen.sliderCircle), context.getResources().getDimension(R.dimen.sliderRound));
        options.setSlideMode(IndicatorSlideMode.WORM);
        options.setIndicatorStyle(IndicatorStyle.CIRCLE);
        options.setPageSize(banners.size());
        dots_indicator.setIndicatorOptions(options);
        dots_indicator.notifyDataChanged();
    }

    private void setFonts() {
        weather_tp.setTypeface(Font.getInstance(context).getMontserrat_800());
        weather_desc.setTypeface(Font.getInstance(context).getMontserrat_700());
        nw_film_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
        promotions_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
    }

    private void initComponents() {
        weather_tp = view.findViewById(R.id.weather_tp);
        weather_desc = view.findViewById(R.id.weather_desc);
        weather_ic = view.findViewById(R.id.weather_ic);
        dots_indicator = view.findViewById(R.id.dots_indicator);
        img_carusel_view_pager = view.findViewById(R.id.img_view_pager);
        rec_films = view.findViewById(R.id.rec_films);
        nw_film_txt = view.findViewById(R.id.nw_film_txt);
        promotions_txt = view.findViewById(R.id.promotions_txt);
        rec_promotions = view.findViewById(R.id.rec_promotions);
        scroll = view.findViewById(R.id.scroll);
        frameLayout = view.findViewById(R.id.root);
        search_btn = view.findViewById(R.id.search_btn);
        all_promotions_txt = view.findViewById(R.id.all_promotions_txt);
        popup_in_start = new Dialog(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, BANNER_DELAY);
        getTemp();
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
        getTemp();
    }


    private void getTemp() {
        weather_tp.setText("0" + "°");
        weather_tp.setText(getResources().getText(R.string.wait_to_weather));

        Location location = new Location("");
        location.setLatitude(37.862499);
        location.setLongitude(58.238056);

        // Log.d("Lat", location.getLatitude() + "," + location.getLongitude());
        SharedPref sharedPref = new SharedPref(context);
        weather_lang = sharedPref.getString("My_Lang", "");
        if (weather_lang.equals(""))
            weather_lang = "ru";
        weatherUrl = "https://api.weatherbit.io/v2.0/current?" + "lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&key=" + appId + "&lang=" + weather_lang;
        requestQueue = Volley.newRequestQueue(context);


        // Request a string response from the provided URL.
        final Location finalLocation = location;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, weatherUrl,
                response -> {
                    // Display the first 500 characters of the response string.
                    try {

                        JSONObject obj = new JSONObject(response);
                        JSONArray arr = obj.getJSONArray("data");

                        JSONObject obj2 = arr.getJSONObject(0);
                        JSONObject weatherArray = obj2.getJSONObject("weather");

                        weather_tp.setText(obj2.getString("temp") + ("°"));
                        weather_desc.setText(weatherArray.getString("description"));
                        loadImageFromAssets("weather_icons/" + weatherArray.getString("icon") + ".png");
                        // load image


                        Log.d("WEATHER", weatherArray.getString("description"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("WEATHER", response);
                }, error -> {
            try {
                weather_tp.setText("0" + "°");
                weather_desc.setText(getResources().getString(R.string.wait_to_weather));
                weather_desc.setTextColor(Color.RED);
                weather_tp.setTextColor(Color.RED);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        stringRequest.setTag(TAG);

        requestQueue.add(stringRequest);


    }

    public void loadImageFromAssets(String path) {
        try {
            // get input stream
            InputStream ims = context.getAssets().open(path);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            weather_ic.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
    }

    private void showPopup(ArrayList<Popup> popups) {
        if (isFirst) {
            popup_in_start.requestWindowFeature(Window.FEATURE_NO_TITLE);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.popup_in_start_app, null, false);
            popup_in_start.setContentView(view);

            TextView popup_tit_txt, popup_desc_text;
            RoundedImageView popup_img;
            ImageView back_img_popup, close_popup_img;
            Button more_btn = popup_in_start.findViewById(R.id.more_btn);
            more_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (popups.get(0).getProfile_id() != null && popups.get(0).getProfile_id() != 0){
                        Utils.add_click_count(popups.get(0).getId(), "popup", context);
                        Intent intent = new Intent(context, AllProductViewsActivity.class);
                        intent.putExtra("ID", popups.get(0).getProfile_id());
                        context.startActivity(intent);
                        return;
                    } else if (popups.get(0).getSite_url()!=null){
                        Utils.add_click_count(popups.get(0).getId(), "popup", context);
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        if (popups.get(0).getSite_url()!=null){
                        intent.setData(Uri.parse(popups.get(0).getSite_url()));
                        }
                        context.startActivity(intent);
                    }
                }
            });
            popup_img = popup_in_start.findViewById(R.id.popup_img);
            back_img_popup = popup_in_start.findViewById(R.id.back_img_popup);
            close_popup_img = popup_in_start.findViewById(R.id.close_popup_img);
            popup_tit_txt = popup_in_start.findViewById(R.id.popup_tit_txt);
            popup_desc_text = popup_in_start.findViewById(R.id.popup_desc_text);


            popup_desc_text.setTypeface(Font.getInstance(context).getMontserrat_400());
            popup_tit_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
            if (popups.get(0).getTitleTM() != null || popups.get(0).getTitleRU() != null) {
                popup_tit_txt.setText(popups.get(0).getTitleTM());
                popup_desc_text.setText(popups.get(0).getDescriptionTM());
                if (Utils.getLanguage(context).equals("ru")) {
                    popup_tit_txt.setText(popups.get(0).getTitleRU());
                    popup_desc_text.setText(popups.get(0).getDescriptionRU());
                }
            }
            try {
                Glide.with(context).load(Constant.BASE_URL_IMAGE + popups.get(0).getImage())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                        .into(popup_img);
                Glide.with(context).asBitmap().listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        Bitmap blur = Utils.blurRenderScript(resource, 20, context);
                        back_img_popup.setImageBitmap(blur);
                        return true;
                    }
                }).load(Constant.BASE_URL_IMAGE + popups.get(0).getImage())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                        .into(back_img_popup);

            } catch (Exception e) {
                e.printStackTrace();
            }


            close_popup_img.setOnClickListener(view -> popup_in_start.dismiss());

            final Window window = popup_in_start.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(R.drawable.card_gradient);
            window.setGravity(Gravity.CENTER);
            window.getAttributes().windowAnimations = R.style.DialogAnimationPopup;
            popup_in_start.show();


            final Handler handler = new Handler();
            final Runnable runnable = () -> {
                if (popup_in_start.isShowing()) {
                    popup_in_start.dismiss();
                }
            };

            popup_in_start.setOnDismissListener(dialog -> handler.removeCallbacks(runnable));

            handler.postDelayed(runnable, CLOSE_POPUP);

            isFirst = false;
        }
    }


    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            img_carusel_view_pager.setCurrentItem(img_carusel_view_pager.getCurrentItem() + 1);
        }
    };



    @Override
    public void onStop() {
        super.onStop();
        img_carusel_view_pager.setCurrentItem(0);
    }
}