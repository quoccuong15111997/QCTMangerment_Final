package com.example.qctmanagement.common;

import com.example.qctmanagement.api.service.ApiService;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiService.init("http://apiqct.azurewebsites.net");
    }
}
