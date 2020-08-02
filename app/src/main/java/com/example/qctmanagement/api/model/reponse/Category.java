package com.example.qctmanagement.api.model.reponse;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category extends BaseObservable implements Serializable {
    @SerializedName("ITEMCODE")
    private int itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;

    public Category() {
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
}
