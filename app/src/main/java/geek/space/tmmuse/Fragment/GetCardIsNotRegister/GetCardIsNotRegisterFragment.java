package geek.space.tmmuse.Fragment.GetCardIsNotRegister;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import geek.space.tmmuse.R;

public class GetCardIsNotRegisterFragment extends Fragment {

    private View view;
    private Context context;
    private ImageView go_back_img;
    private TextView go_to_sig_up;

    public GetCardIsNotRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_get_card_is_not_register, container, false);
        context = getContext();
        initComponents();
        return view;
    }

    private void initComponents() {
        go_to_sig_up = view.findViewById(R.id.go_to_sig_up);
        go_back_img = view.findViewById(R.id.go_back_img);
    }
}