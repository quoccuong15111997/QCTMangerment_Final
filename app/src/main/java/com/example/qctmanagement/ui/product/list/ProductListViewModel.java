package com.example.qctmanagement.ui.product.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.ProductApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.request.ApiRequest;
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
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setAuthKey(Constant.AUTH_KEY);
        apiRequest.setKeyCode("%");
        ApiService.getInstance().getListProduct(apiRequest.convertToJson(), new Callback<ProductApiResponse>() {
            @Override
            public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                if (response.isSuccessful()){
                    ProductApiResponse productApiResponse= response.body();
                    itemApiResponseMutableLiveData.setValue(productApiResponse.getProductItemApiResponses());
                }
            }
            @Override
            public void onFailure(Call<ProductApiResponse> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<ProductItemApiResponse>> getItemApiResponseMutableLiveData() {
        return itemApiResponseMutableLiveData;
    }
}