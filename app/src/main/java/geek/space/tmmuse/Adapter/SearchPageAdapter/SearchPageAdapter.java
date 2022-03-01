package geek.space.tmmuse.Adapter.SearchPageAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.SearchPage.SearchKeyWord;
import geek.space.tmmuse.Model.SearchPage.SearchPage;
import geek.space.tmmuse.R;

public class SearchPageAdapter extends RecyclerView.Adapter<SearchPageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchPage> searchPages;

    public SearchPageAdapter(Context context, ArrayList<SearchPage> searchPages) {
        this.context = context;
        this.searchPages = searchPages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_activity_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchPage search = searchPages.get(position);
        holder.profile_adapter_desc.setText(search.getDesc());
        holder.profile_adapter_name.setText(search.getName());
        Glide.with(context).load(Constant.BASE_URL_IMAGE + search.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(holder.profile_adapter_img);
    }

    @Override
    public int getItemCount() {
        return searchPages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView profile_adapter_img;
        private TextView profile_adapter_name, profile_adapter_desc, top_key_word_txt, search_result_txt;
        private CardView card_view_profile;
        private RecyclerView top_key_words_rec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_adapter_desc = itemView.findViewById(R.id.profile_adapter_desc);
            profile_adapter_img = itemView.findViewById(R.id.profile_adapter_img);
            profile_adapter_name = itemView.findViewById(R.id.profile_adapter_name);
            card_view_profile = itemView.findViewById(R.id.card_view_profile);
            top_key_words_rec = itemView.findViewById(R.id.top_key_words_rec);
            top_key_word_txt = itemView.findViewById(R.id.top_key_word_txt);
            search_result_txt = itemView.findViewById(R.id.search_result_txt);

            profile_adapter_name.setTypeface(Font.getInstance(context).getMontserrat_700());
            profile_adapter_desc.setTypeface(Font.getInstance(context).getMontserrat_600());
            top_key_word_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
            search_result_txt.setTypeface(Font.getInstance(context).getMontserrat_700());

            card_view_profile.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.profile_rec_adapter_back));

        }
    }
}
