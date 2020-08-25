package com.example.qctmanagement.api.model.request.order;

import com.google.gson.JsonObject;

public class OrderStatusRequest {
    private String keyCode;
    private String sttCode;

    public OrderStatusRequest() {
    }

    public OrderStatusRequest(String keyCode, String sttCode) {
        this.keyCode = keyCode;
        this.sttCode = sttCode;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getSttCode() {
        return sttCode;
    }

    public void setSttCode(String sttCode) {
        this.sttCode = sttCode;
    }
    public JsonObject convertToJson(){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("KEYCODE",keyCode);
        jsonObject.addProperty("STTCODE",sttCode);
        return jsonObject;
    }
}
