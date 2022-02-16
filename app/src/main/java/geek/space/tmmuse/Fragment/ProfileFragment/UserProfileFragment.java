package geek.space.tmmuse.Fragment.ProfileFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.SettingsFragment.SettingsFragment;
import geek.space.tmmuse.R;


public class UserProfileFragment extends Fragment {
    private View view;
    private Context context;
    private ImageView go_back_img;
    private EditText full_name_edit, number_edit;

    public UserProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        context = getContext();
        initComponents();
        setListeners();
        return view;
    }

    private void setListeners() {
        go_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Main_Menu.fivesFragment = new SettingsFragment();
                Utils.removeShow(settingsFragment, settingsFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
            }
        });
        number_edit.setText(Utils.getSharePreferences(context, "phone_number"));
        full_name_edit.setText(Utils.getSharePreferences(context, "full_name"));
    }

    private void initComponents() {
        go_back_img = view.findViewById(R.id.go_back_img);
        number_edit = view.findViewById(R.id.number_edit);
        full_name_edit = view.findViewById(R.id.full_name_edit);
    }
}