package com.example.qctmanagement.ui.more.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qctmanagement.R;


public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_chat, new ChatFragment())
                .commit();

    }
}