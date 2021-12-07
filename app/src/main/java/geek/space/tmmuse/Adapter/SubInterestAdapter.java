package geek.space.tmmuse.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Model.Interest.SubInterest;

public class SubInterestAdapter extends RecyclerView.Adapter<SubInterestAdapter.ViewHolder> {
    private ArrayList<SubInterest> subInterests=new ArrayList<>();
    private Context context;

    public SubInterestAdapter(ArrayList<SubInterest> subInterests, Context context) {
        this.subInterests = subInterests;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
