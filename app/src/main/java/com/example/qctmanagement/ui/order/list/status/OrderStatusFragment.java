package com.example.qctmanagement.ui.order.list.status;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.OrderStatusAdapter;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.common.FragmentCommon;
import com.example.qctmanagement.databinding.OrderStatusFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusFragment extends FragmentCommon {

    private OrderStatusViewModel mViewModel;
    private OrderStatusFragmentBinding binding;
    private RecyclerView recyclerView;
    private List<OrderStatusApiresponse> list;
    private OrderStatusAdapter orderStatusAdapter;
    private int statusCode;

    public OrderStatusFragment(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.order_status_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
    }

    private void addControls() {

        list = new ArrayList<>();
        recyclerView = binding.recycle;
        orderStatusAdapter= new OrderStatusAdapter(list, new OrderStatusAdapter.OrderStatusCallback() {
            @Override
            public void onItemClick(OrderStatusApiresponse orderApiResponse, int position) {
                System.out.println("CLICK "+position);
              /*  Intent intent = new Intent(getContext(), OrderInfoActivity.class);
                intent.putExtra(Constant.NAME_PUT_ORDER_CODE,orderApiResponse.getOrderStatusHeaders().get(0).getoRDCODE());
                startActivity(intent);*/
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(orderStatusAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderStatusViewModel.class);
        mViewModel.loadData(statusCode);
        mViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<OrderStatusApiresponse>>() {
            @Override
            public void onChanged(List<OrderStatusApiresponse> orderStatusApiresponses) {
                list.clear();
                list.addAll(orderStatusApiresponses);
                orderStatusAdapter.notifyDataSetChanged();
            }
        });
    }

}