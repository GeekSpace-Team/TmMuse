package geek.space.tmmuse.Adapter.PromotionsPage;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Activity.PostPreview.PostPreviewActivity;
import geek.space.tmmuse.Activity.ShareActivity.ShareActivity;
import geek.space.tmmuse.Common.Constant;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.LikeDislike.PostLikeDislike;
import geek.space.tmmuse.Model.PromotionAndOffers.PromotionAndOffers;
import geek.space.tmmuse.R;
import geek.space.tmmuse.Service.LikeDislikeDb;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.ShapeType;

public class PromotionAndOffersAdapter extends RecyclerView.Adapter<PromotionAndOffersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PromotionAndOffers> promotionAndOffers = new ArrayList<>();
    private FrameLayout frameLayout;
    private String title = "", desc = "", img = "";
    private TextView desc_text;
    private NeumorphCardView fig_up_card, fig_down_card, share_card, fig_insta_card;
    private ImageView like_img, dislike_img;


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

        holder.percent_btn.setText(promotion.getPromotion() +"%");
        holder.name_prom_offer_txt.setText(promotion.getTitleTM());
        if (Utils.getLanguage(context).equals("ru")){
            holder.name_prom_offer_txt.setText(promotion.getTitleRU());
        }
        Glide.with(context).load(Constant.BASE_URL_IMAGE + promotion.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(holder.prom_offer_img);
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
                Utils.add_click_count(promotion.getId(), "post", context);
                Intent intent = new Intent(context, PostPreviewActivity.class);
                intent.putExtra("IMG", Constant.BASE_URL_IMAGE + promotion.getImage() + "");
                intent.putExtra("TITLE", promotion.getTitleTM() + "");
                intent.putExtra("DESC", promotion.getDescriptionTM() + "");
                intent.putExtra("ID", promotion.getId());
                intent.putExtra("VIEW_COUNT", promotion.getView_count()+"");
                intent.putExtra("PROMO_PRECENT", promotion.getPromotion() + "");
                intent.putExtra("INSTA","http://instagram.com/" + promotion.getInstagram());
                if (Utils.getLanguage(context).equals("ru")){
                    intent.putExtra("TITLE", promotion.getTitleRU() + "");
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
            Utils.add_click_count(promotionAndOffers.get(0).getId(), "post", context);
            Intent intent = new Intent(context, PostPreviewActivity.class);
            intent.putExtra("IMG", Constant.BASE_URL_IMAGE + promotion.getImage() + "");
            intent.putExtra("TITLE", promotion.getTitleTM() + "");
            intent.putExtra("DESC", promotion.getDescriptionTM() + "");
            intent.putExtra("ID", promotion.getId());
            intent.putExtra("VIEW_COUNT", promotion.getView_count() + "");
            intent.putExtra("PROMO_PRECENT", promotion.getPromotion() + "");
            intent.putExtra("INSTA","http://instagram.com/" + promotion.getInstagram());
            intent.putExtra("type", "1");
            if (Utils.getLanguage(context).equals("ru")){
                intent.putExtra("TITLE", promotion.getTitleRU() + "");
                intent.putExtra("DESC", promotion.getDescriptionRU() + "");
            }
            context.startActivity(intent);
            dialog.dismiss();
            Main_Menu.get().getBlurLayout().setVisibility(View.GONE);
        });
        fig_up_card = dialog.findViewById(R.id.fig_up_card);
        fig_down_card = dialog.findViewById(R.id.fig_down_card);
        share_card = dialog.findViewById(R.id.share_card);
        fig_insta_card = dialog.findViewById(R.id.fig_insta_card);
        like_img = dialog.findViewById(R.id.like_img);
        dislike_img = dialog.findViewById(R.id.dislike_img);
        fig_up_card.setOnClickListener(view -> sendLikeDislike(promotionAndOffers.get(0).getId(),LikeDislikeDb.LIKE));
        fig_down_card.setOnClickListener(view -> sendLikeDislike(promotionAndOffers.get(0).getId(),LikeDislikeDb.DISLIKE));
        share_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShareActivity.class);
                intent.putExtra("IMG", Constant.BASE_URL_IMAGE + promotion.getImage() + "");
                intent.putExtra("TITLE", promotion.getTitleTM() + "");
                intent.putExtra("PROMO_PRECENT", promotion.getPromotion() + "");
                intent.putExtra("TYPE", "promo");
                context.startActivity(intent);
            }
        });

        fig_insta_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/" + promotion.getInstagram());
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    context.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    context. startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/" + promotion.getInstagram())));
                }
            }
        });
        TextView prom_precent_text = dialog.findViewById(R.id.prom_precent_text);
        prom_precent_text.setText(promotion.getPromotion() + "%");
        ImageView prom_img = dialog.findViewById(R.id.prom_img);
        Glide.with(context).load(Constant.BASE_URL_IMAGE + promotion.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error_photo))
                .into(prom_img);

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


    private void sendLikeDislike(Integer id, String type) {
        ApiInterface apiInterface;
        LikeDislikeDb likeDislikeDb = new LikeDislikeDb(context);
        Cursor cursor=likeDislikeDb.getCount(id,type);
        if(cursor.getCount()>0){
            return;
        }
        KProgressHUD progress = Utils.AppProgressBar(context);
        progress.setLabel(context.getResources().getString(R.string.wait));
        progress.show();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String table_type = "post";
        PostLikeDislike postLikeDislike = new PostLikeDislike(id, type, table_type);
        Call<ResponseBody> responseBodyCall = apiInterface.add_like_dislike(postLikeDislike);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    likeDislikeDb.insert(id, type);
                    setLike();
                } else {
                    Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            context,
                            R.color.no_internet_back);
                    Log.e("Error ", response.code() + "");
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void setLike() {
        LikeDislikeDb likeDislikeDb = new LikeDislikeDb(context);
        Cursor cursor=likeDislikeDb.getCountFirst(promotionAndOffers.get(0).getId()+"","like");
        if(cursor.getCount()>0){
            fig_up_card.setShapeType(ShapeType.PRESSED);
            like_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.aply_text_color)));
        } else {
            fig_up_card.setShapeType(0);
            like_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.text_color)));
        }

        cursor=likeDislikeDb.getCountFirst(promotionAndOffers.get(0).getId()+"","dislike");
        if(cursor.getCount()>0){
            fig_down_card.setShapeType(ShapeType.PRESSED);
            dislike_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.aply_text_color)));

        } else {
            fig_down_card.setShapeType(0);
            dislike_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.text_color)));
        }
    }
}
