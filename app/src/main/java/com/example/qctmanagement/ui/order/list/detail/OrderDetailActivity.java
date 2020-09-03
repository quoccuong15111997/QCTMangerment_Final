package com.example.qctmanagement.ui.order.list.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.qctmanagement.R;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_order_detail, new OrderDetailFragment())
                .commit();
    }
}