package geek.space.tmmuse.Adapter.SearchPageAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.SearchPage.SearchKeyWord;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.SearchHistoryDB;
import soup.neumorphism.NeumorphButton;

public class SearchKeyWordAdapter extends RecyclerView.Adapter<SearchKeyWordAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchKeyWord> searchKeyWords;
    private EditText editText;
    private NeumorphButton oldSearchKeyWordButton;

    public SearchKeyWordAdapter(Context context, ArrayList<SearchKeyWord> searchKeyWords, EditText editText) {
        this.context = context;
        this.searchKeyWords = searchKeyWords;
        this.editText = editText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_keyword_adpater, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.search_key_word_btn.setText(searchKeyWords.get(position).getKey_word());
        holder.search_key_word_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(searchKeyWords.get(position).getKey_word());
                holder.search_key_word_btn.setTextColor(context.getResources().getColor(R.color.aply_text_color));
                holder.search_key_word_btn.setShapeType(1);

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
        return searchKeyWords.size();
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
