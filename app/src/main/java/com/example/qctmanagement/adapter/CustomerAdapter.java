package com.example.qctmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.common.Util;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>  {
    public interface CustomerClickCallback{
        void onClickActive(CustomerApiResponse customer);
    }
    private CustomerClickCallback customerClickCallback;

    public CustomerAdapter(List<CustomerApiResponse> customerApiResponses) {
        this.customerApiResponses = customerApiResponses;
    }

    public void setCustomerClickCallback(CustomerClickCallback customerClickCallback) {
        this.customerClickCallback = customerClickCallback;
    }

    private List<CustomerApiResponse> customerApiResponses;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_customer,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerApiResponse item = customerApiResponses.get(position);
        holder.txtCustName.setText(item.getiTEMNAME());
        holder.txtPhone.setText(item.getpHONE());
        holder.txtDateTime.setText(Util.convertTime(item.getcREATEDATE()));
        holder.aSwitchActive.setChecked(item.getsTATUS() == 1 ? true : false);
        Glide.with(holder.itemView.getContext()).load(item.getiMAGE()).into(holder.imageView);
        holder.aSwitchActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerClickCallback.onClickActive(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerApiResponses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCustName, txtPhone, txtDateTime;
        Switch aSwitchActive;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCustName =itemView.findViewById(R.id.txtCustName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtDateTime  = itemView.findViewById(R.id.txtDateTime);
            aSwitchActive = itemView.findViewById(R.id.switchActive);
            imageView = itemView.findViewById(R.id.imgCust);
        }
    }
}
