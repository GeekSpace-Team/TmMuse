package geek.space.tmmuse.Adapter.FilimAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Film.MovieTime;
import geek.space.tmmuse.R;

public class MovieTimeAdapter extends RecyclerView.Adapter<MovieTimeAdapter.ViewHolder> {
    private ArrayList<MovieTime> movieTimes=new ArrayList<>();
    private Context context;


    public MovieTimeAdapter(ArrayList<MovieTime> movieTimes, Context context) {
        this.movieTimes = movieTimes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_time_item,parent,false);
        return new MovieTimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieTime movieTime=movieTimes.get(position);
        holder.date.setText(movieTime.getDate());
        holder.timesContainer.removeAllViews();
        for(String time:movieTime.getTimes()){
            View view=LayoutInflater.from(context).inflate(R.layout.time_item, null, false);
            TextView timeTV=view.findViewById(R.id.time);
            timeTV.setTypeface(Font.getInstance(context).getMontserrat_500());
            timeTV.setText(time);
            holder.timesContainer.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return movieTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        LinearLayout timesContainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timesContainer=itemView.findViewById(R.id.timesContainer);
            date=itemView.findViewById(R.id.date);

            date.setTypeface(Font.getInstance(context).getMontserrat_600());
        }
    }
}
