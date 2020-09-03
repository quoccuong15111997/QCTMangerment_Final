package com.example.qctmanagement.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderStatusModel implements Serializable {
    private String name;
    private int code;

    public OrderStatusModel(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public OrderStatusModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }
}
