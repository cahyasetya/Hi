package id.my.developer.hi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.developer.hi.adapter.ChatAdapter;
import id.my.developer.hi.dagger.component.DaggerMainActivityComponent;
import id.my.developer.hi.dagger.component.MainActivityComponent;
import id.my.developer.hi.dagger.module.MainActivityModule;
import id.my.developer.hi.main_activity.MainActivityPresenter;
import id.my.developer.hi.main_activity.MainActivityView;
import id.my.developer.hi.model.Chat;
import id.my.developer.hi.utils.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements MainActivityView{
    @Inject
    MainActivityPresenter presenter;
    @Inject
    ChatAdapter chatAdapter;
    @Inject
    LinearLayoutManager layoutManager;

    @BindView(R.id.chat_list_container)
    RecyclerView chatListContainer;
    @BindView(R.id.chat_container)
    TextView chatContainer;
    @BindView(R.id.send_button)
    ImageView sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build();
        mainActivityComponent.inject(this);

        chatListContainer.setLayoutManager(layoutManager);
        chatListContainer.setAdapter(chatAdapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chatContainer.getText().equals(""))
                presenter.publish(SharedPreferencesManager.with(MainActivity.this).getNickName(), chatContainer.getText().toString(), "my_channel");
            }
        });

        presenter.subscribe("my_channel");

        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.nickname_dialog,null);

        EditText nicknameEditText = (EditText) view.findViewById(R.id.nickname_edittext);

        builder.setTitle("Insert your nickname");
        builder.setView(view);
        builder.setPositiveButton("Done", (dialogInterface, i)-> SharedPreferencesManager.with(this).setNickName(nicknameEditText.getText().toString()));

        builder.show();
    }

    @Override
    public void onMessageReceived(PNMessageResult result) {
        String name = result.getMessage().getAsJsonArray().get(0).getAsString();
        String message = result.getMessage().getAsJsonArray().get(1).getAsString();

        Chat chat = new Chat(name, message);
        chatAdapter.append(chat);
    }

    @Override
    public void onMessageSent() {
        chatContainer.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
