package id.my.developer.hi.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.my.developer.hi.R;
import id.my.developer.hi.model.Chat;
import id.my.developer.hi.utils.SharedPreferencesManager;

/**
 * Created by light on 16/08/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private List<Chat> chatList;
    private Context context;

    public ChatAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        if(chat.getName().equals(SharedPreferencesManager.with(context.getApplicationContext()).getNickName())){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.LEFT;
            holder.chatWrapper.setLayoutParams(params);
        }
        holder.nameContainer.setText(chat.getName());
        holder.messageContainer.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void append(Chat chat){
        chatList.add(chat);
        ((AppCompatActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyItemInserted(chatList.size());
            }
        });
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.chat_wrapper)
        LinearLayout chatWrapper;
        @BindView(R.id.name_container)
        TextView nameContainer;
        @BindView(R.id.message_container)
        TextView messageContainer;

        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
