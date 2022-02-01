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

import geek.space.tmmuse.Activity.SearchActivity.SearchActivity;
import geek.space.tmmuse.Adapter.Banner.ImgCaruselAdapter;
import geek.space.tmmuse.Adapter.FilimAdapter.FilmAdapter;
import geek.space.tmmuse.Adapter.PromotionsPage.PromotionAndOffersAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.SharedPref;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.Model.Film.Film;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.R;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import soup.neumorphism.NeumorphImageButton;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private View view;
    private Context context;
    private TextView weather_tp;
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
        setBannersList();
        setBanners();
        setFilmList();
        setFilm();
        setPromotionsAndOffers();
        setPromotionsAndOffersAdapter();

        return view;
    }

    private void setPromotionsAndOffersAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        rec_promotions.setLayoutManager(mLayoutManager);
        promotionAndOffersAdapter = new PromotionAndOffersAdapter(context, promotionAndOffers, scroll, frameLayout);
        rec_promotions.setAdapter(promotionAndOffersAdapter);

    }

    private void setPromotionsAndOffers() {
        promotionAndOffers.clear();
        promotionAndOffers.add(new PromotionAndOffers(1, " 20%", "Turkmen kofe", "https://turkmenportal.com/images/uploads/catalog/2867eea7fc42123de62c998b4c74937c.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 30%", "Turkmen kofe", "https://lotta-tm.com/images/blogs/ammar1.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 40%", "Turkmen kofe", "https://lotta-tm.com/images/blogs/ammar1.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 10%", "Turkmen kofe", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 50%", "Turkmen kofe", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 60%", "Turkmen kofe", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
    }

    private void setFilm() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rec_films.setLayoutManager(layoutManager);
        filmAdapter = new FilmAdapter(context, filmList);
        rec_films.setAdapter(filmAdapter);

    }

    private void setFilmList() {
        filmList.clear();
        filmList.add(new Film(1, "Вечные", "(2021)", "https://kinogo.la/uploads/cache/9/e/1/9/d/d/8/5/5/1636220930-1201942141-vechnye-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Зверопой 2", "(2021)", "https://kinogo.la/uploads/cache/3/5/d/a/b/5/3/7/8/1638386254-1617628670-zveropoy-2-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Обитель зла: Раккун-Сити", "(2021)", "https://kinogo.la/uploads/cache/e/e/e/9/0/d/d/8/d/1636113090-875492897-obitel-zla-rakkun-siti-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Поколение Вояджер", "(2021)", "https://kinogo.la/uploads/cache/2/3/b/2/a/d/1/1/2/1641603587_b4af278d9dfa-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Последняя дуэль", "(2021)", "https://kinogo.la/uploads/cache/d/e/a/6/0/c/b/6/2/1634725023-846939322-poslednyaya-duel-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Охотники за привидениями:", "Наследники (2021)", "https://kinogo.la/uploads/cache/2/6/b/d/8/3/3/5/8/1637527753-1954578497-ohotniki-za-privideniyami-nasledniki-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Чиновник", "(2021)", "https://kinogo.la/uploads/cache/6/f/9/4/e/a/b/2/d/1641296343-1995734972-chinovnik-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Гарри Поттер 20 лет спустя:", "Возвращение в Хогвартс (2022)", "https://kinogo.la/uploads/cache/8/3/6/f/7/8/2/c/b/1641068251-330978045-garri-potter-20-let-spustya-vozvraschenie-v-hogvarts-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));
        filmList.add(new Film(1, "Честный развод", "(2021)", "https://kinogo.la/uploads/cache/e/2/0/8/8/d/0/e/a/1641047722-2115311697-chestnyy-razvod-KINOGO_BY-200x300.jpg", "", "", "", "", "", "", "", "", "", "", "", ""));


    }

    private void setBannersList() {
        banners.clear();
        banners.add(new Banner(1, "https://turkmenportal.com/images/uploads/catalog/2867eea7fc42123de62c998b4c74937c.jpg", null, null));
        banners.add(new Banner(2, "https://media-cdn.tripadvisor.com/media/photo-s/08/0c/7f/a8/2.jpg", null, null));
        banners.add(new Banner(3, "https://lotta-tm.com/images/blogs/ammar1.jpg", "https://lotta-tm.com/images/blogs/ammar1.jpg", null));
        banners.add(new Banner(4, "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "https://lotta-tm.com/images/blogs/ammar1.jpg", null));
        banners.add(new Banner(5, "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", null, null));
        banners.add(new Banner(6, "https://lotta-tm.com/images/blogs/ammar1.jpg", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", null));
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
        OverScrollDecoratorHelper.setUpOverScroll(scroll);
        search_btn.setOnClickListener(view -> startActivity(new Intent(context, SearchActivity.class)));
        timer.scheduleAtFixedRate(new MyTimerTask(), 5000, 5000);
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
                weather_tp.setText("0" + "°");
                weather_desc.setText(getResources().getString(R.string.wait_to_weather));
                weather_desc.setTextColor(Color.RED);
                weather_tp.setTextColor(Color.RED);
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
            if (getActivity() == null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    img_carusel_view_pager.setCurrentItem((img_carusel_view_pager.getCurrentItem() + 1) % banners.size());
                }
            });
        }
    }
}