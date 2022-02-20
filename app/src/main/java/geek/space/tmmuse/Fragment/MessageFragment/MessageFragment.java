package geek.space.tmmuse.Fragment.MessageFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import geek.space.tmmuse.API.ApiClient;
import geek.space.tmmuse.API.ApiInterface;
import geek.space.tmmuse.Activity.Sig_Up.Sig_Up_Activity;
import geek.space.tmmuse.Adapter.MessageAdapter.MessageAdapter;
import geek.space.tmmuse.Common.Font.Font;
import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Message.FirstMessage;
import geek.space.tmmuse.Model.Message.GetMessage;
import geek.space.tmmuse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {
    private View view;
    private Context context;
    private TextView inbox_txt, go_to_sig_up;
    private ArrayList<FirstMessage> messages = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private RecyclerView incoming_msg_rec;
    private ApiInterface apiInterface;
    private ProgressBar progress_bar;
    private boolean isLoading = false;
    public static Integer page = 1;

    public MessageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        context = getContext();
        initComponents();
        setListener();
        setFont();
        setMessageList();
        return view;
    }

    private void setListener() {
        go_to_sig_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Sig_Up_Activity.class).putExtra("type", "1"));
            }
        });
    }

    private void setMessageAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        messageAdapter = new MessageAdapter(context, messages);
        incoming_msg_rec.setLayoutManager(layoutManager);
        incoming_msg_rec.setAdapter(messageAdapter);
    }

    private void setMessageList() {
        KProgressHUD progress = Utils.AppProgressBar(context);
        progress.setLabel(context.getResources().getString(R.string.wait));
        progress.show();
        isLoading = true;
        String token = "Bearer " + Utils.getSharePreferences(context, "token");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetMessage> getMessageCall = apiInterface.answer(token);
        getMessageCall.enqueue(new Callback<GetMessage>() {
            @Override
            public void onResponse(Call<GetMessage> call, Response<GetMessage> response) {
                if (response.isSuccessful()) {
                    if (response.body().getBody() != null) {
                        messages.addAll(response.body().getBody().getFirst());
                        messages.addAll(response.body().getBody().getSecond());
                        messages.addAll(response.body().getBody().getThird());
                        setMessageAdapter();
                    }
                } else {
//
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<GetMessage> call, Throwable t) {
                Utils.showCustomToast(getResources().getString(R.string.check_internet),
                        R.drawable.ic_wifi_no_connection,
                        context,
                        R.color.no_internet_back);
                progress.dismiss();
                Log.e("Error", t.getMessage() + "");
            }
        });
    }

    private void setFont() {
        inbox_txt.setTypeface(Font.getInstance(context).getMontserrat_800());
        go_to_sig_up.setTypeface(Font.getInstance(context).getMontserrat_600());
    }

    private void initComponents() {
        inbox_txt = view.findViewById(R.id.inbox_txt);
        incoming_msg_rec = view.findViewById(R.id.incoming_msg_rec);
        go_to_sig_up = view.findViewById(R.id.go_to_sig_up);
    }

}