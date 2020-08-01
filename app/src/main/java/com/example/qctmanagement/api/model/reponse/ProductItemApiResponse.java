package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;

import java.io.Serializable;
import java.util.List;

public class ProductItemApiResponse implements Serializable {
    @SerializedName("ITEMCODE")
    private String itemCode;
    @SerializedName("ITEMNAME")
    private String itemName;
    @SerializedName("UNIT")
    private Unit unit;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("SDESCRIPTION")
    private String shortDescription;
    @SerializedName("QUANTITY")
    private double quantity;
    @SerializedName("PRICE")
    private double price;
    @SerializedName("SELLPRICE")
    private double sellPrice;
    @SerializedName("IMAGE")
    private String image;
    @SerializedName("IMAGEDETAILS")
    private List<ImageURL> listImageDetails;
    @SerializedName("COLOR1")
    private Color color1;
    @SerializedName("COLOR2")
    private Color color2;
    @SerializedName("COLOR3")
    private Color color3;
    @SerializedName("DISCOUNT")
    private boolean isDiscount;
    @SerializedName("DISCOUNTPRICE")
    private double discountPrice;
    @SerializedName("CATEGORY")
    private Category category;

    public ProductItemApiResponse() {
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ImageURL> getListImageDetails() {
        return listImageDetails;
    }

    public void setListImageDetails(List<ImageURL> listImageDetails) {
        this.listImageDetails = listImageDetails;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor3() {
        return color3;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}