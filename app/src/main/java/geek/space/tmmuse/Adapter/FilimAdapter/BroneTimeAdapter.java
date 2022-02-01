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
import soup.neumorphism.NeumorphButton;

public class BroneTimeAdapter extends RecyclerView.Adapter<BroneTimeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> movieTimes;
    private LinearLayout oldSearchKeyWordButton;
    private TextView oldestTextView;
    private boolean isFirst=true;
    public BroneTimeAdapter(Context context, ArrayList<String> movieTimes) {
        this.context = context;
        this.movieTimes = movieTimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.bron_time_item, parent, false);
        return new BroneTimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String movieTime = movieTimes.get(position);
        holder.bron_time_txt.setText(movieTime);
        if(isFirst){
            holder.bron_time_txt.setTextColor(context.getResources().getColor(R.color.aply_text_color));
            holder.bron_time_linear.setBackground(context.getResources().getDrawable(R.drawable.brone_time_back));
            oldSearchKeyWordButton=holder.bron_time_linear;
            oldestTextView=holder.bron_time_txt;
            isFirst=false;
        }
        holder.bron_time_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.bron_time_txt.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                holder.bron_time_linear.setBackground(context.getResources().getDrawable(R.drawable.brone_time_back));

                if(oldSearchKeyWordButton != null){
                    oldestTextView.setTextColor(context.getResources().getColor(R.color.tex_color_btn_search));
                    oldSearchKeyWordButton.setBackground(context.getResources().getDrawable(R.drawable.bron_uncheck_back));
                }
                oldSearchKeyWordButton=holder.bron_time_linear;
                oldestTextView=holder.bron_time_txt;
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout bron_time_linear;
        private TextView bron_time_txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bron_time_linear = itemView.findViewById(R.id.bron_time_linear);
            bron_time_txt = itemView.findViewById(R.id.bron_time_txt);

            bron_time_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        }
    }
}
