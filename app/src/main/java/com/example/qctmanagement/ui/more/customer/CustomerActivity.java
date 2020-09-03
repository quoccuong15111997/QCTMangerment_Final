package com.example.qctmanagement.ui.more.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.qctmanagement.R;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_customer, new CustomerFragment())
                .commit();
    }
}