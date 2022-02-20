package geek.space.tmmuse.Fragment.CardFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.GetCard.GetCardActivity;
import geek.space.tmmuse.Adapter.TmMuseCard.TmMuseCardAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.GetCard.GetCardBody;
import geek.space.tmmuse.Model.GetCard.GetCardResponse;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CardFragment extends Fragment {


    private View view;
    private Context context;
    private TextView tm_muse_card_txt, tm_muse_card_desc_txt;
    private RecyclerView cards_rec;
    private ApiInterface apiInterface;
    private Integer limit = 20;
    private Integer page = 1;
    private ArrayList<GetCardBody> getCardBodies = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card, container, false);
        context = getContext();
        initComponents();
        fontsText();
        setListener();
        setGetTmMuseCards();
        return view;
    }

    private void setTmMuseCardAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        cards_rec.setLayoutManager(mLayoutManager);
        cards_rec.setAdapter(new TmMuseCardAdapter(context, getCardBodies));
    }

    private void setGetTmMuseCards() {
        KProgressHUD progress = Utils.AppProgressBar(context);
        progress.setLabel(context.getResources().getString(R.string.wait));
        progress.show();
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);
        Call<GetCardResponse> getCardResponseCall = apiInterface.get_card_promotion(limit, page);
        getCardResponseCall.enqueue(new Callback<GetCardResponse>() {
            @Override
            public void onResponse(Call<GetCardResponse> call, Response<GetCardResponse> response) {
                if (response.isSuccessful()) {
                    getCardBodies = response.body().getBody();
                    setTmMuseCardAdapter();
                } else {
                    Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            context,
                            R.color.no_internet_back);
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<GetCardResponse> call, Throwable t) {
                Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
            }
        });
    }

    private void setListener() {
        tm_muse_card_desc_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, GetCardActivity.class));
            }
        });
    }

    private void fontsText() {
        tm_muse_card_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        tm_muse_card_desc_txt.setTypeface(Font.getInstance(context).getMontserrat_400());


    }

    private void initComponents() {
        tm_muse_card_txt = view.findViewById(R.id.tm_muse_card_txt);
        tm_muse_card_desc_txt = view.findViewById(R.id.tm_muse_card_desc_txt);
        cards_rec = view.findViewById(R.id.cards_rec);
    }


}