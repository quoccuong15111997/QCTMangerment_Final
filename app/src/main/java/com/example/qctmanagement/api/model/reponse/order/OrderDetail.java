package com.example.qctmanagement.api.model.reponse.order;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("PRODUCTCODE")
    private String productCode;
    @SerializedName("QUATITY")
    private double quatity;
    @SerializedName("COLOR")
    private String color;

    public OrderDetail() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getQuatity() {
        return quatity;
    }

    public void setQuatity(double quatity) {
        this.quatity = quatity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
