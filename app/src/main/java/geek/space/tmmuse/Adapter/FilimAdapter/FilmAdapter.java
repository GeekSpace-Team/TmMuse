package geek.space.tmmuse.Adapter.FilimAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Film.Film;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphCardView;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Film> filmList;

    public FilmAdapter(Context context, ArrayList<Film> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.film_rec_adapter, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.category_film.setText(film.getFilm_desc());
        holder.name_film.setText(film.getFilm_name());
        Glide.with(context).load(film.getFilm_IMG()).into(holder.films_img);
        holder.film_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllProductViewsActivity.class);
                intent.putExtra("id", getItemId(position));
                intent.putExtra("image",film.getFilm_IMG());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView films_img;
        private TextView category_film, name_film;
        private NeumorphCardView film_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            films_img = itemView.findViewById(R.id.films_img);
            category_film = itemView.findViewById(R.id.category_film);
            name_film = itemView.findViewById(R.id.name_film);
            film_card = itemView.findViewById(R.id.film_card);
            category_film.setTypeface(Font.getInstance(context).getMontserrat_500());
            name_film.setTypeface(Font.getInstance(context).getMontserrat_500());
        }
    }


    public void addData(List<Film> data) {
        filmList.addAll(data);
        notifyDataSetChanged();
    }
}
