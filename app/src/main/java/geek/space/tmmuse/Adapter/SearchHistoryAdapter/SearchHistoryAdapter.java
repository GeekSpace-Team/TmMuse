package geek.space.tmmuse.Adapter.SearchHistoryAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.SearchHistory.SearchHistory;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.SearchHistoryDB;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SearchHistory> searchHistories;
    private EditText editText;
    private SearchHistoryDB searchHistoryDB;
    private String string = "";

    public SearchHistoryAdapter(Context context, ArrayList<SearchHistory> searchHistories, EditText editText) {
        this.context = context;
        this.searchHistories = searchHistories;
        this.editText = editText;
        searchHistoryDB = new SearchHistoryDB(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_history_rec_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchHistory searchHistory = searchHistories.get(holder.getAbsoluteAdapterPosition());

        holder.search_history_txt.setText(searchHistory.getText());
        holder.search_history_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(searchHistory.getText());
                string = editText.getText().toString();
            }
        });
        holder.delete_search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = searchHistoryDB.deleteData(searchHistory.getText());
                if (result > 0) {
                    searchHistories.remove(holder.getAbsoluteAdapterPosition());
                    notifyItemRemoved(holder.getAbsoluteAdapterPosition());
                } else {
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView search_history_txt;
        private ImageView delete_search_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_history_txt = itemView.findViewById(R.id.search_history_txt);
            delete_search_img = itemView.findViewById(R.id.delete_search_img);

            search_history_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        }
    }
}
