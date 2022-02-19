package geek.space.tmmuse.Adapter.TmMuseCard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.GetCard.GetCardBody;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class TmMuseCardAdapter extends RecyclerView.Adapter<TmMuseCardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GetCardBody> bodies = new ArrayList<>();

    public TmMuseCardAdapter(Context context, ArrayList<GetCardBody> bodies) {
        this.context = context;
        this.bodies = bodies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tm_muse_card_item, parent, false);
        return new TmMuseCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetCardBody body = bodies.get(position);
        holder.percent_btn.setText(body.getTm_muse_card() + "");
        holder.name_tm_muse_card_txt.setText(body.getNameRU());
        try {
            Glide.with(context).load(Constant.BASE_URL_IMAGE + body.getImages().get(0).getLarge_image())
                    .into(holder.tm_muse_card_img);
        } catch (Exception e){
            e.printStackTrace();
        }

        holder.root_tm_muse_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllProductViewsActivity.class);
                intent.putExtra("ID", body.getId()+"");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bodies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView tm_muse_card_img;
        private NeumorphButton percent_btn;
        private TextView name_tm_muse_card_txt;
        private RelativeLayout root_tm_muse_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tm_muse_card_txt = itemView.findViewById(R.id.name_tm_muse_card_txt);
            percent_btn = itemView.findViewById(R.id.percent_btn);
            tm_muse_card_img = itemView.findViewById(R.id.tm_muse_card_img);
            root_tm_muse_card = itemView.findViewById(R.id.root_tm_muse_card);

            percent_btn.setTypeface(Font.getInstance(context).getMontserrat_500());
            name_tm_muse_card_txt.setTypeface(Font.getInstance(context).getMontserrat_600());

        }
    }
}
