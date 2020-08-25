package com.example.qctmanagement.api.service;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.qctmanagement.api.RestApi;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.reponse.SumProductApiResponse;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.reponse.order.status.update.UpdateStatusApiResponse;
import com.example.qctmanagement.common.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService apiService;

    private Retrofit retrofit;
    private ApiService(String baseUrl) {
        initClient(baseUrl);
        System.out.println("URl in apiservice retrofit: "+baseUrl);
    }

    private void initClient(@NonNull String baseUrl) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer.DateSerializer())
                .create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build());
        retrofit = builder.build();
    }

    public static void init(@NonNull String baseUrl) {
        if (apiService == null) {
            apiService = new ApiService(baseUrl);
        }
    }
    public static void reset(){
        apiService=null;
    }

    public static ApiService getInstance() {
        return apiService;
    }

    public void getListProduct(JsonObject body, Callback<List<ProductItemApiResponse>> callback){
        if (retrofit!=null){
            Call<List<ProductItemApiResponse>> productApiResponseCall=retrofit.create(RestApi.class).getListProduct(body);
            productApiResponseCall.enqueue(callback);
        }
    }
    public void getSumProduct(Callback<SumProductApiResponse> callback){
        if (retrofit!=null){
            Call<SumProductApiResponse> sumProductApiResponseCall =retrofit.create(RestApi.class).getSumProduct();
            sumProductApiResponseCall.enqueue(callback);
        }
    }
    public void getListCustomerNewToday(Callback<List<CustomerApiResponse>> callback){
        if (retrofit!=null){
            Call<List<CustomerApiResponse>> listCall =retrofit.create(RestApi.class).getListCustomerNewToDay();
            listCall.enqueue(callback);
        }
    }
    public void getOrderStatus(JsonObject boby, Callback<List<OrderStatusApiresponse>>callback){
        if (retrofit!=null){
            Call<List<OrderStatusApiresponse>> listCall = retrofit.create(RestApi.class).getOrderStatus(boby);
            listCall.enqueue(callback);
        }
    }
    public void updateStatusOrder(int ordCode, int sttCode, Callback<List<UpdateStatusApiResponse>> callback){
        if (retrofit!=null){
            Call<List<UpdateStatusApiResponse>> listCall = retrofit.create(RestApi.class).updateStatusOrder(ordCode,sttCode);
            listCall.enqueue(callback);
        }
    }
}
