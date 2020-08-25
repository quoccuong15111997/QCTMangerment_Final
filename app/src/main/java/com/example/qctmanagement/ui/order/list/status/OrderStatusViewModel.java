package com.example.qctmanagement.ui.order.list.status;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.request.order.OrderStatusRequest;
import com.example.qctmanagement.api.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatusViewModel extends ViewModel {
    private MutableLiveData<List<OrderStatusApiresponse>> mutableLiveData;
    public OrderStatusViewModel() {
        mutableLiveData = new MutableLiveData<>();

    }
    public void loadData(int key){
        OrderStatusRequest orderStatusRequest = new OrderStatusRequest();
        orderStatusRequest.setKeyCode(String.valueOf(0));
        orderStatusRequest.setSttCode(String.valueOf(key));
        ApiService.getInstance().getOrderStatus(orderStatusRequest.convertToJson(), new Callback<List<OrderStatusApiresponse>>() {
            @Override
            public void onResponse(Call<List<OrderStatusApiresponse>> call, Response<List<OrderStatusApiresponse>> response) {
                if (response.isSuccessful()){
                    List<OrderStatusApiresponse> orderStatusApiresponses = response.body();
                    mutableLiveData.setValue(orderStatusApiresponses);
                }
                else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<OrderStatusApiresponse>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public LiveData<List<OrderStatusApiresponse>> getMutableLiveData() {
        return mutableLiveData;
    }
}