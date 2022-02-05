package geek.space.tmmuse.Adapter.ProfilePhoneAdapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Model.ProfilePhone.ProfilePhone;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphButton;

public class ProfilePhoneAdapter extends RecyclerView.Adapter<ProfilePhoneAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProfilePhone> profilePhones;


    public ProfilePhoneAdapter(Context context, ArrayList<ProfilePhone> profilePhones) {
        this.context = context;
        this.profilePhones = profilePhones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phone_number_rec_item, parent, false);
        return new ProfilePhoneAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfilePhone profilePhone = profilePhones.get(position);
        holder.phone_numbers_txt.setText(profilePhone.getNumber_profile());
        holder.call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},1001);
                }
                else
                {
                    String call_number = profilePhone.getNumber_profile();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:+"+call_number));
                    context.startActivity(callIntent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return profilePhones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView phone_numbers_txt;
        private NeumorphButton call_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            phone_numbers_txt = itemView.findViewById(R.id.phone_numbers_txt);
            call_btn = itemView.findViewById(R.id.call_btn);

            phone_numbers_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
            call_btn.setTypeface(Font.getInstance(context).getMontserrat_700());
        }
    }
}
