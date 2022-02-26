package geek.space.tmmuse.Adapter.Tags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Properties;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.ProfileFragment.Profiles;
import geek.space.tmmuse.Model.Tags_Filter_Btn.Tags_Btn;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Tags_Btn> tags_btns;
    private NeumorphButton oldSearchKeyWordButton;
    private boolean isClick = true;

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
        holder.setIsRecyclable(false);
        Tags_Btn tagsBtn = tags_btns.get(position);
        if(Profiles.tags.contains(tagsBtn.getId())){
            holder.tags_btn.setShapeType(2);
            holder.tags_btn.setTextColor(context.getResources().getColor(R.color.aply_text_color));
        }
        holder.tags_btn.setText("#" + tagsBtn.getTagTM());
        if (Utils.getLanguage(context).equals("ru")) {
            holder.tags_btn.setText("#" + tagsBtn.getTagRU());
        }
        holder.tags_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tags_btn.getShapeType() == 0) {
                    holder.tags_btn.setShapeType(2);
                    holder.tags_btn.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                    Profiles.tags.add(tagsBtn.getId());
                } else {
                    holder.tags_btn.setTextColor(context.getResources().getColor(R.color.tex_color_btn_search));
                    holder.tags_btn.setShapeType(0);
                    Profiles.tags.remove(tagsBtn.getId());

                }
            }
        });

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
