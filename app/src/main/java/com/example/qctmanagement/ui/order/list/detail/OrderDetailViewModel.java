package com.example.qctmanagement.ui.order.list.detail;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.request.order.OrderStatusRequest;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.model.OrderStatusModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailViewModel extends ViewModel {
    private MutableLiveData<OrderStatusApiresponse> mutableLiveData;
    private MutableLiveData<List<OrderStatusModel>> liveDataListStatusModel;
    public OrderDetailViewModel() {
        mutableLiveData= new MutableLiveData<>();
        liveDataListStatusModel = new MutableLiveData<>();

        initData();
    }

    private void initData() {
        List<OrderStatusModel> orderStatusModels = new ArrayList<>();
        orderStatusModels.add(new OrderStatusModel("Xác nhận đơn hàng",3));
        orderStatusModels.add(new OrderStatusModel("Đã lấy hàng",5));
        orderStatusModels.add(new OrderStatusModel("Đã giao hàng",6));
        orderStatusModels.add(new OrderStatusModel("Hoàn thành",9));
        orderStatusModels.add(new OrderStatusModel("Hủy đơn hàng",7));
        orderStatusModels.add(new OrderStatusModel("Trả hàng",8));

        liveDataListStatusModel.setValue(orderStatusModels);
    }

    public LiveData<List<OrderStatusModel>> getLiveDataListStatusModel() {
        return liveDataListStatusModel;
    }

    public void loadData(int orderCode){
        OrderStatusRequest orderStatusRequest= new OrderStatusRequest();
        orderStatusRequest.setKeyCode(String.valueOf(orderCode));
        orderStatusRequest.setSttCode("0");
        ApiService.getInstance().getOrderInfo(orderStatusRequest.convertToJson(), new Callback<OrderStatusApiresponse>() {
            @Override
            public void onResponse(Call<OrderStatusApiresponse> call, Response<OrderStatusApiresponse> response) {
                if (response.isSuccessful()){
                    OrderStatusApiresponse apiresponse = response.body();
                    mutableLiveData.setValue(apiresponse);
                }
            }

            @Override
            public void onFailure(Call<OrderStatusApiresponse> call, Throwable t) {

            }
        });
    }

    public LiveData<OrderStatusApiresponse> getMutableLiveData() {
        return mutableLiveData;
    }
}