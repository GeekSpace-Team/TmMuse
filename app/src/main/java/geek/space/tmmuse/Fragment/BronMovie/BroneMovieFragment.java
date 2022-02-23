package geek.space.tmmuse.Fragment.BronMovie;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Adapter.FilimAdapter.BroneMovieAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.ProfileFragment.UserProfileFragment;
import geek.space.tmmuse.Fragment.SettingsFragment.SettingsFragment;
import geek.space.tmmuse.Model.Film.BronMovie;
import geek.space.tmmuse.Model.Film.BronMovieUsers;
import geek.space.tmmuse.Model.Film.BronMovieUsersBody;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BroneMovieFragment extends Fragment {

    private View view;
    private Context context;
    private TextView bron_movie_txt;
    private RecyclerView bron_film_rec;
    private ImageView go_back_img;
    private ApiInterface apiInterface;
    private ArrayList<BronMovieUsers> bronMovieUsers = new ArrayList<>();
    public BroneMovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_brone_movie, container, false);
        context = getContext();
        initComponents();
        setFont();
        setListener();
        setBronMovie();
        return view;
    }

    private void setBronMovieAapter() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        bron_film_rec.setLayoutManager(mLayoutManager);
        bron_film_rec.setAdapter(new BroneMovieAdapter(context, bronMovieUsers));
    }


    private void setBronMovie() {
        KProgressHUD progress = Utils.AppProgressBar(context);
        progress.setLabel(context.getResources().getString(R.string.wait));
        progress.show();
        String token = "Bearer " + Utils.getSharePreferences(context, "token");
        String user_id = Utils.getSharePreferences(context, "user_id");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BronMovieUsersBody> bronMovieUsersBodyCall = apiInterface.get_current_ticket(user_id, token);
        bronMovieUsersBodyCall.enqueue(new Callback<BronMovieUsersBody>() {
            @Override
            public void onResponse(Call<BronMovieUsersBody> call, Response<BronMovieUsersBody> response) {
                if (response.isSuccessful()){
                    bronMovieUsers = response.body().getBody();
                    setBronMovieAapter();
                } else {
                    Log.e("Error",response.code()+"");
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<BronMovieUsersBody> call, Throwable t) {
                Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
                Log.e("Error",t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListener() {
        go_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.fivesFragment = settingsFragment;
                Utils.hideAdd(settingsFragment, settingsFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });
    }

    private void setFont() {
        bron_movie_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
    }

    private void initComponents() {
        bron_movie_txt = view.findViewById(R.id.bron_movie_txt);
        bron_film_rec = view.findViewById(R.id.bron_film_rec);
        go_back_img = view.findViewById(R.id.go_back_img);
    }
}