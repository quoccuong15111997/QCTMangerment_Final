package com.example.qctmanagement.ui.product.add;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qctmanagement.R;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_add_product, new AddProductFragment())
                .commit();
    }
}