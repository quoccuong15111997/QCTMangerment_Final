package com.example.qctmanagement.ui.product.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;

public class ProductDetailViewModel extends ViewModel {
    private MutableLiveData<ProductItemApiResponse> productData;

    public ProductDetailViewModel() {

        productData= new MutableLiveData<>();
    }

    public MutableLiveData<ProductItemApiResponse> getProductData() {
        return productData;
    }

    public void setProductData(ProductItemApiResponse product) {
        productData.setValue(product);
    }
}