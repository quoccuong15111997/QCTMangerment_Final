package com.example.qctmanagement.api.service;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.qctmanagement.api.RestApi;
import com.example.qctmanagement.api.model.reponse.ProductApiResponse;
import com.example.qctmanagement.common.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Date;
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

    public void getListProduct(JsonObject body, Callback<ProductApiResponse> callback){
        if (retrofit!=null){
            Call<ProductApiResponse> productApiResponseCall=retrofit.create(RestApi.class).getListProduct(body);
            productApiResponseCall.enqueue(callback);
        }
    }
}
