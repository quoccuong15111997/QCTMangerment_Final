package com.example.qctmanagement.ui.product.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.request.KeyCodeRequest;
import com.example.qctmanagement.api.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {
    private MutableLiveData<ProductItemApiResponse> productData;

    public ProductDetailViewModel() {

        productData= new MutableLiveData<>();
    }

    public MutableLiveData<ProductItemApiResponse> getProductData() {
        return productData;
    }
    public void loadData(String itemCode) {
        KeyCodeRequest keyCodeRequest = new KeyCodeRequest();
        keyCodeRequest.setKeyCode(itemCode);
        ApiService.getInstance().getListProduct(keyCodeRequest.convertToJson(), new Callback<List<ProductItemApiResponse>>() {
            @Override
            public void onResponse(Call<List<ProductItemApiResponse>> call, Response<List<ProductItemApiResponse>> response) {
                if (response.isSuccessful()){
                    List<ProductItemApiResponse> productApiResponse= response.body();
                    productData.setValue(productApiResponse.get(0));
                }
            }
            @Override
            public void onFailure(Call<List<ProductItemApiResponse>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    public void setProductData(ProductItemApiResponse product) {
        productData.setValue(product);
    }
}