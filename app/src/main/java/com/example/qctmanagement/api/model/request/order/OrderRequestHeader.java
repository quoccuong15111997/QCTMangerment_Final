package com.example.qctmanagement.api.model.request.order;

import com.google.gson.annotations.SerializedName;

public class OrderRequestHeader {
    @SerializedName("CRTDATE")
    private String createDate;
    @SerializedName("CUSTCODE")
    private int custCode;
    @SerializedName("SHIPADDRESS")
    private String shipAddress;
    @SerializedName("PHONE")
    private String phone;
    @SerializedName("EMAIL")
    private String email;
    @SerializedName("PAYMETHOD")
    private String payMethod;
    @SerializedName("SHIPCOMP")
    private String shipCom;

    public OrderRequestHeader() {
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCustCode() {
        return custCode;
    }

    public void setCustCode(int custCode) {
        this.custCode = custCode;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getShipCom() {
        return shipCom;
    }

    public void setShipCom(String shipCom) {
        this.shipCom = shipCom;
    }
}
