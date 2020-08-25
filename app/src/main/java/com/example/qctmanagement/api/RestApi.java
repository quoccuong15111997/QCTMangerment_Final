package com.example.qctmanagement.api;

import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.reponse.SumProductApiResponse;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.reponse.order.status.update.UpdateStatusApiResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @POST("/api/product?runCode=PRD.001")
    Call<List<ProductItemApiResponse>> getListProduct(@Body JsonObject body);

    @POST("/api/product?runcode=PRD.006")
    Call<SumProductApiResponse> getSumProduct();

    @POST("/api/customer?date")
    Call<List<CustomerApiResponse>> getListCustomerNewToDay();

    @POST("/api/order?runcode=ORD.002")
    Call<List<OrderStatusApiresponse>> getOrderStatus(@Body JsonObject jsonObject);

    @POST("/api/order")
    Call<List<UpdateStatusApiResponse>> updateStatusOrder(@Query("ordCode") int orderCode,@Query("sttCode") int sstCode);
}
