package geek.space.tmmuse.Adapter.InterestPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Interest.Interest;
import geek.space.tmmuse.Model.Interest.SubInterest;
import geek.space.tmmuse.R;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Interest> interests = new ArrayList<>();
    public InterestAdapter(Context context, ArrayList<Interest> interests) {
        this.context = context;
        this.interests = interests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.interest_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.interest_name.setText(interests.get(position).getTitle());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(
                holder.rec.getContext(),
                3);
        linearLayoutManager.setInitialPrefetchItemCount(interests.get(position).getSubInterests().size());
        holder.rec.setLayoutManager(linearLayoutManager);
        holder.rec.setAdapter(new SubInterestAdapter(interests.get(position).getSubInterests(), context, holder.rec));
    }

    @Override
    public int getItemCount() {
        return interests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rec;
        private TextView interest_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.sub_inters_adapter);
            interest_name = itemView.findViewById(R.id.interest_name);

            interest_name.setTypeface(Font.getInstance(context).getMontserrat_700());
        }
    }
}
