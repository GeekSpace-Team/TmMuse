package geek.space.tmmuse.Fragment.OpenMessage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.MessageFragment.MessageFragment;
import geek.space.tmmuse.Fragment.SettingsFragment.SettingsFragment;
import geek.space.tmmuse.R;
public class OpenMessageFragment extends Fragment {
    private Context context;
    private View view;
    private TextView message_txt, name_mesg_txt, data_mesg_txt, desc_mesg_txt, TM_MUSE;
    private String msg_name = "", msg_desc = "", msg_data = "";
    private ImageView go_to_img;
    private View msg_check_uncheck_view;


    public OpenMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_open_message, container, false);
        context = getContext();
        initViews();
        setListeners();
        setFonts();
        setGetMessage();
        return view;
    }

    private void setListeners() {
        go_to_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg_check_uncheck_view.setBackground(context.getResources().getDrawable(R.drawable.mesg_checked_back));
                MessageFragment messageFragment = new MessageFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.fourthFragment = messageFragment;
                Utils.removeShow(messageFragment, messageFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });
    }

    private void setGetMessage() {
        msg_name = getArguments().getString("MSG_NAME");
        msg_data = getArguments().getString("MSG_DATA");
        msg_desc = getArguments().getString("MSG_DESC");
        name_mesg_txt.setText(msg_name);
        data_mesg_txt.setText(msg_data);
        desc_mesg_txt.setText(msg_desc);
    }

    private void setFonts() {
        message_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        name_mesg_txt.setTypeface(Font.getInstance(context).getMontserrat_700());
        message_txt.setTypeface(Font.getInstance(context).getMontserrat_500());
        message_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        TM_MUSE.setTypeface(Font.getInstance(context).getMontserrat_500());
        TextPaint paint = TM_MUSE.getPaint();
        float width = paint.measureText(getString(R.string.tm_muse_message));
        Shader textShader = new LinearGradient(0, 0, width, TM_MUSE.getTextSize(),
                new int[]{
                        Color.parseColor("#48647D"),
                        Color.parseColor("#6B8299"),
                        Color.parseColor("#DAE2EB"),
                        Color.parseColor("#8CA2B7"),
                        Color.parseColor("#EDF2F7"),
                }, null, Shader.TileMode.CLAMP);
        TM_MUSE.getPaint().setShader(textShader);

    }

    private void initViews() {
        message_txt = view.findViewById(R.id.message_txt);
        name_mesg_txt = view.findViewById(R.id.name_mesg_txt);
        data_mesg_txt = view.findViewById(R.id.data_mesg_txt);
        desc_mesg_txt = view.findViewById(R.id.desc_mesg_txt);
        TM_MUSE = view.findViewById(R.id.TM_MUSE);
        go_to_img = view.findViewById(R.id.go_back_img);
        msg_check_uncheck_view = view.findViewById(R.id.msg_check_uncheck_view);
    }
}