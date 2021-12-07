package geek.space.tmmuse.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.R;


public class CardFragment extends Fragment {


    private View view;
    private Context context;
    private TextView tv;


    public CardFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_card, container, false);
        context=getContext();
        initComponents();
        fontsText();
        return view;
    }

    private void fontsText() {
        tv.setTypeface(Font.getInstance(context).getMontserrat_800());
    }

    private void initComponents() {
        tv=view.findViewById(R.id.tv);
    }
}