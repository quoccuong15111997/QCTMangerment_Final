package com.example.qctmanagement.callback;

import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;

public interface ProductListCallback {
    void onProductClick(ProductItemApiResponse product);
}
