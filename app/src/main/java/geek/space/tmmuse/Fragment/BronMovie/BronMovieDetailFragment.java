package geek.space.tmmuse.Fragment.BronMovie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Common.AppAlert;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Film.CancelBronFilm;
import geek.space.tmmuse.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class BronMovieDetailFragment extends Fragment {
    private Context context;
    private View view;
    private TextView movie_name, film_desc, date_time_film_txt, ticket_count_txt, filim_price_txt,
            ticket_discount_txt, user_txt, total_price_txt, date_bron_txt, ticket_detail_txt;
    private RoundedImageView film_img;
    private ImageView go_back_img;
    private String movie_name1 = "", film_desc1 = "", date_time_film_txt1 = "", ticket_count_txt1 = "",
            filim_price_txt1 = "", ticket_discount_txt1 = "", user_txt1 = "", total_price_txt1 = "",
            date_bron_txt1 = "", film_img1 = "";
    private NeumorphButton cancel_btn;
    private ApiInterface apiInterface;

    public BronMovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bron_movie_detail, container, false);
        context = getContext();
        initComponents();
        setFont();
        setListeners();
        return view;
    }

    private void setListeners() {
        movie_name1 = getArguments().getString("film_name");
        film_desc1 = getArguments().getString("film_desc");
        date_time_film_txt1 = getArguments().getString("film_date");
        ticket_count_txt1 = getArguments().getString("ticket_count");
        filim_price_txt1 = getArguments().getString("film_price");
        ticket_discount_txt1 = getArguments().getString("film_discount");
        total_price_txt1 = getArguments().getString("total_price");
        user_txt1 = getArguments().getString("film_user_name");
        date_bron_txt1 = getArguments().getString("film_date");
        film_img1 = getArguments().getString("film_img");

        movie_name.setText(movie_name1);
        film_desc.setText(film_desc1);
        date_time_film_txt.setText(date_time_film_txt1);
        ticket_count_txt.setText(ticket_count_txt1);
        filim_price_txt.setText(filim_price_txt1);
        ticket_discount_txt.setText(ticket_discount_txt1);
        user_txt.setText(user_txt1);
        date_bron_txt.setText(date_bron_txt1);
        try {
            Glide.with(context).load(film_img1).into(film_img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        go_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BroneMovieFragment broneMovieFragment = new BroneMovieFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.fivesFragment = broneMovieFragment;
                Utils.removeShow(broneMovieFragment, broneMovieFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppAlert alert = new AppAlert(context);
                alert.setTitle(context.getResources().getString(R.string.access_get_card));
                alert.hasCancel(true);
                alert.setButtonListener(new AppAlert.ButtonListener() {
                    @Override
                    public void onOkListener() {
                        apiInterface = ApiClient.cancelFilm().create(ApiInterface.class);
                        String token = "Bearer " + Utils.getSharePreferences(context, "token");
                        Integer film_id = getArguments().getInt("film_id");
                        CancelBronFilm cancelBronFilm = new CancelBronFilm();
                        Call<ResponseBody> responseBodyCall = apiInterface.ticket_status_update(token,
                                film_id, cancelBronFilm);
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    if (cancelBronFilm.getStatus()==3){
                                        cancel_btn.setVisibility(View.GONE);
                                        alert.dismiss();
                                    }
                                    alert.dismiss();
                                } else {
                                    Toast.makeText(context, "Error " + response.code() + "\n" + film_id
                                            + "\n" + token
                                            + "\n" + cancelBronFilm, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(context, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelListener() {
                        alert.dismiss();
                    }
                });
                alert.show();
            }
        });
    }

    private void setFont() {
        movie_name.setTypeface(Font.getInstance(context).getMontserrat_500());
        film_desc.setTypeface(Font.getInstance(context).getMontserrat_500());
        date_time_film_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        ticket_count_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        filim_price_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        ticket_discount_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        user_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        total_price_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        date_bron_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        ticket_detail_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        cancel_btn.setTypeface(Font.getInstance(context).getMontserrat_500());
    }

    private void initComponents() {
        movie_name = view.findViewById(R.id.movie_name);
        film_desc = view.findViewById(R.id.film_desc);
        date_time_film_txt = view.findViewById(R.id.date_time_film_txt);
        ticket_count_txt = view.findViewById(R.id.ticket_count_txt);
        filim_price_txt = view.findViewById(R.id.filim_price_txt);
        ticket_discount_txt = view.findViewById(R.id.ticket_discount_txt);
        user_txt = view.findViewById(R.id.user_txt);
        total_price_txt = view.findViewById(R.id.total_price_txt);
        date_bron_txt = view.findViewById(R.id.date_bron_txt);
        film_img = view.findViewById(R.id.film_img);
        ticket_detail_txt = view.findViewById(R.id.ticket_detail_txt);
        go_back_img = view.findViewById(R.id.go_back_img);
        cancel_btn = view.findViewById(R.id.cancel_btn);
    }
}