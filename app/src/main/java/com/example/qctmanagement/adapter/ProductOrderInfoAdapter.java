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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusDetail;
import com.example.qctmanagement.common.Util;


import java.util.List;

public class ProductOrderInfoAdapter extends RecyclerView.Adapter<ProductOrderInfoAdapter.ViewHolder> {
    private List<OrderStatusDetail> orderStatusDetailList;

    public ProductOrderInfoAdapter(List<OrderStatusDetail> orderStatusDetailList) {
        this.orderStatusDetailList = orderStatusDetailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_order_info,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStatusDetail detail = orderStatusDetailList.get(position);
        holder.txtProductName.setText(detail.getpRDNAME());
        holder.txtQuatity.setText(String.valueOf((int)detail.getqUATITY()));
        holder.txtProductPrice.setText(Util.convertToCurrencyVN(detail.getpRDSALEPRICE()));
        holder.setBackgroundImageView(detail.getcOLOR());
        holder.txtSumPrice.setText(Util.convertToCurrencyVN(detail.getpRDSALEPRICE() * detail.getqUATITY()));
        Glide.with(holder.itemView.getContext()).load(detail.getpRDIMAGE()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return orderStatusDetailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtProductName, txtProductPrice,txtSumPrice, txtQuatity;
        ImageView imgColor, imgProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtQuatity = itemView.findViewById(R.id.txtQuatity);
            txtSumPrice = itemView.findViewById(R.id.txtProductSumPrice);
            imgColor = itemView.findViewById(R.id.color1BgImageVieww);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
        public void setBackgroundImageView(String colorValue){

            Bitmap b = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(b);
            canvas.drawColor(Color.parseColor(colorValue));
            bindCircleBitmap(imgColor,b);
        }
        public void bindCircleBitmap(ImageView imageView, Bitmap bitmap) {

            if(Util.isBitmapValid(imageView, bitmap,imageView.getContext())) {

                Glide.with(itemView.getContext()).load(bitmap).apply(RequestOptions.circleCropTransform()
                        .placeholder(R.drawable.circle_default_image)
                ).into(imageView);

            }else {

                if(imageView != null) {
                    imageView.setImageResource(R.drawable.circle_default_image);
                }
            }

        }
    }
}
