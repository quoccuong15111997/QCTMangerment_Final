package com.example.qctmanagement.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.model.reponse.SumProductApiResponse;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.request.order.OrderStatusRequest;
import com.example.qctmanagement.api.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<SumProductApiResponse> sumProductApiResponseMutableLiveData;
    private MutableLiveData<List<CustomerApiResponse>> listMutableLiveDataCustommerNewToday;
    private MutableLiveData<Double> valueReverneu;
    private MutableLiveData<Integer> numberOrderComplete;
    private MutableLiveData<Integer> numberOrder;
    private MutableLiveData<Integer> numberOrderCancel;
    private MutableLiveData<Integer> numberOrderReturn;

    public DashboardViewModel() {
        sumProductApiResponseMutableLiveData = new MutableLiveData<>();
        listMutableLiveDataCustommerNewToday= new MutableLiveData<>();
        valueReverneu = new MutableLiveData<>();
        numberOrderComplete= new MutableLiveData<>();
        numberOrder= new MutableLiveData<>();
        numberOrderCancel= new MutableLiveData<>();
        numberOrderReturn= new MutableLiveData<>();
        loadData();
    }

    private void loadData() {
        ApiService.getInstance().getSumProduct(new Callback<SumProductApiResponse>() {
            @Override
            public void onResponse(Call<SumProductApiResponse> call, Response<SumProductApiResponse> response) {
                if (response.isSuccessful()){
                    SumProductApiResponse sumProductApiResponse = response.body();
                    sumProductApiResponseMutableLiveData.setValue(sumProductApiResponse);
                }
            }

            @Override
            public void onFailure(Call<SumProductApiResponse> call, Throwable t) {

            }
        });
        ApiService.getInstance().getListCustomerNewToday(new Callback<List<CustomerApiResponse>>() {
            @Override
            public void onResponse(Call<List<CustomerApiResponse>> call, Response<List<CustomerApiResponse>> response) {
                if (response.isSuccessful()){
                    listMutableLiveDataCustommerNewToday.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CustomerApiResponse>> call, Throwable t) {

            }
        });
        OrderStatusRequest orderStatusRequest = new OrderStatusRequest();
        orderStatusRequest.setKeyCode(String.valueOf(0));
        orderStatusRequest.setSttCode(String.valueOf(9));
        ApiService.getInstance().getOrderStatus(orderStatusRequest.convertToJson(), new Callback<List<OrderStatusApiresponse>>() {
            @Override
            public void onResponse(Call<List<OrderStatusApiresponse>> call, Response<List<OrderStatusApiresponse>> response) {
                if (response.isSuccessful()){
                    numberOrderComplete.setValue(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<OrderStatusApiresponse>> call, Throwable t) {

            }
        });
        orderStatusRequest.setSttCode(String.valueOf(1));
        ApiService.getInstance().getOrderStatus(orderStatusRequest.convertToJson(), new Callback<List<OrderStatusApiresponse>>() {
            @Override
            public void onResponse(Call<List<OrderStatusApiresponse>> call, Response<List<OrderStatusApiresponse>> response) {
                if (response.isSuccessful()){
                    numberOrder.setValue(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<OrderStatusApiresponse>> call, Throwable t) {

            }
        });
        orderStatusRequest.setSttCode(String.valueOf(7));
        ApiService.getInstance().getOrderStatus(orderStatusRequest.convertToJson(), new Callback<List<OrderStatusApiresponse>>() {
            @Override
            public void onResponse(Call<List<OrderStatusApiresponse>> call, Response<List<OrderStatusApiresponse>> response) {
                if (response.isSuccessful()){
                    numberOrderCancel.setValue(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<OrderStatusApiresponse>> call, Throwable t) {

            }
        });
        orderStatusRequest.setSttCode(String.valueOf(8));
        ApiService.getInstance().getOrderStatus(orderStatusRequest.convertToJson(), new Callback<List<OrderStatusApiresponse>>() {
            @Override
            public void onResponse(Call<List<OrderStatusApiresponse>> call, Response<List<OrderStatusApiresponse>> response) {
                if (response.isSuccessful()){
                    numberOrderReturn.setValue(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<OrderStatusApiresponse>> call, Throwable t) {

            }
        });
    }

    public LiveData<SumProductApiResponse> getSumProductApiResponseMutableLiveData() {
        return sumProductApiResponseMutableLiveData;
    }

    public LiveData<List<CustomerApiResponse>> getListMutableLiveDataCustommerNewToday() {
        return listMutableLiveDataCustommerNewToday;
    }

    public LiveData<Double> getValueReverneu() {
        return valueReverneu;
    }

    public LiveData<Integer> getNumberOrderComplete() {
        return numberOrderComplete;
    }

    public LiveData<Integer> getNumberOrder() {
        return numberOrder;
    }

    public LiveData<Integer> getNumberOrderCancel() {
        return numberOrderCancel;
    }

    public LiveData<Integer> getNumberOrderReturn() {
        return numberOrderReturn;
    }
}