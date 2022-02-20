package geek.space.tmmuse.Fragment.HelpFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kaopiz.kprogresshud.KProgressHUD;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Main_menu.Main_Menu;
import geek.space.tmmuse.Common.AppAlert;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Fragment.SettingsFragment.SettingsFragment;
import geek.space.tmmuse.Model.Help.PostHelp;
import geek.space.tmmuse.Model.UserRegister.StringResponse;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class HelpFragment extends Fragment {
    private Context context;
    private View view;
    private TextView help_txt, name_txt, number_txt,
            email_txt, your_message_txt;
    private EditText full_name_edit, number_edit, email_edit,
            ed_message;
    private NeumorphButton send_btn;
    private String title = "help";
    private String message;
    private ApiInterface apiInterface;
    private ImageView go_back_img;


    public HelpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_help, container, false);
        context = getContext();
        intiComponents();
        setFont();
        setListener();

        return view;
    }

    private void setListener() {
        number_edit.setText(Utils.getSharePreferences(context, "phone_number"));
        full_name_edit.setText(Utils.getSharePreferences(context, "full_name"));
        send_btn.setOnClickListener(view -> {
            if (ed_message.getText().toString().equals("") && email_edit.getText().toString().equals("")) {
                Toast.makeText(context, getResources().getString(R.string.enter_all_values), Toast.LENGTH_SHORT).show();
            } else {
                postMessage();
            }
        });
        go_back_img.setOnClickListener(view -> {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Main_Menu.fivesFragment = settingsFragment;
            Utils.hideAdd(settingsFragment, settingsFragment.getClass().getSimpleName(), fragmentManager, R.id.menu_frame);
        });

    }

    private void postMessage() {
        KProgressHUD progress = Utils.AppProgressBar(context);
        progress.setLabel(getResources().getString(R.string.wait));
        progress.show();
        message = "Full name: " + full_name_edit.getText().toString() + "\n" +
                "Phone number: " + number_edit.getText().toString() + "\n" +
                "Email: " + email_edit.getText().toString() + "\n" +
                "Message: " + ed_message.getText().toString();
        String token = "Bearer " + Utils.getSharePreferences(context, "token");
        apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);
        PostHelp postHelps = new PostHelp(title, message);
        Call<StringResponse> stringResponseCall = apiInterface.insert_inbox(postHelps, token);
        stringResponseCall.enqueue(new Callback<StringResponse>() {
            @Override
            public void onResponse(Call<StringResponse> call, Response<StringResponse> response) {
                if (response.isSuccessful()) {
                    AppAlert alert = new AppAlert(context);
                    alert.setTitle(context.getResources().getString(R.string.access_get_card));
                    alert.hasCancel(false);
                    alert.setButtonListener(new AppAlert.ButtonListener() {
                        @Override
                        public void onOkListener() {
                            email_edit.setText("");
                            ed_message.setText("");
                            alert.dismiss();
                        }

                        @Override
                        public void onCancelListener() {

                        }
                    });
                    alert.show();
                } else {

                    Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                            R.drawable.ic_wifi_no_connection,
                            context,
                            R.color.no_internet_back);
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<StringResponse> call, Throwable t) {
                Utils.showCustomToast(context.getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
            }
        });
    }

    private void setFont() {
        help_txt.setTypeface(Font.getInstance(context).getMontserrat_800());

        full_name_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
        number_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
        email_edit.setTypeface(Font.getInstance(context).getMontserrat_600());
        ed_message.setTypeface(Font.getInstance(context).getMontserrat_600());

        name_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        number_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        email_txt.setTypeface(Font.getInstance(context).getMontserrat_400());
        your_message_txt.setTypeface(Font.getInstance(context).getMontserrat_400());

        send_btn.setTypeface(Font.getInstance(context).getMontserrat_700());
    }

    private void intiComponents() {
        help_txt = view.findViewById(R.id.help_txt);
        name_txt = view.findViewById(R.id.name_txt);
        number_txt = view.findViewById(R.id.number_txt);
        email_txt = view.findViewById(R.id.email_txt);
        your_message_txt = view.findViewById(R.id.your_message_txt);
        go_back_img = view.findViewById(R.id.go_back_img);
        full_name_edit = view.findViewById(R.id.full_name_edit);
        number_edit = view.findViewById(R.id.number_edit);
        email_edit = view.findViewById(R.id.email_edit);
        ed_message = view.findViewById(R.id.ed_message);
        send_btn = view.findViewById(R.id.send_btn);



    }
}