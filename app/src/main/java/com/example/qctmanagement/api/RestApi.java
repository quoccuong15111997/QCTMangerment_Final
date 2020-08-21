package com.example.qctmanagement.api;

import com.example.qctmanagement.api.model.reponse.ProductApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {
    @POST("/api/product?runCode=PRD.001")
    Call<List<ProductItemApiResponse>> getListProduct(@Body JsonObject body);
}
