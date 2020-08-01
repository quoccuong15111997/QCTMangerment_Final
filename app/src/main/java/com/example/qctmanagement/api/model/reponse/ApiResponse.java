package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    @SerializedName("SESSIONCODE")
    private boolean sessionCode;
    @SerializedName("MESSAGE")
    private String message;

    public ApiResponse() {
    }

    public boolean isSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(boolean sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
