package com.example.qctmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.ImageURL;
import com.example.qctmanagement.callback.ImageClickCallback;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>{
    private List<ImageURL> list;
    private ImageClickCallback imageClickCallback;

    public ImageListAdapter(List<ImageURL> list, ImageClickCallback imageClickCallback) {
        this.list = list;
        this.imageClickCallback = imageClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_image_list,parent,false);
        return new ImageListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ImageURL imageURL= list.get(position);
        Glide.with(holder.itemView.getContext()).load(imageURL.getUrl()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickCallback.onImageClick(imageURL.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
