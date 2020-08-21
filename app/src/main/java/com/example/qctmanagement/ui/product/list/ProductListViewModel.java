package com.example.qctmanagement.ui.product.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.ProductApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.request.ApiRequest;
import com.example.qctmanagement.api.model.request.KeyCodeRequest;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.common.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListViewModel extends ViewModel {
    private MutableLiveData<List<ProductItemApiResponse>> itemApiResponseMutableLiveData;
    public ProductListViewModel() {
        itemApiResponseMutableLiveData = new MutableLiveData<>();

        loadData();
    }

    private void loadData() {
        KeyCodeRequest keyCodeRequest = new KeyCodeRequest();
        keyCodeRequest.setKeyCode("%");
        ApiService.getInstance().getListProduct(keyCodeRequest.convertToJson(), new Callback<List<ProductItemApiResponse>>() {
            @Override
            public void onResponse(Call<List<ProductItemApiResponse>> call, Response<List<ProductItemApiResponse>> response) {
                if (response.isSuccessful()){
                    List<ProductItemApiResponse> productApiResponse= response.body();
                    itemApiResponseMutableLiveData.setValue(productApiResponse);
                }
            }
            @Override
            public void onFailure(Call<List<ProductItemApiResponse>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public MutableLiveData<List<ProductItemApiResponse>> getItemApiResponseMutableLiveData() {
        return itemApiResponseMutableLiveData;
    }
}