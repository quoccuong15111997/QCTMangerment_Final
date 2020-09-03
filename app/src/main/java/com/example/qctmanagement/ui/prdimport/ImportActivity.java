package com.example.qctmanagement.ui.prdimport;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qctmanagement.R;

public class ImportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_import, new ImportFragment())
                .commit();
    }
}