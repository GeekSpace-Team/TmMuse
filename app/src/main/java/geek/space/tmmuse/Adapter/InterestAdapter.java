package geek.space.tmmuse.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Model.Interest.Interest;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rec.setAdapter(new SubInterestAdapter(interests.get(position).getSubInterests(), context));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
