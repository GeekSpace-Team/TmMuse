package geek.space.tmmuse.Common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;

public class AppLoading {
    private Context  context;
    private Dialog dialog;
    private View view;
    private TextView pay_attention, pay_attention_desc, cancel_txt, aply_txt;
    private View view_stick_vertical;
    public interface ButtonListener{
        void onOkListener();
        void onCancelListener();
    }



    public AppLoading(Context context){
        this.context=context;
        createDialog();
    }

    private void createDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.log_out_popup, null, false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        pay_attention = dialog.findViewById(R.id.pay_attention);
        view_stick_vertical = dialog.findViewById(R.id.view_stick_vertical);
        pay_attention_desc = dialog.findViewById(R.id.pay_attention_desc);
        cancel_txt = dialog.findViewById(R.id.cancel_txt);
        aply_txt = dialog.findViewById(R.id.aply_txt);
        pay_attention.setTypeface(Font.getInstance(context).getMontserrat_800());
        pay_attention_desc.setTypeface(Font.getInstance(context).getMontserrat_400());
        cancel_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        aply_txt.setTypeface(Font.getInstance(context).getMontserrat_600());
        pay_attention.setText("");
        pay_attention_desc.setText("");
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public void setButtonListener(ButtonListener listener){
        aply_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOkListener();
            }
        });

        cancel_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancelListener();
            }
        });
    }

    public void setTitle(String text){
        pay_attention.setText(text);
    }

    public void setDescription(String description){
        pay_attention_desc.setText(description);
    }

    public void hasCancel(boolean has_cancel){
        if(has_cancel){
            cancel_txt.setVisibility(View.VISIBLE);
            view_stick_vertical.setVisibility(View.VISIBLE);
        } else{
            cancel_txt.setVisibility(View.GONE);
            view_stick_vertical.setVisibility(View.GONE);
        }
    }

}
