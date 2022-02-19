package geek.space.tmmuse.Fragment.PromotionsOffersFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.PromotionsPage.PromotionAndOffersAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.HomeFragment.HomeFragment;
import geek.space.tmmuse.Model.Home.Home;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;

public class PromotionsOffersFragment extends Fragment {
    private View view;
    private Context context;
    private ImageView back_to_card;
    private TextView profile_name;
    private RecyclerView promotions_offers_rec;
    private ApiInterface apiInterface;
    private ArrayList<PromotionAndOffers> promotionAndOffers = new ArrayList<>();
    private PromotionAndOffersAdapter promotionAndOffersAdapter;
    private Integer page = 1;
    private ProgressBar loadingProgress;
    private boolean isLoading=false;

    public PromotionsOffersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_promotions_offers, container, false);
        context = getContext();
        intiComponents();
        setFont();
        setListener();
        setPromotionsAndOffersAdapter();
        request(page);
        return view;
    }

    private void setListener() {
        back_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.firstFragment = new HomeFragment();
                Utils.removeShow(homeFragment, homeFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });

        promotions_offers_rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)){
                    if (!isLoading){
                        loadMore();
                    }
                }
            }
        });
    }

    private void setFont() {
        profile_name.setTypeface(Font.getInstance(context).getMontserrat_800());
    }

    private void intiComponents() {
        promotions_offers_rec = view.findViewById(R.id.promotions_offers_rec);
        back_to_card = view.findViewById(R.id.back_to_card);
        profile_name = view.findViewById(R.id.profile_name);
        loadingProgress = view.findViewById(R.id.loadingProgress);
    }

    private void setPromotionsAndOffersAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        promotions_offers_rec.setLayoutManager(mLayoutManager);
        promotionAndOffersAdapter = new PromotionAndOffersAdapter(context, promotionAndOffers);
        promotions_offers_rec.setAdapter(promotionAndOffersAdapter);
        promotions_offers_rec.setHasFixedSize(true);
        promotions_offers_rec.setNestedScrollingEnabled(false);
    }


    private void request(int i) {
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);
        Call<Home> call = apiInterface.getHome(i);
        isLoading=true;
        loadingProgress.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, retrofit2.Response<Home> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    Home res=response.body();
                    if(page==1) {
                        if(res.getBody().getPromotionAndOffers()!=null) {
                            promotionAndOffers = res.getBody().getPromotionAndOffers();
                            setPromotionsAndOffersAdapter();
                        }
                    } else{
                        if(res.getBody().getPromotionAndOffers()!=null) {
                            promotionAndOffers.addAll(res.getBody().getPromotionAndOffers());
                        }
                    }
                } else {
                    Log.e("Code",response.code()+"");
                    Log.e("Error",response.errorBody().toString());
                }
                isLoading=false;
                loadingProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.e("Error",t.getMessage());
                isLoading=false;
                loadingProgress.setVisibility(View.GONE);
            }
        });
    }


    private void loadMore() {
        page++;
        request(page);
    }
}