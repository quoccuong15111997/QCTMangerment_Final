package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
    public class SumProductApiResponse implements Serializable {
    @SerializedName("SumProduct")
    private double sumProduct;
    @SerializedName("SumPrice")
    private double sumPrice;
    @SerializedName("SumPriceSale")
    private double sumPriceSale;
    @SerializedName("SumProfit")
    private double sumProfit;

    public SumProductApiResponse() {
    }

    public double getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(double sumProduct) {
        this.sumProduct = sumProduct;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public double getSumPriceSale() {
        return sumPriceSale;
    }

    public void setSumPriceSale(double sumPriceSale) {
        this.sumPriceSale = sumPriceSale;
    }

    public double getSumProfit() {
        return sumProfit;
    }

    public void setSumProfit(double sumProfit) {
        this.sumProfit = sumProfit;
    }
}
