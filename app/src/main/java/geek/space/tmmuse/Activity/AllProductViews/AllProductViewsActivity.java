package geek.space.tmmuse.Activity.AllProductViews;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.ShareActivity.ShareActivity;
import geek.space.tmmuse.Activity.Sig_Up.Sig_Up_Activity;
import geek.space.tmmuse.Activity.VrImage.VrImageActivity;
import geek.space.tmmuse.Adapter.FilimAdapter.BroneData_adapter;
import geek.space.tmmuse.Adapter.FilimAdapter.BroneTimeAdapter;
import geek.space.tmmuse.Adapter.GalleryAdapter.GalleryAdapter;
import geek.space.tmmuse.Adapter.ProfilePhoneAdapter.ProfilePhoneAdapter;
import geek.space.tmmuse.Adapter.PromotionsPage.PromotionAndOffersAdapter;
import geek.space.tmmuse.Adapter.TestAdapterViewPager.TestAdapterViewPager;
import geek.space.tmmuse.Common.AppAlert;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.Model.AllProfile.GetProfileTiny;
import geek.space.tmmuse.Model.AllProfile.ImgProfile;
import geek.space.tmmuse.Model.AllProfile.ProfilePhone;
import geek.space.tmmuse.Model.Certificate.InsertCertificate;
import geek.space.tmmuse.Model.Film.BronMovie;
import geek.space.tmmuse.Model.Film.MovieTime;
import geek.space.tmmuse.Model.Film.RequestBronFilm;
import geek.space.tmmuse.Model.GetPromoCode.GetPromoCodeBody;
import geek.space.tmmuse.Model.GetPromoCode.GetPromoCodes;
import geek.space.tmmuse.Model.LikeDislike.PostLikeDislike;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.LikeDislikeDb;
import geek.space.tmmuse.View.AppBarStateChangeListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class AllProductViewsActivity extends AppCompatActivity {

    private View view;
    private RelativeLayout sliderContainer, view_bottom_rel;
    private ViewPager all_views_viewPager;
    private WormDotsIndicator dots_indicator;
    private Toolbar tool_bar;
    private TextView product_text_count_down, product_text_call, product_text_instagram,
            product_text_web, product_text_location, certificate_txt, promo_txt,
            address_txt, address_desc_txt, deliver_desc_txt, average_check_txt, own_promotion_desc_txt,
            gallery_txt, post_txt, name_profile_txt, product_text_count_up, payment_txt,
            deliver_txt, average_check_desc_txt, look_txt_counts, film_desc_txt, film_desc_all_desc_txt,
            film_time_txt, payment_desc_txt, work_hours_txt, work_hours_desc_txt, cuisine_txt, cuisine_desc_txt,
            own_promotion_txt, tm_muse_card_txt, tm_muse_card_desc_txt, wifi_txt, wifi_desc_txt;
    private LinearLayout certificate_layout, promo_layout, images_layout,
            call_layout_products, instagram_layout, fing_up_layout, fing_down_layout, brone_movie_layout,
            site_layout, location_layout, film_desc_layout, film_time_layout, payment_layout, work_time_another_layout,
            deliver_layout, cuisine_layout, average_check_layout, own_promotion_layout, tm_muse_card_layout, gallery_layout,
            wifi_layout, post_layout;
    private View view_Stick;
    private HorizontalScrollView img_horizontal_scroll;
    private RecyclerView post_rec, movie_time_rec, images_profile_rec, gallery_rec;
    private ScrollView prom_scroll;
    private FrameLayout root_prom;
    private NestedScrollView bottomsheet;
    private RoundedImageView vr_img;
    private ImageView fing_up_img_products, fig_down_img_products, share_img_profile;

    private static ApiInterface apiInterface;

    private TestAdapterViewPager imagesAdapter;
    private PromotionAndOffersAdapter promotionAndOffersAdapter;

    private ArrayList<ImgProfile> imgProfiles = new ArrayList<>();
    private ArrayList<AllProfile> allProfiles = new ArrayList<>();
    private ArrayList<ProfilePhone> profilePhones = new ArrayList<>();
    private ArrayList<TestModelViewPager> testModelViewPagers = new ArrayList<>();
    private ArrayList<MovieTime> movieTimes = new ArrayList<>();
    private ArrayList<PromotionAndOffers> promotionAndOffers = new ArrayList<>();


    private String profileId = "", imageUrl = "";
    private Integer id;
    private String count_tickets;
    private int dotsCount;
    float oldOffSet = 0f;
    private boolean inRangeExpanding;
    private boolean inRangeCollapsing;
    private int string_to_int;
    private String largeVrImageUrl = "";
    AllProfile profile = null;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_all_product_views);
        initViews();
        setFont();
        getLang();
        setAllProfileList();
        setLike();
    }

    private void setAllProfileList() {
        KProgressHUD progress = Utils.AppProgressBar(this);
        progress.setLabel(this.getResources().getString(R.string.wait));
        progress.show();
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);
        Call<GetProfileTiny> getProfileTinyCall = apiInterface.get_profile_tiny(id + "");
        getProfileTinyCall.enqueue(new Callback<GetProfileTiny>() {
            @Override
            public void onResponse(Call<GetProfileTiny> call, Response<GetProfileTiny> response) {
                if (response.isSuccessful() || response.body().getBody() != null) {
                    Log.e("Log", response.body().toString());
                    profile = response.body().getBody().getProfile();
                    if (profile.getNameTM() != null || profile.getNameRU() != null) {
                        name_profile_txt.setText(profile.getNameTM());
                        if (Utils.getLanguage(AllProductViewsActivity.this).equals("ru")) {
                            name_profile_txt.setText(profile.getNameRU());
                        }
                    }
                    if (response.body().getBody().getImages() != null) {
                        findViewById(R.id.img_horizontal_scroll).setVisibility(View.VISIBLE);
                        imgProfiles = response.body().getBody().getImages();
                        allProfileImageAdapter();
                    }

                    if (profile.getLike() != null && profile.getDislike() != null) {
                        product_text_count_up.setText(profile.getLike() + "");
                        product_text_count_down.setText(profile.getDislike() + "");
                    }
                    if (response.body().getBody().getPhone_numbers() != null) {
                        profilePhones = response.body().getBody().getPhone_numbers();

                    }

                    if (response.body().getBody().getProfile().getInstagram() != null) {
                        if (profile.getInstagram().isEmpty()) {
                            instagram_layout.setVisibility(View.GONE);
                        }
                    }
                    if (response.body().getBody().getProfile().getSite() != null) {
                        if (profile.getSite().isEmpty()) {
                            site_layout.setVisibility(View.GONE);
                        }
                    }
                    if (response.body().getBody().getProfile().getLocation() != null) {
                        if (profile.getLocation().isEmpty()) {
                            location_layout.setVisibility(View.GONE);
                        }
                    }

                    if (profile.getDescriptionTM() != null) {
                        film_desc_all_desc_txt.setText(profile.getDescriptionTM());
                        if (Utils.getLanguage(AllProductViewsActivity.this).equals("ru")) {
                            film_desc_all_desc_txt.setText(profile.getDescriptionRU());
                        }
                    }

                    if (response.body().getBody().getProfile().getIs_certificate() != null && profile.getIs_certificate()) {
                        certificate_layout.setVisibility(View.VISIBLE);
                    } else {
                        certificate_layout.setVisibility(View.GONE);
                    }

                    if (response.body().getBody().getProfile().getIs_promo() != null && profile.getIs_promo()) {
                        promo_layout.setVisibility(View.VISIBLE);

                    } else {
                        promo_layout.setVisibility(View.GONE);
                    }
                    if (profile.getCategory_id() != null) {
                        if (profile.getCategory_id() == 2 || profile.getCategory_id() == 1) {
                            brone_movie_layout.setVisibility(View.VISIBLE);
                            film_desc_layout.setVisibility(View.VISIBLE);
                            average_check_txt.setText(getResources().getString(R.string.price));
                            String movieTime = "";
                            if (profile.getSite() != null) {
                                movieTime = profile.getSite();
                                splitTime(movieTime);
                                brone_movie_layout.setVisibility(View.VISIBLE);
                            } else {
                                brone_movie_layout.setVisibility(View.GONE);
                            }
                        } else {
                            brone_movie_layout.setVisibility(View.GONE);
                            film_desc_layout.setVisibility(View.GONE);
                            average_check_txt.setText(getResources().getString(R.string.average_check));
                        }
                    }



                    if (profile.getDelivery() != null && profile.getDelivery()) {
                        deliver_layout.setVisibility(View.VISIBLE);
                    } else {
                        deliver_layout.setVisibility(View.GONE);
                    }

                    if (profile.getIs_terminal() != null && profile.getIs_terminal()) {
                        payment_layout.setVisibility(View.VISIBLE);
                    } else {
                        payment_layout.setVisibility(View.GONE);
                    }

                    if (profile.getWork_hours() != null) {
                        work_time_another_layout.setVisibility(View.VISIBLE);
                        work_hours_desc_txt.setText(profile.getWork_hours());
                        if (profile.getWork_hours().isEmpty()) {
                            work_time_another_layout.setVisibility(View.GONE);
                        }
                    }

                    if (profile.getCousineTM() != null && profile.getCousineRU() != null) {
                        cuisine_layout.setVisibility(View.VISIBLE);
                        cuisine_desc_txt.setText(profile.getCousineTM());
                        if (Utils.getLanguage(AllProductViewsActivity.this).equals("ru")) {
                            cuisine_desc_txt.setText(profile.getCousineRU());
                        }
                        if (profile.getCousineTM().isEmpty() && profile.getCousineRU().isEmpty()) {
                            cuisine_layout.setVisibility(View.GONE);
                        }
                    }

                    if (profile.getInstagram() != null) {
                        instagram_layout.setVisibility(View.VISIBLE);

                    }

                    if (profile.getAverage_check() != null) {
                        average_check_layout.setVisibility(View.VISIBLE);
                        average_check_desc_txt.setText(profile.getAverage_check() + "TM");
                        if (profile.getAverage_check() == null) {
                            average_check_layout.setVisibility(View.GONE);
                        }
                    }

                    if (profile.getIs_wifi() != null && profile.getIs_wifi()) {
                        wifi_layout.setVisibility(View.VISIBLE);
                    } else {
                        wifi_layout.setVisibility(View.GONE);
                    }
                    if (profile.getAddress() != null) {
                        address_desc_txt.setText(profile.getAddress());
                    }

                    if (response.body().getBody().getImages() != null) {
                        gallery_layout.setVisibility(View.VISIBLE);
                        imgProfiles = response.body().getBody().getImages();
                        galleryAdapter();
                        for (ImgProfile img : response.body().getBody().getImages()) {
                            if (img.getVR()) {
                                try {
                                    Glide.with(AllProductViewsActivity.this)
                                            .load(Constant.BASE_URL_IMAGE + img.getLarge_image())
                                            .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                                            .into(vr_img);
                                    largeVrImageUrl = Constant.BASE_URL_IMAGE + img.getLarge_image();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    } else {
                        gallery_layout.setVisibility(View.GONE);
                    }

                    if (response.body().getBody().getPosts() != null) {
                        post_layout.setVisibility(View.VISIBLE);
                        promotionAndOffers = response.body().getBody().getPosts();
                        postAdapter();
                    } else {
                        post_layout.setVisibility(View.GONE);
                    }

                    if (profile.getView_count() != null) {
                        look_txt_counts.setText(profile.getView_count() + "");
                    }

                    setListener();
                } else {
                    Utils.showCustomToast(getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            AllProductViewsActivity.this,
                            R.color.no_internet_back);
                    Log.e("Error: ", response.code() + "");
                    Toast.makeText(AllProductViewsActivity.this, response.code(), Toast.LENGTH_SHORT).show();

                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<GetProfileTiny> call, Throwable t) {
                Utils.showCustomToast(getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        AllProductViewsActivity.this,
                        R.color.no_internet_back);
                progress.dismiss();
                Log.e("Error", t.getMessage().toString());
            }
        });
    }

    private void splitTime(String movieTime) {
        try {
            String[] times = movieTime.split("\\*");
            for (String time : times) {
                String[] dateTime = time.split("\\(");
                for (int l = 0; l < dateTime.length; l++) {
                    if (l % 2 == 0) {
                        String[] myDate = dateTime[l].replace(".", "/").split("-");
                        for (String md : myDate) {
                            String[] myTimes = dateTime[l + 1].replace(")", "").split(",");
                            ArrayList<String> mt = new ArrayList<>();
                            mt.addAll(Arrays.asList(myTimes));
                            movieTimes.add(new MovieTime(md, mt));
                        }
                    }

                }
            }


            for (MovieTime md : movieTimes) {
                System.out.println(md.getDate());
                for (String mt : md.getTimes()) {
                    System.out.println("Times: " + mt);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomDialog() {
        if(movieTimes.size()<=0){
            return;
        }
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.brone_filim_dialog, null, false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);

        EditText count_tickets_ed_text = dialog.findViewById(R.id.count_tickets_ed_text);
        count_tickets_ed_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (count_tickets_ed_text.equals("") || count_tickets_ed_text == null) {
                    count_tickets = "0";
                } else {
                    count_tickets = count_tickets_ed_text.getText().toString();
                }
                string_to_int = Integer.parseInt(count_tickets);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        NeumorphButton send_btn = dialog.findViewById(R.id.send_btn);
        ImageView close_dialog_img = dialog.findViewById(R.id.close_dialog_img);
        close_dialog_img.setOnClickListener(view -> dialog.dismiss());
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (string_to_int <= 0 || count_tickets_ed_text.getText().toString().equals("") || count_tickets_ed_text == null) {
                    Toast.makeText(AllProductViewsActivity.this, R.string.how_many_tickets, Toast.LENGTH_SHORT).show();
                } else {
                    showTicketsAcceptDialog();
                    dialog.dismiss();
                }

            }
        });
        RecyclerView brone_time_rec = dialog.findViewById(R.id.brone_time_rec);
        RecyclerView brone_date_rec = dialog.findViewById(R.id.brone_date_rec);
        brone_date_rec.setAdapter(new BroneData_adapter(this, movieTimes, brone_time_rec));
        brone_time_rec.setAdapter(new BroneTimeAdapter(this, movieTimes.get(0).getTimes()));
        brone_date_rec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        brone_time_rec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawableResource(R.drawable.card_gradient);
        window.setGravity(Gravity.CENTER);
        dialog.show();
    }

    public void showTicketsAcceptDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.accept_buy_ticket_dialog, null, false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);

        NeumorphButton no_btn = dialog.findViewById(R.id.no_btn);
        no_btn.setOnClickListener(view -> dialog.dismiss());
        NeumorphButton yes_btn = dialog.findViewById(R.id.yes_btn);
        TextView price_bron_txt = dialog.findViewById(R.id.price_bron_txt);
        TextView answer_buy_ticket_txt = dialog.findViewById(R.id.answer_buy_ticket_txt);
        TextView desc_buy_txt = dialog.findViewById(R.id.desc_buy_txt);
        Double price_b = profile.getAverage_check();
        Double price_for_count = string_to_int * price_b;
        price_bron_txt.setText("Price: " + String.valueOf(price_for_count) + " TMT");

        no_btn.setTypeface(Font.getInstance(this).getMontserrat_600());
        yes_btn.setTypeface(Font.getInstance(this).getMontserrat_600());
        price_bron_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        answer_buy_ticket_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
        desc_buy_txt.setTypeface(Font.getInstance(this).getMontserrat_600());

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BroneData_adapter.selectedDate.isEmpty() || BroneTimeAdapter.selectedTime.isEmpty()) {
                    Toast.makeText(AllProductViewsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                KProgressHUD progress = Utils.AppProgressBar(AllProductViewsActivity.this);
                progress.setLabel(getResources().getString(R.string.wait));
                progress.show();
//                profile.getCinema_id() BroneData_adapter.selectedDate, BroneTimeAdapter.selectedTime
                String token = "Bearer " + Utils.getSharePreferences(AllProductViewsActivity.this, "token");
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                BronMovie bronMovie = new BronMovie(
                        2,
                        profile.getId(),
                        Integer.parseInt(Utils.getSharePreferences(AllProductViewsActivity.this, "user_id")),
                        BroneData_adapter.selectedDate,
                        BroneTimeAdapter.selectedTime,
                        string_to_int,
                        profile.getAverage_check(),
                        0.0);
                Call<RequestBronFilm> requestBronFilmCall = apiInterface.add_ticket(bronMovie, token);
                requestBronFilmCall.enqueue(new Callback<RequestBronFilm>() {
                    @Override
                    public void onResponse(Call<RequestBronFilm> call, Response<RequestBronFilm> response) {
                        if (response.isSuccessful()) {
                            AppAlert alert = new AppAlert(AllProductViewsActivity.this);
                            alert.setTitle(AllProductViewsActivity.this.getResources().getString(R.string.access_get_card));
                            alert.hasCancel(false);
                            alert.setButtonListener(new AppAlert.ButtonListener() {
                                @Override
                                public void onOkListener() {
                                    Intent intent = new Intent();
                                    intent.putExtra("movie_price", string_to_int);
                                    alert.dismiss();
                                    progress.dismiss();
                                }

                                @Override
                                public void onCancelListener() {

                                }
                            });
                            alert.show();
                        } else {
                            Log.e("Error ", response.code() + "");
                            Log.e("Error ", BroneData_adapter.selectedDate + " " + BroneTimeAdapter.selectedTime);
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<RequestBronFilm> call, Throwable t) {
                        dialog.dismiss();
                        Utils.showCustomToast(getResources().getString(R.string.check_internet),
                                R.drawable.ic_wifi_no_connection,
                                AllProductViewsActivity.this,
                                R.color.no_internet_back);
                        progress.dismiss();
//                        findViewById(R.id.getCard_progress).setVisibility(View.VISIBLE);
                        Log.e("Error ", t.getMessage() + "");
                    }
                });
            }
        });

        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void setFont() {
        name_profile_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        gallery_txt.setTypeface(Font.getInstance(this).getMontserrat_800());
        post_txt.setTypeface(Font.getInstance(this).getMontserrat_800());

        address_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        payment_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        deliver_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        average_check_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        film_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        film_time_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        work_hours_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        cuisine_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        own_promotion_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        wifi_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_700());

        product_text_count_up.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_count_down.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_call.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_instagram.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_web.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_location.setTypeface(Font.getInstance(this).getMontserrat_600());
        certificate_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
        promo_txt.setTypeface(Font.getInstance(this).getMontserrat_600());


        address_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        deliver_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        own_promotion_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        gallery_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        average_check_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());

        payment_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        film_desc_all_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        payment_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        work_hours_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        deliver_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        cuisine_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        own_promotion_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        wifi_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());


    }

    private void initViews() {

        view = findViewById(R.id.bottomsheet);
        share_img_profile = findViewById(R.id.share_img_profile);
        post_layout = findViewById(R.id.post_layout);
        fing_up_img_products = findViewById(R.id.fing_up_img_products);
        fig_down_img_products = findViewById(R.id.fig_down_img_products);
        tool_bar = findViewById(R.id.tool_bar);
        sliderContainer = findViewById(R.id.sliderContainer);
        wifi_layout = findViewById(R.id.wifi_layout);
        instagram_layout = findViewById(R.id.instagram_layout);
        site_layout = findViewById(R.id.site_layout);
        location_layout = findViewById(R.id.location_layout);
        call_layout_products = findViewById(R.id.call_layout_products);
        all_views_viewPager = findViewById(R.id.all_views_viewPager);
        dots_indicator = findViewById(R.id.dots_indicator);
        product_text_count_down = findViewById(R.id.product_text_count_down);
        product_text_call = findViewById(R.id.product_text_call);
        product_text_instagram = findViewById(R.id.product_text_instagram);
        product_text_web = findViewById(R.id.product_text_web);
        product_text_location = findViewById(R.id.product_text_location);
        certificate_txt = findViewById(R.id.certificate_txt);
        promo_txt = findViewById(R.id.promo_txt);
        address_txt = findViewById(R.id.address_txt);
        address_desc_txt = findViewById(R.id.address_desc_txt);
        deliver_desc_txt = findViewById(R.id.deliver_desc_txt);
        average_check_txt = findViewById(R.id.average_check_txt);
        own_promotion_desc_txt = findViewById(R.id.own_promotion_desc_txt);
        gallery_txt = findViewById(R.id.gallery_txt);
        post_txt = findViewById(R.id.post_txt);
        name_profile_txt = findViewById(R.id.name_profile_txt);
        product_text_count_up = findViewById(R.id.product_text_count_up);
        deliver_txt = findViewById(R.id.deliver_txt);
        promo_txt = findViewById(R.id.promo_txt);
        certificate_layout = findViewById(R.id.certificate_layout);
        promo_layout = findViewById(R.id.promo_layout);
        payment_txt = findViewById(R.id.payment_txt);
        average_check_desc_txt = findViewById(R.id.average_check_desc_txt);
        images_layout = findViewById(R.id.images_layout);
        view_Stick = findViewById(R.id.view_Stick);
        view_bottom_rel = findViewById(R.id.view_bottom_rel);
        post_rec = findViewById(R.id.post_rec);
        prom_scroll = findViewById(R.id.prom_scroll);
        root_prom = findViewById(R.id.root_prom);
        bottomsheet = findViewById(R.id.bottomsheet);
        images_profile_rec = findViewById(R.id.images_profile_rec);
        vr_img = findViewById(R.id.vr_img);
        gallery_rec = findViewById(R.id.gallery_rec);
        look_txt_counts = findViewById(R.id.look_txt_counts);
        film_desc_txt = findViewById(R.id.film_desc_txt);
        film_desc_all_desc_txt = findViewById(R.id.film_desc_all_desc_txt);
        film_time_txt = findViewById(R.id.film_time_txt);
        payment_desc_txt = findViewById(R.id.payment_desc_txt);
        work_hours_txt = findViewById(R.id.work_hours_txt);
        work_hours_desc_txt = findViewById(R.id.work_hours_desc_txt);
        cuisine_txt = findViewById(R.id.cuisine_txt);
        cuisine_desc_txt = findViewById(R.id.cuisine_desc_txt);
        own_promotion_txt = findViewById(R.id.own_promotion_txt);
        tm_muse_card_txt = findViewById(R.id.tm_muse_card_txt);
        tm_muse_card_desc_txt = findViewById(R.id.tm_muse_card_desc_txt);
        fing_down_layout = findViewById(R.id.fing_down_layout);
        fing_up_layout = findViewById(R.id.fing_up_layout);
        brone_movie_layout = findViewById(R.id.brone_movie_layout);
        site_layout = findViewById(R.id.site_layout);
        location_layout = findViewById(R.id.location_layout);
        film_desc_layout = findViewById(R.id.film_desc_layout);
        film_time_layout = findViewById(R.id.film_time_layout);
        payment_layout = findViewById(R.id.payment_layout);
        work_time_another_layout = findViewById(R.id.work_time_another_layout);
        deliver_layout = findViewById(R.id.deliver_layout);
        cuisine_layout = findViewById(R.id.cuisine_layout);
        average_check_layout = findViewById(R.id.average_check_layout);
        own_promotion_layout = findViewById(R.id.own_promotion_layout);
        tm_muse_card_layout = findViewById(R.id.tm_muse_card_layout);
        gallery_layout = findViewById(R.id.gallery_layout);
        wifi_txt = findViewById(R.id.wifi_txt);
        wifi_desc_txt = findViewById(R.id.wifi_desc_txt);
        appBarLayout = findViewById(R.id.appBarLayout);
    }

    private void setListener() {

        call_layout_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AllProductViewsActivity.this,
                        R.style.CustomBottomSheetDialogTheme);
                View bottomSheetDialogView = LayoutInflater.from(AllProductViewsActivity.this)
                        .inflate(R.layout.phone_bottom_sheet,
                                view.findViewById(R.id.phone_bottom));
                RecyclerView phone_number_rec;
                phone_number_rec = bottomSheetDialogView.findViewById(R.id.phone_number_rec);
                LayoutManager layoutManager = new LinearLayoutManager(AllProductViewsActivity.this);
                phone_number_rec.setLayoutManager(layoutManager);
                phone_number_rec.setAdapter(new ProfilePhoneAdapter(AllProductViewsActivity.this, profilePhones));

                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });

        findViewById(R.id.brone_movie_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getSharePreferences(AllProductViewsActivity.this, "token").equals("")) {
                    startActivity(new Intent(AllProductViewsActivity.this, Sig_Up_Activity.class).putExtra("type", "1"));
                } else {
                    showCustomDialog();
                }

            }
        });

        all_views_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        findViewById(R.id.image_relative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!largeVrImageUrl.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), VrImageActivity.class);
                    intent.putExtra("VR_IMG", largeVrImageUrl);
                    startActivity(intent);
                }

            }
        });

        if (largeVrImageUrl.isEmpty()) {
            findViewById(R.id.image_relative).setVisibility(View.GONE);
        }

        certificate_layout.setOnClickListener(view -> {
            if (Utils.getSharePreferences(AllProductViewsActivity.this, "token").equals("")) {
                startActivity(new Intent(AllProductViewsActivity.this, Sig_Up_Activity.class).putExtra("type", "1"));
            } else {
                Context context = AllProductViewsActivity.this;
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AllProductViewsActivity.this,
                        R.style.CustomBottomSheetDialogTheme);
                View bottomSheetDialogView = LayoutInflater.from(AllProductViewsActivity.this)
                        .inflate(R.layout.cartificate_bottom_sheet,
                                view.findViewById(R.id.bottom_sheet_certificate));

                TextView certificate_text, write_cert_txt, close_bottom_txt;
                EditText certificate_ed_text;
                NeumorphButton send_btn;

                close_bottom_txt = bottomSheetDialogView.findViewById(R.id.close_bottom_txt);
                certificate_ed_text = bottomSheetDialogView.findViewById(R.id.certificate_ed_text);
                certificate_text = bottomSheetDialogView.findViewById(R.id.certificate_text);
                write_cert_txt = bottomSheetDialogView.findViewById(R.id.write_cert_txt);
                send_btn = bottomSheetDialogView.findViewById(R.id.send_btn);
                send_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (certificate_ed_text.getText().toString().equals("") || certificate_ed_text.getText().toString().equals("0")) {
                            Toast.makeText(context, R.string.enter_all_values, Toast.LENGTH_SHORT).show();
                        } else {
                            KProgressHUD progress = Utils.AppProgressBar(AllProductViewsActivity.this);
                            progress.setLabel(getResources().getString(R.string.wait));
                            progress.show();
                            String token = "Bearer " + Utils.getSharePreferences(AllProductViewsActivity.this, "token");
                            Integer profile_id = profile.getId();
                            String amountTxt = certificate_ed_text.getText().toString();
                            Integer amount = Integer.parseInt(amountTxt);
                            apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            InsertCertificate insertCertificate = new InsertCertificate(amount, profile_id);
                            Call<ResponseBody> responseBodyCall = apiInterface.create_certificate(token, insertCertificate);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        AppAlert appAlert = new AppAlert(AllProductViewsActivity.this);
                                        appAlert.hasCancel(false);
                                        appAlert.setTitle(AllProductViewsActivity.this.getResources().getString(R.string.access_get_card));
                                        appAlert.setButtonListener(new AppAlert.ButtonListener() {
                                            @Override
                                            public void onOkListener() {
                                                bottomSheetDialog.dismiss();
                                                appAlert.dismiss();
                                            }

                                            @Override
                                            public void onCancelListener() {

                                            }
                                        });
                                        appAlert.show();
                                    }
                                    progress.dismiss();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Utils.showCustomToast(getResources().getString(R.string.check_internet),
                                            R.drawable.ic_wifi_no_connection,
                                            AllProductViewsActivity.this,
                                            R.color.no_internet_back);
                                    progress.dismiss();
                                }
                            });
                        }

                    }

                });

                certificate_ed_text.setTypeface(Font.getInstance(context).getMontserrat_800());
                certificate_text.setTypeface(Font.getInstance(context).getMontserrat_700());
                write_cert_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
                close_bottom_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
                send_btn.setTypeface(Font.getInstance(context).getMontserrat_700());

                close_bottom_txt.setOnClickListener(view1 -> bottomSheetDialog.dismiss());


                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }

        });


        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    tool_bar.setBackgroundColor(getResources().getColor(R.color.card_background));
                } else {
                    tool_bar.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });


        fing_up_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLikeDislike(id, LikeDislikeDb.LIKE);
            }
        });

        fing_down_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLikeDislike(id, LikeDislikeDb.DISLIKE);
            }
        });

        share_img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(AllProductViewsActivity.this, ShareActivity.class);
                    intent.putExtra("IMG", Constant.BASE_URL_IMAGE + imgProfiles.get(0).getLarge_image() + "");
                    intent.putExtra("TITLE", profile.getNameTM());
                    if (Utils.getLanguage(AllProductViewsActivity.this).equals("ru")) {
                        intent.putExtra("TITLE", profile.getNameRU());
                    }
                    intent.putExtra("PROMO_PRECENT", profile.getAverage_check() + "");
                    intent.putExtra("TYPE", "profile");
                    startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        instagram_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/" + profile.getInstagram());
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/" + profile.getInstagram())));
                }
            }
        });

        promo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getSharePreferences(AllProductViewsActivity.this, "token").equals("")) {
                    startActivity(new Intent(AllProductViewsActivity.this, Sig_Up_Activity.class).putExtra("type", ""));
                } else {
                    apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    String token = "Bearer " + Utils.getSharePreferences(AllProductViewsActivity.this, "token");
                    Integer profile_id = profile.getId();
                    GetPromoCodeBody getPromoCodeBody = new GetPromoCodeBody(profile_id);
                    Call<GetPromoCodes> getPromoCodesCall = apiInterface.get_promo_codes(token, getPromoCodeBody);
                    getPromoCodesCall.enqueue(new Callback<GetPromoCodes>() {
                        @Override
                        public void onResponse(Call<GetPromoCodes> call, Response<GetPromoCodes> response) {
                            if (response.isSuccessful()) {
                                Context context = AllProductViewsActivity.this;
                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AllProductViewsActivity.this,
                                        R.style.CustomBottomSheetDialogTheme);
                                View bottomSheetDialogView = LayoutInflater.from(AllProductViewsActivity.this)
                                        .inflate(R.layout.promocode_bottom_sheet,
                                                view.findViewById(R.id.bottom_sheet_promo));

                                TextView promo_text, your_promo_txt, code_number_txt, close_bottom_txt;

                                promo_text = bottomSheetDialogView.findViewById(R.id.promo_text);
                                your_promo_txt = bottomSheetDialogView.findViewById(R.id.your_promo_txt);
                                code_number_txt = bottomSheetDialogView.findViewById(R.id.code_number_txt);
                                close_bottom_txt = bottomSheetDialogView.findViewById(R.id.close_bottom_txt);

                                GetPromoCodes getPromoCodes = response.body();
                                if (getPromoCodes.getBody() != null) {
                                    Integer code = getPromoCodes.getBody();
                                    code_number_txt.setText(code + "");
                                }

                                your_promo_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
                                promo_text.setTypeface(Font.getInstance(context).getMontserrat_700());
                                code_number_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
                                close_bottom_txt.setTypeface(Font.getInstance(context).getMontserrat_600());

                                close_bottom_txt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        bottomSheetDialog.dismiss();
                                    }
                                });

                                bottomSheetDialog.setContentView(bottomSheetDialogView);
                                bottomSheetDialog.show();

                            } else {
                                Utils.showCustomToast(getResources().getString(R.string.check_internet),
                                        R.drawable.ic_wifi_no_connection,
                                        AllProductViewsActivity.this,
                                        R.color.no_internet_back);
                                Log.e("Error", response.code() + "");
                                Toast.makeText(AllProductViewsActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<GetPromoCodes> call, Throwable t) {
                            Utils.showCustomToast(getResources().getString(R.string.check_internet),
                                    R.drawable.ic_wifi_no_connection,
                                    AllProductViewsActivity.this,
                                    R.color.no_internet_back);
                        }
                    });
                }
            }

        });
    }

    // НАстройка языкого панеля
    public String getLang() {
        return getSharedPreferences("mysettings", MODE_PRIVATE).getString("My_Lang", "");
    }

    public void onBack(View view) {
        onBackPressed();
        finish();
    }

    private void allProfileImageAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        images_profile_rec.setLayoutManager(layoutManager);
        images_profile_rec.setAdapter(new TestAdapterViewPager(this, imgProfiles, all_views_viewPager, dots_indicator));
    }

    private void galleryAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        gallery_rec.setLayoutManager(mLayoutManager);
        gallery_rec.setAdapter(new GalleryAdapter(this, imgProfiles));
    }

    private void postAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        post_rec.setLayoutManager(mLayoutManager);
        post_rec.setAdapter(new PromotionAndOffersAdapter(this, promotionAndOffers));
    }


    private void setLike() {
        LikeDislikeDb likeDislikeDb = new LikeDislikeDb(AllProductViewsActivity.this);
        Cursor cursor = likeDislikeDb.getCountFirst(id + "", "like");
        if (cursor.getCount() > 0) {
            fing_up_layout.setBackground(getResources().getDrawable(R.drawable.like_back));
            fing_up_img_products.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.aply_text_color)));
            product_text_count_up.setTextColor(getResources().getColor(R.color.aply_text_color));
        } else {
            fing_up_layout.setBackground(getResources().getDrawable(R.drawable.all_product_images_back));
            fing_up_img_products.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_color)));
            product_text_count_up.setTextColor(getResources().getColor(R.color.text_color));
        }

        cursor = likeDislikeDb.getCountFirst(id + "", "dislike");
        if (cursor.getCount() > 0) {
            fing_down_layout.setBackground(getResources().getDrawable(R.drawable.like_back));
            fig_down_img_products.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.aply_text_color)));
            product_text_count_down.setTextColor(getResources().getColor(R.color.aply_text_color));

        } else {
            fing_down_layout.setBackground(getResources().getDrawable(R.drawable.all_product_images_back));
            fig_down_img_products.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_color)));
            product_text_count_down.setTextColor(getResources().getColor(R.color.text_color));
        }
    }


    private void sendLikeDislike(Integer id, String type) {
        LikeDislikeDb likeDislikeDb = new LikeDislikeDb(AllProductViewsActivity.this);
        Cursor cursor = likeDislikeDb.getCount(id, type);
        if (cursor.getCount() > 0) {
            return;
        }
        KProgressHUD progress = new KProgressHUD(AllProductViewsActivity.this);
        progress.setLabel(getResources().getString(R.string.wait));
        progress.show();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String table_type = "profile";
        PostLikeDislike postLikeDislike = new PostLikeDislike(id, type, table_type);
        Call<ResponseBody> responseBodyCall = apiInterface.add_like_dislike(postLikeDislike);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    likeDislikeDb.insert(id, type);
                    setLike();
                } else {
                    Log.e("Error ", response.code() + "");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", t.getMessage());
                progress.dismiss();
            }
        });
    }
}