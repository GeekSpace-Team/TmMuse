package geek.space.tmmuse.Adapter.FilimAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Film.MovieTime;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class BroneData_adapter extends RecyclerView.Adapter<BroneData_adapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieTime> movieTimes;
    private NeumorphButton oldSearchKeyWordButton;
    private RecyclerView timeRec;
    private boolean isFirst=true;
    public BroneData_adapter(Context context, ArrayList<MovieTime> movieTimes,RecyclerView timeRec) {
        this.context = context;
        this.movieTimes = movieTimes;
        this.timeRec=timeRec;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bron_date_item, parent, false);
        return new BroneData_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieTime movieTime = movieTimes.get(position);
        holder.search_key_word_btn.setText(movieTime.getDate());
        if(isFirst){
            holder.search_key_word_btn.setTextColor(context.getResources().getColor(R.color.aply_text_color));
            holder.search_key_word_btn.setShapeType(1);
            timeRec.setAdapter(new BroneTimeAdapter(context,movieTime.getTimes()));
            timeRec.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            oldSearchKeyWordButton=holder.search_key_word_btn;
            isFirst=false;
        }
        holder.search_key_word_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.search_key_word_btn.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                holder.search_key_word_btn.setShapeType(1);
                timeRec.setAdapter(new BroneTimeAdapter(context,movieTime.getTimes()));
                timeRec.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));

                if(oldSearchKeyWordButton != null){
                    oldSearchKeyWordButton.setTextColor(context.getResources().getColor(R.color.tex_color_btn_search));
                    oldSearchKeyWordButton.setShapeType(0);
                }
                oldSearchKeyWordButton=holder.search_key_word_btn;
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private NeumorphButton search_key_word_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_key_word_btn = itemView.findViewById(R.id.search_key_word_btn);

            search_key_word_btn.setTypeface(Font.getInstance(context).getMontserrat_700());
        }
    }
}
