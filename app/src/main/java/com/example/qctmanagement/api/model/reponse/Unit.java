package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Unit implements Serializable {
    @SerializedName("ITEMCODE")
    private int itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;

    public Unit() {
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
