package com.example.qctmanagement.api.model.reponse.order.status;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderStatusApiresponse {
    @SerializedName("HEADER")
    private List<OrderStatusHeader> orderStatusHeaders;
    @SerializedName("DETAIL")
    private List<OrderStatusDetail> orderStatusDetails;

    public OrderStatusApiresponse() {
    }

    public List<OrderStatusHeader> getOrderStatusHeaders() {
        return orderStatusHeaders;
    }

    public void setOrderStatusHeaders(List<OrderStatusHeader> orderStatusHeaders) {
        this.orderStatusHeaders = orderStatusHeaders;
    }

    public List<OrderStatusDetail> getOrderStatusDetails() {
        return orderStatusDetails;
    }

    public void setOrderStatusDetails(List<OrderStatusDetail> orderStatusDetails) {
        this.orderStatusDetails = orderStatusDetails;
    }
}
