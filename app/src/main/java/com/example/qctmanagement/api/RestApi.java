package com.example.qctmanagement.api;

import com.example.qctmanagement.api.model.reponse.Category;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.reponse.SumProductApiResponse;
import com.example.qctmanagement.api.model.reponse.Unit;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.reponse.order.status.update.UpdateStatusApiResponse;
import com.example.qctmanagement.api.model.reponse.prdimport.ImportApiResponse;
import com.example.qctmanagement.api.model.reponse.user.UserApiResponse;
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
    Call<List<UpdateStatusApiResponse>> updateStatusOrder(@Query("employeeCode") String employeeCode,@Query("ordCode") int orderCode,@Query("sttCode") int sstCode);

    @POST("/api/employee")
    Call<List<UserApiResponse>> dologin(@Query("username") String username, @Query("password") String password,@Query("token") String token);

    @POST("/api/order?runcode=ORD.001")
    Call<OrderStatusApiresponse> getOrderInfo(@Body JsonObject jsonObject);

    @POST("api/product?runCode=PRD.004")
    Call<Boolean> updateProduct(@Body JsonObject jsonObject);

    @POST("/api/category")
    Call<List<Category>> getAllCategory();

    @POST("/api/unit")
    Call<List<Unit>> getAllUnit();

    @POST("/api/customer")
    Call<Integer> activeCustomer(@Query("username") String username,@Query("sttCode") int sttCode);

    @POST("/api/customer?getall")
    Call<List<CustomerApiResponse>> getAllCustomer();

    @POST("api/product?runCode=PRD.003")
    Call<Boolean> insertNewProduct(@Body JsonObject body);

    @POST("/api/import?getAll")
    Call<List<ImportApiResponse>> getAllInportList();
}