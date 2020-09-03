package com.example.qctmanagement.ui.more.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ChatAdapter;
import com.example.qctmanagement.databinding.ChatFragmentBinding;
import com.example.qctmanagement.firebase.ChatMessage;
import com.example.qctmanagement.firebase.CustomerFirebase;
import com.example.qctmanagement.sharedpreferences.SharedPreferencesManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private ChatViewModel mViewModel;
    private ChatFragmentBinding binding;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> dsMessages;
    private ArrayList<String> dsKEY = new ArrayList<>();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private String urlImage = SharedPreferencesManager.getPrefAccountImage();
    private String key;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aaaaa = urlImage;
                mData.child("customer")
                        .child("supporter")
                        .child("chats")
                        .child(key)
                        .push()
                        .setValue(new ChatMessage(binding.textContent.getText().toString(), SharedPreferencesManager.getPrefUserName(), urlImage));
                sendNotification();
                binding.textContent.setText("");
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void sendNotification() {

    }

    private void addControls() {
        Intent intent = getActivity().getIntent();
        key = intent.getStringExtra("KEY");

        dsMessages = new ArrayList<>();
        CustomerFirebase customerFirebase = new CustomerFirebase();
        customerFirebase.setCustCode(1);
        customerFirebase.setCustName("Hỗ trợ khách hàng");
        customerFirebase.setCustUsername(SharedPreferencesManager.getPrefUserName());
        customerFirebase.setUrlImage(SharedPreferencesManager.getPrefAccountImage());
        adapter = new ChatAdapter(getContext(),dsMessages,customerFirebase);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        displayChatMessages(key);
    }
    private void displayChatMessages(String key) {
        mData.child("customer")
                .child("supporter")
                .child("chats")
                .child(key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                dsMessages.add(chatMessage);
                dsKEY.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
                binding.recyclerView.smoothScrollToPosition(binding.recyclerView.getAdapter().getItemCount() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
    }
}