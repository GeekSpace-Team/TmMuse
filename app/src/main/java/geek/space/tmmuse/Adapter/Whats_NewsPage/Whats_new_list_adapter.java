package geek.space.tmmuse.Adapter.Whats_NewsPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;

public class Whats_new_list_adapter extends RecyclerView.Adapter<Whats_new_list_adapter.ViewHolder> {

    Context context;
    ArrayList<String> arrayList = new ArrayList<>();

    public Whats_new_list_adapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item_whats_new, parent, false);
        return new Whats_new_list_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition() + 1;
        holder.description_txt.setText(pos + ". " + arrayList.get(position));
        holder.description_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description_txt = itemView.findViewById(R.id.description_txt);

        }
    }
}
