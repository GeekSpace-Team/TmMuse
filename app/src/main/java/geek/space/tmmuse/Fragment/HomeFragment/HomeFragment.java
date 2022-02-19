package geek.space.tmmuse.Fragment.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

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
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Activity.SearchActivity.SearchActivity;
import geek.space.tmmuse.Adapter.Banner.ImgCaruselAdapter;
import geek.space.tmmuse.Adapter.FilimAdapter.FilmAdapter;
import geek.space.tmmuse.Adapter.PromotionsPage.PromotionAndOffersAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.PromotionsOffersFragment.PromotionsOffersFragment;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.Model.Film.Film;
import geek.space.tmmuse.Model.Home.Home;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.Model.SearchHistory.SearchHistory;
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


    private WormDotsIndicator dots_indicator;
    private ImgCaruselAdapter imgCaruselAdapter;
    private static ViewPager img_carusel_view_pager;
    private int dotsCount;
    private Timer timer = new Timer();
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
        setListener();
        request(page);

        return view;
    }

    private void request(int i) {
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
                    Log.e("Code", response.code() + "");
                    Log.e("Error", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
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

    private void setPromotionsAndOffers() {
    }

    private void setFilm() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rec_films.setLayoutManager(layoutManager);
        filmAdapter = new FilmAdapter(context, filmList);
        rec_films.setAdapter(filmAdapter);

    }

    private void setFilmList() {
    }

    private void setBannersList() {
    }


    private void setListener() {
        img_carusel_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots_indicator.setDotsColor(getResources().getColor(R.color.card_background));
                }
                dots_indicator.setDotsColor(getResources().getColor(R.color.aply_text_color));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        imgCaruselAdapter = new ImgCaruselAdapter(context, banners, img_carusel_view_pager);
        img_carusel_view_pager.setAdapter(imgCaruselAdapter);
        dots_indicator.setViewPager(img_carusel_view_pager);

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


    }

    @Override
    public void onResume() {
        super.onResume();
        getTemp();
    }

    @Override
    public void onPause() {
        super.onPause();
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
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    weather_tp.setText("0" + "°");
                    weather_desc.setText(getResources().getString(R.string.wait_to_weather));
                    weather_desc.setTextColor(Color.RED);
                    weather_tp.setTextColor(Color.RED);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
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

    // AutoScroll Banner Carusel
    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (getActivity() == null || banners == null) {
                return;
            }
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    img_carusel_view_pager.setCurrentItem((img_carusel_view_pager.getCurrentItem() + 1) % banners.size());
//                }
//            });
        }
    }
}