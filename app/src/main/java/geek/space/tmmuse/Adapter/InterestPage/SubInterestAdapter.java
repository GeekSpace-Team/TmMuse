package geek.space.tmmuse.Adapter.InterestPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.Interest.Interest_Activity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.ToggleableRadioButton;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Interest.SubInterest;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphCardView;

public class SubInterestAdapter extends RecyclerView.Adapter<SubInterestAdapter.ViewHolder> {
    private ArrayList<SubInterest> subInterests;
    private Context context;
    private RecyclerView recyclerView;

    public SubInterestAdapter(ArrayList<SubInterest> subInterests, Context context, RecyclerView recyclerView) {
        this.subInterests = subInterests;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_interest_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sub_interest_btn.setText(subInterests.get(position).getTitleTM());
        if (Utils.getLanguage(context).equals("ru")){
            holder.sub_interest_btn.setText(subInterests.get(position).getTitleRU());
        }
        recyclerView.getId();
        holder.sub_interest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Interest_Activity.selectedInterests.contains(subInterests.get(position).getId())){
                    holder.sub_interest_btn.setTextColor(context.getResources().getColor(R.color.tex_color_btn_search));
                    holder.sub_interest_card.setShapeType(0);
                    holder.sub_interest_btn.setChecked(false);
                    Interest_Activity.selectedInterests.remove(subInterests.get(position).getId());
                } else{
                    holder.sub_interest_btn.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                    holder.sub_interest_card.setShapeType(2);
                    holder.sub_interest_btn.setChecked(true);
                    Interest_Activity.selectedInterests.add(subInterests.get(position).getId());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return subInterests.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton sub_interest_btn;
        private NeumorphCardView sub_interest_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sub_interest_btn = itemView.findViewById(R.id.sub_interest_btn);
            sub_interest_card = itemView.findViewById(R.id.sub_interest_card);
            sub_interest_btn.setTypeface(Font.getInstance(context).getMontserrat_400());
        }
    }
}
