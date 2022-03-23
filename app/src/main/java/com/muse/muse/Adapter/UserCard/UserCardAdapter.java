package com.muse.muse.Adapter.UserCard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muse.muse.Common.Font.Font;
import com.muse.muse.Model.UserRegister.GetUserInfoBody;
import com.muse.muse.R;

import java.util.ArrayList;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GetUserInfoBody> getUserInfoBodies = new ArrayList<>();

    public UserCardAdapter(Context context, ArrayList<GetUserInfoBody> getUserInfoBodies) {
        this.context = context;
        this.getUserInfoBodies = getUserInfoBodies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_cards_item, parent, false);
        return new UserCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetUserInfoBody getUserInfoBody = getUserInfoBodies.get(position);

        holder.card_name.setText(getUserInfoBody.getFullname());
        holder.end_card_date_txt.setText(getUserInfoBody.getExpired());

    }

    @Override
    public int getItemCount() {
        return getUserInfoBodies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView user_card_number, end_card_date_txt, card_name, multicard_txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_card_number = itemView.findViewById(R.id.user_card_number);
            end_card_date_txt = itemView.findViewById(R.id.end_card_date_txt);
            card_name = itemView.findViewById(R.id.card_name);
            multicard_txt = itemView.findViewById(R.id.multicard_txt);

            user_card_number.setTypeface(Font.getInstance(context).getMontserrat_600());
            end_card_date_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
            card_name.setTypeface(Font.getInstance(context).getMontserrat_600());
            multicard_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        }
    }
}
