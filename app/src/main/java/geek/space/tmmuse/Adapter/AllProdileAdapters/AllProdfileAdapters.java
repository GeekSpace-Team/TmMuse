package geek.space.tmmuse.Adapter.AllProdileAdapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.R;

public class AllProdfileAdapters extends RecyclerView.Adapter<AllProdfileAdapters.ViewHolder> {
    private Context context;
    private ArrayList<AllProfile> profile = new ArrayList<>();

    public AllProdfileAdapters(Context context, ArrayList<AllProfile> profile) {
        this.context = context;
        this.profile = profile;
    }

    @NonNull
    @Override
    public AllProdfileAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_rec_adapter, parent, false);
        return new AllProdfileAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProdfileAdapters.ViewHolder holder, int position) {
        AllProfile allProfile = profile.get(position);

        holder.profile_adapter_name.setText(allProfile.getNameTM());
        holder.profile_adapter_desc.setText(allProfile.getShort_descTM());
        if (Utils.getLanguage(context).equals("ru")) {
            holder.profile_adapter_name.setText(allProfile.getNameRU());
            holder.profile_adapter_desc.setText(allProfile.getShort_descRU());
        }

        try {
            Glide.with(context).load(Constant.BASE_URL_IMAGE + allProfile.getImage().get(0).getLarge_image()).into(holder.profile_adapter_img);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(allProfile.getIs_VIP()!=null && allProfile.getIs_VIP()==1){
            holder.card_view_profile.setBackground(context.getResources().getDrawable(R.drawable.profile_rec_adapter_back));
        } else {
            holder.card_view_profile.setCardBackgroundColor(context.getResources().getColor(R.color.card_background));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllProductViewsActivity.class);
                intent.putExtra("ID", allProfile.getId() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView profile_adapter_img;
        private TextView profile_adapter_name, profile_adapter_desc;
        private CardView card_view_profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_adapter_img = itemView.findViewById(R.id.profile_adapter_img);
            profile_adapter_name = itemView.findViewById(R.id.profile_adapter_name);
            profile_adapter_desc = itemView.findViewById(R.id.profile_adapter_desc);
            card_view_profile = itemView.findViewById(R.id.card_view_profile);

            profile_adapter_name.setTypeface(Font.getInstance(context).getMontserrat_600());
            profile_adapter_desc.setTypeface(Font.getInstance(context).getMontserrat_500());
        }
    }
}
