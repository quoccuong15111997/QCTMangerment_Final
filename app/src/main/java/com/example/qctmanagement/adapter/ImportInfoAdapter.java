package com.example.qctmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.prdimport.ImportDetail;

import java.util.List;

public class ImportInfoAdapter extends RecyclerView.Adapter<ImportInfoAdapter.ViewHolder> {
    public interface ImportDetailOnClickListener{
        void  onDeleteClick(ImportDetail importDetail);
        void onEditClick(ImportDetail importDetail);
    }
    private List<ImportDetail> importDetails;
    private ImportDetailOnClickListener importDetailOnClickListener;

    public ImportInfoAdapter(List<ImportDetail> importDetails) {
        this.importDetails = importDetails;
    }

    public void setImportDetailOnClickListener(ImportDetailOnClickListener importDetailOnClickListener) {
        this.importDetailOnClickListener = importDetailOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_import_detail, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImportDetail importDetail = importDetails.get(position);
        holder.txtCode.setText(importDetail.getpRDCODE());
        holder.txtName.setText(importDetail.getpRDNAME());
        holder.txtQuatity.setText(String.valueOf(importDetail.getqUATITY()));
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importDetailOnClickListener.onEditClick(importDetail);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importDetailOnClickListener.onDeleteClick(importDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return importDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCode, txtName, txtQuatity;
        ImageView imgEdit, imgDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode=itemView.findViewById(R.id.txtProductCode);
            txtName=itemView.findViewById(R.id.txtProductName);
            txtQuatity=itemView.findViewById(R.id.txtQuatity);
        }
    }
}
