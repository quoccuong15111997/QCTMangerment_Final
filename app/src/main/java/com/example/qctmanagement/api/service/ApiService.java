package com.example.qctmanagement.api.service;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.qctmanagement.api.RestApi;
import com.example.qctmanagement.api.model.reponse.Category;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.reponse.SumProductApiResponse;
import com.example.qctmanagement.api.model.reponse.Unit;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.reponse.order.status.update.UpdateStatusApiResponse;
import com.example.qctmanagement.api.model.reponse.prdimport.ImportApiResponse;
import com.example.qctmanagement.api.model.reponse.user.UserApiResponse;
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
    public void updateStatusOrder(String employeeCode,int ordCode, int sttCode, Callback<List<UpdateStatusApiResponse>> callback){
        if (retrofit!=null){
            Call<List<UpdateStatusApiResponse>> listCall = retrofit.create(RestApi.class).updateStatusOrder(employeeCode,ordCode,sttCode);
            listCall.enqueue(callback);
        }
    }
    public void doLogin(String username, String pasword, Callback<List<UserApiResponse>> callback){
        if (retrofit!=null){
            Call<List<UserApiResponse>> listCall = retrofit.create(RestApi.class).dologin(username,pasword);
            listCall.enqueue(callback);
        }
    }
    public void getOrderInfo(JsonObject boby, Callback<OrderStatusApiresponse>callback){
        if (retrofit!=null){
            Call<OrderStatusApiresponse> listCall = retrofit.create(RestApi.class).getOrderInfo(boby);
            listCall.enqueue(callback);
        }
    }
    public void updateProduct(JsonObject body, Callback<Boolean> callback){
        if (retrofit!=null){
            Call<Boolean> booleanCall =retrofit.create(RestApi.class).updateProduct(body);
            booleanCall.enqueue(callback);
        }
    }
    public void getAllCategory(Callback<List<Category>> callback){
        if (retrofit!=null){
            Call<List<Category>> listCall = retrofit.create(RestApi.class).getAllCategory();
            listCall.enqueue(callback);
        }
    }
    public void getAllUnit(Callback<List<Unit>> callback){
        if (retrofit!=null){
            Call<List<Unit>> listCall =retrofit.create(RestApi.class).getAllUnit();
            listCall.enqueue(callback);
        }
    }
    public void activeCustomer(String username,int sttCode, Callback<Integer> callback){
        if (retrofit!=null){
            Call<Integer> integerCall = retrofit.create(RestApi.class).activeCustomer(username,sttCode);
            integerCall.enqueue(callback);
        }
    }
    public void getListCustomer(Callback<List<CustomerApiResponse>> callback){
        if (retrofit!=null){
            Call<List<CustomerApiResponse>> listCall =retrofit.create(RestApi.class).getAllCustomer();
            listCall.enqueue(callback);
        }
    }
    // Thêm mới sản phẩm
    public void insertNewProduct(JsonObject body, Callback<Boolean> callback){
        if (retrofit!=null){
            Call<Boolean> booleanCall = retrofit.create(RestApi.class).insertNewProduct(body);
            booleanCall.enqueue(callback);
        }
    }
    // Lấy danh sách nhập kho
    public void getAllImport(Callback<List<ImportApiResponse>> callback){
        if (retrofit!=null){
            Call<List<ImportApiResponse>> listCall = retrofit.create(RestApi.class).getAllInportList();
            listCall.enqueue(callback);
        }
    }
}
