package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductApiResponse extends ApiResponse implements Serializable {
    @SerializedName("DATA")
    private List<ProductItemApiResponse> productItemApiResponses;

    public ProductApiResponse() {
    }

    public List<ProductItemApiResponse> getProductItemApiResponses() {
        return productItemApiResponses;
    }

    public void setProductItemApiResponses(List<ProductItemApiResponse> productItemApiResponses) {
        this.productItemApiResponses = productItemApiResponses;
    }
}
