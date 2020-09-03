package com.example.qctmanagement.adapter;

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
        holder.txtStatus.setText(apiresponse.getOrderStatusHeaders().get(0).getsTTNAME());
        double sumPrice = 0;
        for (OrderStatusDetail detail : apiresponse.getOrderStatusDetails()){
            sumPrice+=(detail.getpRDSALEPRICE() * detail.getqUATITY());
        }
        holder.txtSumPrice.setText(Util.convertToCurrencyVN(sumPrice));
        holder.txtDate.setText(Util.formatDate(apiresponse.getOrderStatusHeaders().get(0).getoRDDATE()));
        holder.txtNumberProduct.setText(String.valueOf(apiresponse.getOrderStatusDetails().size()));
        holder.txtCustName.setText(apiresponse.getOrderStatusHeaders().get(0).getcUSTNAME());
        holder.txtPhone.setText(apiresponse.getOrderStatusHeaders().get(0).getpHONE());
        holder.txtAddress.setText(apiresponse.getOrderStatusHeaders().get(0).getsHPADDR());
        holder.txtOrderCode.setText(String.valueOf(apiresponse.getOrderStatusHeaders().get(0).getoRDCODE()));
        holder.txtPayMethod.setText(apiresponse.getOrderStatusHeaders().get(0).getpAYMETHOD());

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
        TextView txtOrderCode, txtCustName,txtPhone, txtAddress,txtSumPrice, txtStatus, txtNumberProduct, txtDate,txtPayMethod;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSumPrice = itemView.findViewById(R.id.txtProductSumPrice);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtNumberProduct =itemView.findViewById(R.id.txtNumberProduct);
            txtDate = itemView.findViewById(R.id.txtDate);
            constraintLayout = itemView.findViewById(R.id.layout);
            txtOrderCode =itemView.findViewById(R.id.txtOrderCode);
            txtCustName = itemView.findViewById(R.id.txtCustName);
            txtPhone=itemView.findViewById(R.id.txtPhone);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtPayMethod = itemView.findViewById(R.id.txtPayMethod);
        }
    }
}
