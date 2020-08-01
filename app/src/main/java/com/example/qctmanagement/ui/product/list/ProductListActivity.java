package com.example.qctmanagement.ui.product.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.qctmanagement.R;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_product_list,new ProductListFragment()).commit();
    }
}