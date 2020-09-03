package com.example.qctmanagement.ui.more.customer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.api.model.reponse.ApiResponse;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerViewModel extends ViewModel {
    private MutableLiveData<List<CustomerApiResponse>> listMutableLiveData;

    public CustomerViewModel() {
        listMutableLiveData= new MutableLiveData<>();

        loadData();
    }

    public void loadData() {
        ApiService.getInstance().getListCustomer(new Callback<List<CustomerApiResponse>>() {
            @Override
            public void onResponse(Call<List<CustomerApiResponse>> call, Response<List<CustomerApiResponse>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CustomerApiResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<CustomerApiResponse>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}