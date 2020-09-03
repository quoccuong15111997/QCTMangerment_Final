package com.example.qctmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.callback.SupportClickCallback;
import com.example.qctmanagement.firebase.SupportFirebase;

import java.util.List;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.ViewHolder> {
    private List<SupportFirebase> stringList;
    private SupportClickCallback supportClickCallback;

    public void setSupportClickCallback(SupportClickCallback supportClickCallback) {
        this.supportClickCallback = supportClickCallback;
    }

    public SupportAdapter(List<SupportFirebase> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_support,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position).getTitle());
        holder.txtNumber.setText(String.valueOf(stringList.get(position).getNumber()));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportClickCallback.onItemClick(stringList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView, txtNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.txtCustName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
        }
    }
}
