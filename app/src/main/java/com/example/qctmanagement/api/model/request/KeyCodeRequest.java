package com.example.qctmanagement.api.model.request;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/21/2020.
 */
public class KeyCodeRequest implements Serializable {
    private String keyCode;

    public KeyCodeRequest(String keyCode) {
        this.keyCode = keyCode;
    }

    public KeyCodeRequest() {
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
    public JsonObject convertToJson(){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("KEYCODE",keyCode);
        return jsonObject;
    }
}
