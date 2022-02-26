package geek.space.tmmuse.Adapter.PromotionsPage;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.AllProductViews.AllProductViewsActivity;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Activity.PostPreview.PostPreviewActivity;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class PromotionAndOffersAdapter extends RecyclerView.Adapter<PromotionAndOffersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PromotionAndOffers> promotionAndOffers = new ArrayList<>();
    private FrameLayout frameLayout;
    private String title = "", desc = "", img = "";
    private TextView desc_text;

    public PromotionAndOffersAdapter(Context context, ArrayList<PromotionAndOffers> promotionAndOffers) {
        this.context = context;
        this.promotionAndOffers = promotionAndOffers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.promotion_and_offers, parent, false);
        return new PromotionAndOffersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PromotionAndOffers promotion = promotionAndOffers.get(position);

        holder.percent_btn.setText(promotion.getPromotion());
        holder.name_prom_offer_txt.setText(promotion.getTitleTM());
        if (Utils.getLanguage(context).equals("ru")){
            holder.name_prom_offer_txt.setText(promotion.getTitleRU());
        }
        Glide.with(context).load(Constant.BASE_URL_IMAGE + promotion.getImage()).into(holder.prom_offer_img);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog(promotion);
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostPreviewActivity.class);
                intent.putExtra("IMG", Constant.BASE_URL_IMAGE + promotion.getImage() + "");
                intent.putExtra("TITLE", promotion.getTitleTM() + "");
                intent.putExtra("DESC", promotion.getDescriptionTM() + "");
                intent.putExtra("ID", promotion.getId());
                intent.putExtra("VIEW_COUNT", promotion.getView_count()+"");
                if (Utils.getLanguage(context).equals("ru")){
                    intent.putExtra("TITLE", promotion.getDescriptionRU() + "");
                    intent.putExtra("DESC", promotion.getDescriptionRU() + "");
                }
                context.startActivity(intent);

            }
        });




    }

    private void showDialog(PromotionAndOffers promotion) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        TextView prom_name_txt = dialog.findViewById(R.id.prom_name_txt);
        ReadMoreTextView desc_text = dialog.findViewById(R.id.desc_text);
        prom_name_txt.setText(promotion.getTitleTM());
        desc_text.setText(promotion.getTitleTM());
        if (Utils.getLanguage(context).equals("ru")){
            prom_name_txt.setText(promotion.getTitleRU());
            desc_text.setText(promotion.getTitleRU());
        }
        desc_text.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostPreviewActivity.class);
            intent.putExtra("IMG", Constant.BASE_URL_IMAGE + promotion.getImage() + "");
            intent.putExtra("TITLE", promotion.getTitleTM() + "");
            intent.putExtra("DESC", promotion.getDescriptionTM() + "");
            intent.putExtra("ID", promotion.getId());
            intent.putExtra("VIEW_COUNT", promotion.getView_count()+"");
            if (Utils.getLanguage(context).equals("ru")){
                intent.putExtra("TITLE", promotion.getDescriptionRU() + "");
                intent.putExtra("DESC", promotion.getDescriptionRU() + "");
            }
            context.startActivity(intent);
            dialog.dismiss();
            Main_Menu.get().getBlurLayout().setVisibility(View.GONE);
        });
        TextView prom_precent_text = dialog.findViewById(R.id.prom_precent_text);
        prom_precent_text.setText(promotion.getPromotion());
        ImageView prom_img = dialog.findViewById(R.id.prom_img);
        Glide.with(context).load(Constant.BASE_URL_IMAGE + promotion.getImage()).into(prom_img);

        dialog.setOnCancelListener(dialogInterface -> Main_Menu.get().getBlurLayout().setVisibility(View.GONE));
        dialog.setOnShowListener(dialogInterface -> Main_Menu.get().getBlurLayout().setVisibility(View.VISIBLE));

        dialog.setCancelable(true);
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return promotionAndOffers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private NeumorphCardView prom_offer_card;
        private NeumorphButton percent_btn;
        private RoundedImageView prom_offer_img;
        private TextView name_prom_offer_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prom_offer_card = itemView.findViewById(R.id.prom_offer_card);
            percent_btn = itemView.findViewById(R.id.percent_btn);
            prom_offer_img = itemView.findViewById(R.id.prom_offer_img);
            name_prom_offer_txt = itemView.findViewById(R.id.name_prom_offer_txt);
            name_prom_offer_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
            percent_btn.setTypeface(Font.getInstance(context).getMontserrat_500());

        }
    }
}
