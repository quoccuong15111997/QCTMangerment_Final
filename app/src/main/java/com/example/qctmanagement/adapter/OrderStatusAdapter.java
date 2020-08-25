package com.example.qctmanagement.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusDetail;
import com.example.qctmanagement.common.Util;


import java.util.List;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.ViewHolder> {
    public interface OrderStatusCallback{
        void onItemClick(OrderStatusApiresponse orderApiResponse, int position);
    }

    private List<OrderStatusApiresponse> list;
    private OrderStatusCallback callback;

    public OrderStatusAdapter(List<OrderStatusApiresponse> list, OrderStatusCallback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_order_status,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStatusApiresponse apiresponse = list.get(position);
        holder.txtProductName.setText(apiresponse.getOrderStatusDetails().get(0).getpRDNAME());
        holder.txtStatus.setText(apiresponse.getOrderStatusHeaders().get(0).getsTTNAME());
        holder.txtQuatity.setText(String.valueOf((int)apiresponse.getOrderStatusDetails().get(0).getqUATITY()));
        holder.txtProductPrice.setText(Util.convertToCurrencyVN(apiresponse.getOrderStatusDetails().get(0).getpRDSALEPRICE()));
        double sumPrice = 0;
        for (OrderStatusDetail detail : apiresponse.getOrderStatusDetails()){
            sumPrice+=(detail.getpRDSALEPRICE() * detail.getqUATITY());
        }
        holder.txtSumPrice.setText(Util.convertToCurrencyVN(sumPrice));
        holder.txtDate.setText(Util.formatDate(apiresponse.getOrderStatusHeaders().get(0).getoRDDATE()));
        Glide.with(holder.itemView.getContext()).load(apiresponse.getOrderStatusDetails().get(0).getpRDIMAGE()).into(holder.imgProduct);
        holder.txtNumberProduct.setText(String.valueOf(apiresponse.getOrderStatusDetails().size()));

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CLCIK aDAPTER: "+ position);
                callback.onItemClick(apiresponse, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtProductName, txtProductPrice,txtSumPrice, txtQuatity, txtStatus, txtNumberProduct, txtDate;
        ImageView imgColor, imgProduct;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtQuatity = itemView.findViewById(R.id.txtQuatity);
            txtSumPrice = itemView.findViewById(R.id.txtProductSumPrice);
            imgColor = itemView.findViewById(R.id.color1BgImageVieww);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtNumberProduct =itemView.findViewById(R.id.txtNumberProduct);
            txtDate = itemView.findViewById(R.id.txtDate);
            constraintLayout = itemView.findViewById(R.id.layout);
        }
    }
}
