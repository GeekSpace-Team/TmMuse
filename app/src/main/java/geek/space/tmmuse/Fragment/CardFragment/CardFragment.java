package geek.space.tmmuse.Fragment.CardFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import geek.space.tmmuse.Activity.GetCard.GetCardActivity;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;


public class CardFragment extends Fragment {


    private View view;
    private Context context;
    private TextView tm_muse_card_txt, tm_muse_card_desc_txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card, container, false);
        context = getContext();
        initComponents();
        fontsText();
        setListener();
        return view;
    }

    private void setListener() {
        tm_muse_card_desc_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, GetCardActivity.class));
            }
        });
    }

    private void fontsText() {
        tm_muse_card_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        tm_muse_card_desc_txt.setTypeface(Font.getInstance(context).getMontserrat_400());


    }

    private void initComponents() {
        tm_muse_card_txt = view.findViewById(R.id.tm_muse_card_txt);
        tm_muse_card_desc_txt = view.findViewById(R.id.tm_muse_card_desc_txt);
    }
}