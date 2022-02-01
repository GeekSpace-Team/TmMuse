package geek.space.tmmuse.Adapter.Tags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.Tags_Filter_Btn.Tags_Btn;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Tags_Btn> tags_btns;

    public TagsAdapter(Context context, ArrayList<Tags_Btn> tags_btns) {
        this.context = context;
        this.tags_btns = tags_btns;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_tags_adapter, parent, false);
        return new TagsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tags_Btn tagsBtn = tags_btns.get(position);
        holder.tags_btn.setText("#" + tagsBtn.getTag_name());
    }

    @Override
    public int getItemCount() {
        return tags_btns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private NeumorphButton tags_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tags_btn = itemView.findViewById(R.id.tags_btn);
            tags_btn.setTypeface(Font.getInstance(context).getMontserrat_500());

        }
    }
}
