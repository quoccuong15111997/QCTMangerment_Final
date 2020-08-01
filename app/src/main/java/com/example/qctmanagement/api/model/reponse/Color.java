package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.SerializedName;

public class Color {
    @SerializedName("ITEMCODE")
    private int itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    @SerializedName("DESCRIPTION")
    private String description;

    public Color() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
