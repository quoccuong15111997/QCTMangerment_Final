package com.example.qctmanagement.ui.product.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.qctmanagement.R;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_product_detail, new ProductDetailFragment()).commit();
    }
}