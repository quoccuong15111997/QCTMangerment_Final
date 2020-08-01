package com.example.qctmanagement.api.model.request;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class ApiRequest implements Serializable {
    private String authKey;
    private String keyCode;

    public ApiRequest() {
    }

    public ApiRequest(String authKey, String keyCode) {
        this.authKey = authKey;
        this.keyCode = keyCode;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
    public JsonObject convertToJson(){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("AUTH_KEY",authKey);
        jsonObject.addProperty("KEYWORD",keyCode);
        return jsonObject;
    }
}
