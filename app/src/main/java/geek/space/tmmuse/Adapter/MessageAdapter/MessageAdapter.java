package geek.space.tmmuse.Adapter.MessageAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.MessageFragment.OpenMessageFragment;
import geek.space.tmmuse.Model.Message.FirstMessage;
import geek.space.tmmuse.R;
import soup.neumorphism.NeumorphCardView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FirstMessage>messages = new ArrayList<>();

    public MessageAdapter(Context context, ArrayList<FirstMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incoming_msg_rec_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        FirstMessage message = messages.get(position);
        if (message.getTittle()!=null){
            holder.msg_name_txt.setText(message.getTittle());
        }

        if (message.getMessage()!=null){
            holder.msg_desc_txt.setText(message.getMessage().replace("\n",", "));
        }

        if (message.getCreated_at()!=null){
            holder.data_income_txt.setText(message.getCreated_at().split("T")[0]);
        }

        holder.message_card_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message.getIs_read()!=null && message.getIs_read()){
                    holder.msg_check_uncheck_view.setBackground(context.getResources().getDrawable(R.drawable.mesg_uncheked_back));
                } else{
                    holder.msg_check_uncheck_view.setBackground(context.getResources().getDrawable(R.drawable.mesg_checked_back));
                }
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                OpenMessageFragment openMessageFragment = new OpenMessageFragment();
                openMessageFragment.firstMsg = messages;
                openMessageFragment.position = position;
                FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("MSG_NAME", message.getTittle());
                bundle.putString("MSG_DATA", message.getCreated_at());
                bundle.putString("MSG_DESC", message.getMessage());
                openMessageFragment.setArguments(bundle);
                Main_Menu.fourthFragment = openMessageFragment;
                Utils.hideAdd(openMessageFragment, openMessageFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);

            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private NeumorphCardView message_card_adapter;
        private TextView msg_name_txt, msg_desc_txt, data_income_txt;
        private View msg_check_uncheck_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg_name_txt = itemView.findViewById(R.id.msg_name_txt);
            msg_desc_txt = itemView.findViewById(R.id.msg_desc_txt);
            data_income_txt = itemView.findViewById(R.id.data_income_txt);
            msg_check_uncheck_view = itemView.findViewById(R.id.msg_check_uncheck_view);
            message_card_adapter = itemView.findViewById(R.id.message_card_adapter);

            msg_name_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
            msg_desc_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
            data_income_txt.setTypeface(Font.getInstance(context).getMontserrat_500());


        }
    }

}
