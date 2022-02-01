package geek.space.tmmuse.Activity.AllProductViews;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.PostPreview.PostPreviewActivity;
import geek.space.tmmuse.Activity.VrImage.VrImageActivity;
import geek.space.tmmuse.Adapter.FilimAdapter.BroneData_adapter;
import geek.space.tmmuse.Adapter.FilimAdapter.BroneTimeAdapter;
import geek.space.tmmuse.Adapter.FilimAdapter.MovieTimeAdapter;
import geek.space.tmmuse.Adapter.PromotionsPage.PromotionAndOffersAdapter;
import geek.space.tmmuse.Adapter.TestAdapterViewPager.TestAdapterViewPager;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Banner.Banner;
import geek.space.tmmuse.Model.Film.MovieTime;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.Model.TestModelViewPager.TestModelViewPager;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class AllProductViewsActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private View view;
    float oldOffSet = 0f;
    private boolean inRangeExpanding;
    private boolean inRangeCollapsing;
    private RelativeLayout sliderContainer, view_bottom_rel;
    private ViewPager all_views_viewPager;
    private WormDotsIndicator dots_indicator;
    private TextView product_text_count_down, product_text_call, product_text_instagram,
            product_text_web, product_text_location, certificate_txt, promo_txt,
            address_txt, address_desc_txt, Payment_desc_txt, clock_txt, clock_desc_txt,
            deliver_desc_txt, cuisen_txt, average_check_txt, own_promotion_desc_txt, tm_muse_card_prod_txt,
            ard_prod_desc_txt, gallery_txt, post_txt, name_profile_txt, product_text_count_up, payment_txt, promotion_txt,
            deliver_txt, cuisine_desc_txt, average_check_desc_txt, tm_muse_card_prod_desc_txt;
    private LinearLayout certificate_layout, promo_layout, images_layout, all_views_info_layout;
    private View view_Stick;
    private ImageView onback_img, share_img;
    private String profileId = "", imageUrl = "";
    private ArrayList<PromotionAndOffers> promotionAndOffers = new ArrayList<>();
    private PromotionAndOffersAdapter promotionAndOffersAdapter;
    private RecyclerView post_rec, movie_time_rec, images_profile_rec;
    private ScrollView prom_scroll;
    private FrameLayout root_prom;
    private NestedScrollView bottomsheet;
    private ArrayList<MovieTime> movieTimes = new ArrayList<>();
    private String count_tickets;
    private int string_to_int;
    private ArrayList<TestModelViewPager> testModelViewPagers = new ArrayList<>();
    private TestAdapterViewPager testAdapterViewPager;
    private int dotsCount;
    private RoundedImageView vr_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product_views);
        initViews();
        setListener();
        setFont();
        getLang();
        setPromotionsList();
        setPromotionsAdapter();
        setMovieTimes();
        setProfileImagesList();
        setProfileImgAdapter();
    }

    private void setProfileImgAdapter() {
        LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        images_profile_rec.setLayoutManager(layoutManager);
        images_profile_rec.setAdapter(new TestAdapterViewPager(this, testModelViewPagers, all_views_viewPager));

    }

    private void setProfileImagesList() {
        testModelViewPagers.clear();
        testModelViewPagers.add(new TestModelViewPager(1, "https://turkmenportal.com/images/uploads/catalog/2867eea7fc42123de62c998b4c74937c.jpg"));
        testModelViewPagers.add(new TestModelViewPager(2, "https://media-cdn.tripadvisor.com/media/photo-s/08/0c/7f/a8/2.jpg"));
        testModelViewPagers.add(new TestModelViewPager(3, "https://lotta-tm.com/images/blogs/ammar1.jpg"));
        testModelViewPagers.add(new TestModelViewPager(4, "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL"));
        testModelViewPagers.add(new TestModelViewPager(5, "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL"));
        testModelViewPagers.add(new TestModelViewPager(6, "https://lotta-tm.com/images/blogs/ammar1.jpg"));

    }

    public void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.brone_filim_dialog, null, false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);

        EditText count_tickets_ed_text = dialog.findViewById(R.id.count_tickets_ed_text);
        count_tickets_ed_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (count_tickets_ed_text.equals("") || count_tickets_ed_text == null) {
                    count_tickets = "0";
                } else {
                    count_tickets = count_tickets_ed_text.getText().toString();
                }
                string_to_int = Integer.valueOf(count_tickets);
            }
        });
        NeumorphButton send_btn = dialog.findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (string_to_int <= 0999.9 || count_tickets_ed_text.equals("") || count_tickets_ed_text == null) {
                    Toast.makeText(AllProductViewsActivity.this, "Please enter how many tickets you want", Toast.LENGTH_SHORT).show();
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
        int price_for_count = string_to_int * 100;
        price_bron_txt.setText("Price: " + String.valueOf(price_for_count) + " TMT");

        no_btn.setTypeface(Font.getInstance(this).getMontserrat_600());
        yes_btn.setTypeface(Font.getInstance(this).getMontserrat_600());
        price_bron_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        answer_buy_ticket_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
        desc_buy_txt.setTypeface(Font.getInstance(this).getMontserrat_600());

        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void setMovieTimes() {
        movieTimes.clear();
        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> times1 = new ArrayList<>();
        ArrayList<String> times2 = new ArrayList<>();
        ArrayList<String> times3 = new ArrayList<>();
        times.add("13:00");
        times.add("15:00");
        times.add("17:00");
        times.add("19:00");
        times1.add("22:30");
        times2.add("22:30");
        times3.add("22:30");
        movieTimes.add(new MovieTime("7.01.2022", times));
        movieTimes.add(new MovieTime("7.01.2022", times1));
        movieTimes.add(new MovieTime("7.01.2022", times2));
        movieTimes.add(new MovieTime("7.01.2022", times3));

        movie_time_rec.setAdapter(new MovieTimeAdapter(movieTimes, this));
        movie_time_rec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }

    private void setPromotionsAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        post_rec.setLayoutManager(mLayoutManager);
        promotionAndOffersAdapter = new PromotionAndOffersAdapter(this, promotionAndOffers, prom_scroll, root_prom);
        post_rec.setAdapter(promotionAndOffersAdapter);
    }

    private void setPromotionsList() {
        promotionAndOffers.clear();
        promotionAndOffers.add(new PromotionAndOffers(1, " 20%", "Turkmen kofe", "https://turkmenportal.com/images/uploads/catalog/2867eea7fc42123de62c998b4c74937c.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 30%", "Turkmen kofe", "https://lotta-tm.com/images/blogs/ammar1.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 40%", "Turkmen kofe", "https://lotta-tm.com/images/blogs/ammar1.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 10%", "Turkmen kofe", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 50%", "Turkmen kofe", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));
        promotionAndOffers.add(new PromotionAndOffers(1, " 60%", "Turkmen kofe", "https://avatars.mds.yandex.net/get-altay/1881820/2a0000016de58bee7e6f8174f773ef64ac50/XXL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus at dolor commodo, ut pellentesque augue pretium. Fusce laoreet orci vel gravida rhoncus. Donec ornare dignissim quam, at ultrices magna vestibulum eget. Nullam at eleifend metus, eu fringilla ante. Fusce pellentesque egestas interdum. Etiam laoreet consectetur blandit. Aliquam commodo libero et justo lacinia, ac aliquet metus fermentum."));

    }

    private void setFont() {
        name_profile_txt.setTypeface(Font.getInstance(this).getMontserrat_800());

        address_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        payment_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        clock_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        deliver_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        cuisen_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        average_check_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        promotion_txt.setTypeface(Font.getInstance(this).getMontserrat_700());
        tm_muse_card_prod_txt.setTypeface(Font.getInstance(this).getMontserrat_700());

        product_text_count_up.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_count_down.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_call.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_instagram.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_web.setTypeface(Font.getInstance(this).getMontserrat_600());
        product_text_location.setTypeface(Font.getInstance(this).getMontserrat_600());
        certificate_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
        promo_txt.setTypeface(Font.getInstance(this).getMontserrat_600());
        payment_txt.setTypeface(Font.getInstance(this).getMontserrat_600());

        address_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        Payment_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        clock_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        deliver_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        own_promotion_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        ard_prod_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        gallery_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        post_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        cuisine_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        average_check_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
        tm_muse_card_prod_desc_txt.setTypeface(Font.getInstance(this).getMontserrat_500());
    }

    private void initViews() {
        view = findViewById(R.id.bottomsheet);
        sliderContainer = findViewById(R.id.sliderContainer);
        movie_time_rec = findViewById(R.id.movie_time_rec);
        bottomSheetBehavior = BottomSheetBehavior.from(view);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
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
        Payment_desc_txt = findViewById(R.id.Payment_desc_txt);
        clock_txt = findViewById(R.id.clock_txt);
        clock_desc_txt = findViewById(R.id.clock_desc_txt);
        deliver_desc_txt = findViewById(R.id.deliver_desc_txt);
        cuisen_txt = findViewById(R.id.cuisen_txt);
        average_check_txt = findViewById(R.id.average_check_txt);
        own_promotion_desc_txt = findViewById(R.id.own_promotion_desc_txt);
        tm_muse_card_prod_txt = findViewById(R.id.tm_muse_card_prod_txt);
        ard_prod_desc_txt = findViewById(R.id.tm_muse_card_prod_desc_txt);
        gallery_txt = findViewById(R.id.gallery_txt);
        post_txt = findViewById(R.id.post_txt);
        name_profile_txt = findViewById(R.id.name_profile_txt);
        product_text_count_up = findViewById(R.id.product_text_count_up);
        deliver_txt = findViewById(R.id.deliver_txt);
        promo_txt = findViewById(R.id.promo_txt);
        promotion_txt = findViewById(R.id.promotion_txt);
        certificate_layout = findViewById(R.id.certificate_layout);
        promo_layout = findViewById(R.id.promo_layout);
        payment_txt = findViewById(R.id.payment_txt);
        cuisine_desc_txt = findViewById(R.id.cuisine_desc_txt);
        average_check_desc_txt = findViewById(R.id.average_check_desc_txt);
        tm_muse_card_prod_desc_txt = findViewById(R.id.tm_muse_card_prod_desc_txt);
        images_layout = findViewById(R.id.images_layout);
        all_views_info_layout = findViewById(R.id.all_views_info_layout);
        view_Stick = findViewById(R.id.view_Stick);
        view_bottom_rel = findViewById(R.id.view_bottom_rel);
        onback_img = findViewById(R.id.onback_img);
        share_img = findViewById(R.id.share_img);
        post_rec = findViewById(R.id.post_rec);
        prom_scroll = findViewById(R.id.prom_scroll);
        root_prom = findViewById(R.id.root_prom);
        bottomsheet = findViewById(R.id.bottomsheet);
        images_profile_rec = findViewById(R.id.images_profile_rec);
        vr_img = findViewById(R.id.vr_img);
    }

    private void setListener() {

        findViewById(R.id.bronMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                } else if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                inRangeExpanding = oldOffSet < slideOffset;
                inRangeCollapsing = oldOffSet > slideOffset;
                oldOffSet = slideOffset;
                if (inRangeExpanding) {
                    // acylyarka

                }
                if (inRangeCollapsing) {
                    // yapylyarka
                }

                if (oldOffSet >= 1) {
                    sliderContainer.setBackgroundColor(Color.parseColor("#FF000000"));
                    view_Stick.setVisibility(View.INVISIBLE);
                    onback_img.setVisibility(View.VISIBLE);
                    share_img.setVisibility(View.VISIBLE);
                    view_bottom_rel.setBackground(getResources().getDrawable(R.drawable.card_gradient));
                } else if (oldOffSet >= 0.80f) {
                    sliderContainer.setBackgroundColor(Color.parseColor("#A6000000"));
                    view_bottom_rel.setBackground(getResources().getDrawable(R.drawable.bottom_sheet_language_back));
                    view_Stick.setVisibility(View.VISIBLE);
                    onback_img.setVisibility(View.INVISIBLE);
                    share_img.setVisibility(View.INVISIBLE);
                    prom_scroll.stopNestedScroll();
                } else if (oldOffSet >= 0.70f) {
                    sliderContainer.setBackgroundColor(Color.parseColor("#66000000"));
                    view_bottom_rel.setBackground(getResources().getDrawable(R.drawable.bottom_sheet_language_back));
                    view_Stick.setVisibility(View.VISIBLE);
                    onback_img.setVisibility(View.INVISIBLE);
                    share_img.setVisibility(View.INVISIBLE);
                    prom_scroll.stopNestedScroll();
                } else if (oldOffSet >= 0.60f) {
                    sliderContainer.setBackgroundColor(Color.parseColor("#33000000"));
                    view_bottom_rel.setBackground(getResources().getDrawable(R.drawable.bottom_sheet_language_back));
                    view_Stick.setVisibility(View.VISIBLE);
                    onback_img.setVisibility(View.INVISIBLE);
                    share_img.setVisibility(View.INVISIBLE);
                    prom_scroll.stopNestedScroll();
                } else if (oldOffSet >= 0.50f) {
                    sliderContainer.setBackgroundColor(Color.parseColor("#00000000"));
                    view_Stick.setVisibility(View.VISIBLE);
                    onback_img.setVisibility(View.INVISIBLE);
                    share_img.setVisibility(View.INVISIBLE);
                    prom_scroll.stopNestedScroll();
                    view_bottom_rel.setBackground(getResources().getDrawable(R.drawable.bottom_sheet_language_back));
                } else if (oldOffSet < 0.50f) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                    view_Stick.setVisibility(View.VISIBLE);
                    view_bottom_rel.setBackground(getResources().getDrawable(R.drawable.bottom_sheet_language_back));
                    onback_img.setVisibility(View.INVISIBLE);
                    share_img.setVisibility(View.INVISIBLE);
                    prom_scroll.stopNestedScroll();
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
                startActivity(new Intent(getApplicationContext(), VrImageActivity.class));
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
}