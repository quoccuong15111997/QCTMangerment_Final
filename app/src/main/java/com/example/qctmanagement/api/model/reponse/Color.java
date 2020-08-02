package com.example.qctmanagement.api.model.reponse;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Color extends BaseObservable implements Serializable {
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

    @Bindable
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
