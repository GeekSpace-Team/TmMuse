package geek.space.tmmuse.Adapter.AllProdileAdapters;

import android.content.Context;
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

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.AllProfile.AllProfile;
import geek.space.tmmuse.R;

import static geek.space.tmmuse.Model.AllProfile.AllProfile.LayoutOne;
import static geek.space.tmmuse.Model.AllProfile.AllProfile.LayoutTwo;

public class AllProdfileAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<AllProfile> allProfiles;
    private Context context;

    public AllProdfileAdapters(Context context, ArrayList<AllProfile> allProfiles) {
        this.allProfiles = allProfiles;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        switch (allProfiles.get(position).getViewType()) {
            case 0:
                return LayoutOne;
            case 1:
                return LayoutTwo;
            default:
                return 0;
        }
    }

    class FirstLayoutViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView profile_adapter_img;
        private TextView profile_adapter_name, profile_adapter_desc;
        private CardView card_view_profile;

        public FirstLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_adapter_desc = itemView.findViewById(R.id.profile_adapter_desc);
            profile_adapter_img = itemView.findViewById(R.id.profile_adapter_img);
            profile_adapter_name = itemView.findViewById(R.id.profile_adapter_name);
            card_view_profile = itemView.findViewById(R.id.card_view_profile);

            profile_adapter_name.setTypeface(Font.getInstance(context).getMontserrat_700());
            profile_adapter_desc.setTypeface(Font.getInstance(context).getMontserrat_600());

            card_view_profile.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.profile_rec_adapter_back));
        }
    }

    class SecondLayoutViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView profile_banner_adapter_img;
        private TextView see_more_txt;

        public SecondLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_banner_adapter_img = itemView.findViewById(R.id.profile_banner_adapter_img);
            see_more_txt = itemView.findViewById(R.id.see_more_txt);
            see_more_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LayoutOne:
                View layoutOne
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_rec_adapter, parent,
                                false);
                return new FirstLayoutViewHolder(layoutOne);
            case LayoutTwo:
                View layoutTwo
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.banner_in_profile_page, parent,
                                false);
                return new SecondLayoutViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AllProfile profile = allProfiles.get(position);
        switch (profile.getViewType()) {
            case LayoutOne:
                ((FirstLayoutViewHolder) holder).profile_adapter_name.setText(profile.getName_profile());
                ((FirstLayoutViewHolder) holder).profile_adapter_desc.setText(profile.getDesc_profile());
                Glide.with(context).load(profile.getImg_profile()).into(((FirstLayoutViewHolder) holder).profile_adapter_img);
                break;
            case LayoutTwo:
                Glide.with(context).load(profile.getImg_banner()).into(((SecondLayoutViewHolder) holder).profile_banner_adapter_img);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return allProfiles.size();
    }
}
