package com.muse.muse.Adapter.UserCard;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.WriterException;
import com.muse.muse.Common.Font.Font;
import com.muse.muse.Model.UserRegister.GetUserInfoBody;
import com.muse.muse.R;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GetUserInfoBody> getUserInfoBodies = new ArrayList<>();
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;

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
        String[] ex_date=getUserInfoBody.getExpired().split("-");
        if(ex_date.length>1) {  // 2022 - 03 - 31 ex_date[1]+"/"+ex_date[0].substring(2)
            holder.end_card_date_txt.setText(ex_date[1]+"/"+ex_date[0].substring(2));
        }
        holder.user_card_number.setText(getUserInfoBody.getCard_id());
        if (getUserInfoBody.getGender() == 1){
            holder.user_card_img.setImageResource(R.drawable.ic_card_for_man_svg);
        } else if (getUserInfoBody.getGender() == 2){
            holder.user_card_img.setImageResource(R.drawable.ic_card_for_her);
        }
        Log.e("Error", getUserInfoBody.getGender()+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.qr_code, null, false);
                dialog.setContentView(view1);

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                ImageView description_img = view1.findViewById(R.id.description_img);
                Point point = new Point();
                int width = (int)context.getResources().getDimension(R.dimen.qrCode);
                int height = (int)context.getResources().getDimension(R.dimen.qrCode);
                int dimen = width < height ? width : height;
                dimen = dimen * 3 / 4;
                String info_after_generate = "Name: " + getUserInfoBody.getFullname() + "\n" +
                        "Number: " + getUserInfoBody.getPhone_number() + "\n" +
                        "Day ending: " + getUserInfoBody.getExpired() + "\n" +
                        "Card number: " + getUserInfoBody.getCard_id();
                qrgEncoder = new QRGEncoder(info_after_generate, null, QRGContents.Type.TEXT, width);
                try {
                    // getting our qrcode in the form of bitmap.
                    bitmap = qrgEncoder.encodeAsBitmap();
                    // the bitmap is set inside our image
                    // view using .setimagebitmap method.
                    description_img.setImageBitmap(bitmap);
                } catch (WriterException e){
                    Log.e("Tag", e.toString());
                }
                dialog.setCancelable(true);
                window.setLayout(width, height);
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getUserInfoBodies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView user_card_number, end_card_date_txt, card_name, multicard_txt;
        private ImageView user_card_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_card_number = itemView.findViewById(R.id.user_card_number);
            end_card_date_txt = itemView.findViewById(R.id.end_card_date_txt);
            card_name = itemView.findViewById(R.id.card_name);
            user_card_img = itemView.findViewById(R.id.user_card_img);

            user_card_number.setTypeface(Font.getInstance(context).getMontserrat_600());
            end_card_date_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
            card_name.setTypeface(Font.getInstance(context).getMontserrat_600());
        }
    }
}
