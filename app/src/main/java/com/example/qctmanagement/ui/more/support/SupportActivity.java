package com.example.qctmanagement.ui.more.support;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qctmanagement.R;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_support, new SupportFragment())
                .commit();
    }
}