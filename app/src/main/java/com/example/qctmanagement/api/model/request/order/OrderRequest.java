package com.example.qctmanagement.api.model.request.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderRequest {
    @SerializedName("HEADER")
    private OrderRequestHeader orderRequestHeader;
    @SerializedName("DETAIL")
    private List<OrderRequestDetail> orderRequestDetails;

    public OrderRequest() {
    }

    public OrderRequestHeader getOrderRequestHeader() {
        return orderRequestHeader;
    }

    public void setOrderRequestHeader(OrderRequestHeader orderRequestHeader) {
        this.orderRequestHeader = orderRequestHeader;
    }

    public List<OrderRequestDetail> getOrderRequestDetails() {
        return orderRequestDetails;
    }

    public void setOrderRequestDetails(List<OrderRequestDetail> orderRequestDetails) {
        this.orderRequestDetails = orderRequestDetails;
    }
}
