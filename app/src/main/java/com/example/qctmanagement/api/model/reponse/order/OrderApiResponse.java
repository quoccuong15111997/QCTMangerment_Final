package com.example.qctmanagement.api.model.reponse.order;

import com.example.qctmanagement.api.model.request.order.OrderRequestDetail;
import com.example.qctmanagement.api.model.request.order.OrderRequestHeader;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class OrderApiResponse {
    @SerializedName("HEADER")
    private List<OrderRequestHeader> orderRequestHeaders;
    @SerializedName("DETAIL")
    private List<OrderRequestDetail> orderRequestDetails;

    public OrderApiResponse() {
    }

    public List<OrderRequestHeader> getOrderRequestHeaders() {
        return orderRequestHeaders;
    }

    public void setOrderRequestHeaders(List<OrderRequestHeader> orderRequestHeaders) {
        this.orderRequestHeaders = orderRequestHeaders;
    }

    public List<OrderRequestDetail> getOrderRequestDetails() {
        return orderRequestDetails;
    }

    public void setOrderRequestDetails(List<OrderRequestDetail> orderRequestDetails) {
        this.orderRequestDetails = orderRequestDetails;
    }
}
