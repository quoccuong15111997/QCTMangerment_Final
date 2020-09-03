package com.example.qctmanagement.api.model.reponse.user;

import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserApiResponse implements Serializable {
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    @SerializedName("ADDRESS")
    private String address;
    @SerializedName("PHONE")
    private String phone;
    @SerializedName("EMAIL")
    private String email;
    @SerializedName("STATUS")
    private Integer status;
    @SerializedName("USERNAME")
    private String username;
    @SerializedName("PASSWORD")
    private String password;
    @SerializedName("IMAGE")
    private String image;
    @SerializedName("ROLECODE")
    private int roleCode;

    public UserApiResponse() {
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
