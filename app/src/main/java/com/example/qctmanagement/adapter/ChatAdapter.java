package com.example.qctmanagement.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.firebase.ChatMessage;
import com.example.qctmanagement.firebase.CustomerFirebase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ChatMessage> chatData;
    private CustomerFirebase customerFirebase;

    public static final  int TYPE_OWER=1;
    public static final  int TYPE_GUEST=2;

    public ChatAdapter(Context context, ArrayList<ChatMessage> chatData, CustomerFirebase customerFirebase) {
        mContext = context;
        this.chatData = chatData;
        this.customerFirebase = customerFirebase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view=null;
        if(i==TYPE_OWER)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_owner,viewGroup,false);
        }
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_guest,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ChatMessage chatMessage = chatData.get(i);
            viewHolder.messageText.setText(chatMessage.getMessageText());
            viewHolder.messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                    chatMessage.getMessageTime()));
    }

    @Override
    public int getItemCount() {
        return chatData.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage=chatData.get(position);
        if (chatMessage!=null) {
            if (chatMessage.getMessageUser().equals(customerFirebase.getCustUsername())) {
                return TYPE_OWER;
            } else
                return TYPE_GUEST;
        } else
            return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView messageTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message_text);
            messageTime = itemView.findViewById(R.id.message_time);
        }
    }
}
