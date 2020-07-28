package com.example.qctmanagement.ui.wizard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.qctmanagement.R;

public class WizardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_wizard, new WizardFragment()).commit();
    }
}