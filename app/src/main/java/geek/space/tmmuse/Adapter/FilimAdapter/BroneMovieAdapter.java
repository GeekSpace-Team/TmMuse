package geek.space.tmmuse.Adapter.FilimAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.BronMovie.BronMovieDetailFragment;
import geek.space.tmmuse.Model.Film.BronMovieUsers;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphCardView;

public class BroneMovieAdapter extends RecyclerView.Adapter<BroneMovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BronMovieUsers> bronMovieUsers = new ArrayList<>();

    public BroneMovieAdapter(Context context, ArrayList<BronMovieUsers> bronMovieUsers) {
        this.context = context;
        this.bronMovieUsers = bronMovieUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bron_film_item, parent, false);
        return new BroneMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BronMovieUsers bronMovieUser = bronMovieUsers.get(position);
        Integer price_movie = bronMovieUser.getTicket_count();
        Double b = bronMovieUser.getTicket_price();
        Double a = Double.parseDouble(String.valueOf(price_movie));
        Double total_price_movie = a * b;

        holder.movie_name.setText(bronMovieUser.getNameTM());
        if (Utils.getLanguage(context).equals("ru")) {
            holder.movie_name.setText(bronMovieUser.getNameRU());
        }


        holder.ticket_count_txt.setText(String.valueOf(bronMovieUser.getTicket_count()));


        holder.date_time_film_txt.setText(bronMovieUser.getMovie_date() + "/" + bronMovieUser.getMovie_time());
        holder.filim_price_txt.setText("Price " + bronMovieUser.getTicket_price() + "TMT");
        if (bronMovieUser.getTicket_discount() != null) {
            holder.ticket_discount_txt.setText("Ticket discount: " + bronMovieUser.getTicket_discount() + "%");
            holder.discount_ticket_layout.setVisibility(View.VISIBLE);
        } else {
            holder.discount_ticket_layout.setVisibility(View.GONE);
        }

        holder.user_txt.setText(bronMovieUser.getFullname());
        holder.total_price_txt.setText("Total price: " + total_price_movie + "TMT");
        try {
            Glide.with(context).load(Constant.BASE_URL_IMAGE + bronMovieUser.getImage().get(0).getSmall_image())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                    .into(holder.img_movie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bronMovieUser.getStatus() == 1){
            holder.bron_cardView.setBackgroundColor(context.getResources().getColor(R.color.yellow_film));
        } else if(bronMovieUser.getStatus() == 2){
            holder.bron_cardView.setBackgroundColor(context.getResources().getColor(R.color.green_film));
        } else if (bronMovieUser.getStatus() == 3){
            holder.bron_cardView.setBackgroundColor(context.getResources().getColor(R.color.red_film));
        }


        holder.bron_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                BronMovieDetailFragment bronMovieDetailFragment = new BronMovieDetailFragment();
                bronMovieDetailFragment.holder=holder;
                bronMovieDetailFragment.bronMovieUsers=bronMovieUsers;
                bronMovieDetailFragment.pos=position;
                FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
                Bundle bundle = new Bundle();

                bundle.putString("film_user_name", bronMovieUser.getFullname());
                bundle.putString("film_desc", bronMovieUser.getDescriptionTM());
                if (Utils.getLanguage(context).equals("ru")) {
                    bundle.putString("film_desc", bronMovieUser.getDescriptionRU());
                }
                bundle.putString("film_date", bronMovieUser.getMovie_date() + "/" + bronMovieUser.getMovie_time());
                bundle.putString("ticket_count", bronMovieUser.getTicket_count() + "");
                bundle.putString("film_price", bronMovieUser.getTicket_price() + "");
                bundle.putString("film_discount", bronMovieUser.getTicket_discount() + "");
                bundle.putString("film_name", bronMovieUser.getNameTM());
                if (Utils.getLanguage(context).equals("ru")) {
                    bundle.putString("film_name", bronMovieUser.getNameRU());
                }
                bundle.putString("total_price", total_price_movie + "");
                bundle.putString("film_img", Constant.BASE_URL_IMAGE + bronMovieUser.getImage().get(0).getLarge_image());
                bundle.putInt("film_id", bronMovieUser.getId());

                bronMovieDetailFragment.setArguments(bundle);
                Main_Menu.fivesFragment = bronMovieDetailFragment;
                Utils.hideAdd(bronMovieDetailFragment, bronMovieDetailFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bronMovieUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private NeumorphCardView bron_cardView;
        private TextView movie_name, date_time_film_txt, ticket_count_txt, filim_price_txt,
                ticket_discount_txt, user_txt, total_price_txt;
        private RoundedImageView img_movie;
        private LinearLayout discount_ticket_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bron_cardView = itemView.findViewById(R.id.bron_cardView);
            img_movie = itemView.findViewById(R.id.img_movie);
            discount_ticket_layout = itemView.findViewById(R.id.discount_ticket_layout);

            movie_name = itemView.findViewById(R.id.movie_name);

            date_time_film_txt = itemView.findViewById(R.id.date_time_film_txt);
            ticket_count_txt = itemView.findViewById(R.id.ticket_count_txt);
            filim_price_txt = itemView.findViewById(R.id.filim_price_txt);
            ticket_discount_txt = itemView.findViewById(R.id.ticket_discount_txt);
            user_txt = itemView.findViewById(R.id.user_txt);
            total_price_txt = itemView.findViewById(R.id.total_price_txt);

            movie_name.setTypeface(Font.getInstance(context).getMontserrat_500());
            date_time_film_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
            ticket_count_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
            filim_price_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
            ticket_discount_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
            user_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
            total_price_txt.setTypeface(Font.getInstance(context).getMontserrat_400());

        }
    }
}
