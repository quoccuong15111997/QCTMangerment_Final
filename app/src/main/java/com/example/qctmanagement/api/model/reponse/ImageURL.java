package com.example.qctmanagement.api.model.reponse;

import android.telephony.SignalStrength;

import com.google.gson.annotations.SerializedName;

public class ImageURL {
    @SerializedName("URL")
    private String url;

    public ImageURL() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
