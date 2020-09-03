package com.example.qctmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.prdimport.ImportApiResponse;
import com.example.qctmanagement.common.Util;

import java.util.List;

public class ImportAdapter extends RecyclerView.Adapter<ImportAdapter.ViewHolder> {
    public interface ImportOnClickListener{
        void onOnItemClick(ImportApiResponse item);
    }
    private List<ImportApiResponse> list;
    private ImportOnClickListener importOnClickListener;

    public ImportAdapter(List<ImportApiResponse> list) {
        this.list = list;
    }

    public void setImportOnClickListener(ImportOnClickListener importOnClickListener) {
        this.importOnClickListener = importOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_import, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImportApiResponse importApiResponse = list.get(position);
        holder.txtImportCode.setText(importApiResponse.getImportHeaders().get(0).getiMPORTCODE()+"");
        holder.txtEmployee.setText(importApiResponse.getImportHeaders().get(0).geteMPNAME());
        holder.txtImportDate.setText(Util.formatDate(importApiResponse.getImportHeaders().get(0).getiMPDATE()));
        holder.txtNumber.setText(String.valueOf(importApiResponse.getImportDetails().size()));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importOnClickListener.onOnItemClick(importApiResponse);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtImportCode, txtImportDate, txtEmployee, txtNumber;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtImportCode= itemView.findViewById(R.id.txtImportCode);
            txtImportDate=itemView.findViewById(R.id.txtDate);
            txtEmployee = itemView.findViewById(R.id.txtEmployee);
            txtNumber=itemView.findViewById(R.id.txtNumber);
            layout = itemView.findViewById(R.id.layoutMain);
        }
    }
}
