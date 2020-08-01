package com.example.qctmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.callback.ProductListCallback;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private List<ProductItemApiResponse> list;
    private ProductListCallback productListCallback;

    public ProductAdapter(List<ProductItemApiResponse> list, ProductListCallback productListCallback) {
        this.list = list;
        this.productListCallback = productListCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductItemApiResponse item = list.get(position);
        holder.txtQuantityValue.setText(String.valueOf((int)item.getQuantity()));
        holder.txtName.setText(item.getItemName());
        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtName, txtQuantityValue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtName=itemView.findViewById(R.id.txtName);
            txtQuantityValue=itemView.findViewById(R.id.txtQuantityValue);
        }
    }
}
