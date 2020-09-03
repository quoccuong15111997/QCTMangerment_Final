package com.example.qctmanagement.common;

import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.sharedpreferences.SharedPreferencesManager;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(this);
        ApiService.init("http://apiqctshop.azurewebsites.net");
    }
}
