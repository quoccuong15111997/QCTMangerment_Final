package com.example.qctmanagement.ui.order.list.detail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ProductOrderInfoAdapter;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusApiresponse;
import com.example.qctmanagement.api.model.reponse.order.status.OrderStatusDetail;
import com.example.qctmanagement.api.model.reponse.order.status.update.UpdateStatusApiResponse;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.callback.ConfirmDialogCallback;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.common.FragmentCommon;
import com.example.qctmanagement.common.Util;
import com.example.qctmanagement.databinding.OrderDetailFragmentBinding;
import com.example.qctmanagement.model.OrderStatusModel;
import com.example.qctmanagement.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailFragment extends FragmentCommon {

    private OrderDetailViewModel mViewModel;
    private OrderDetailFragmentBinding binding;
    private ProductOrderInfoAdapter productOrderInfoAdapter;
    private List<OrderStatusDetail> orderStatusDetails;
    private int orderCode = 0;
    private ArrayAdapter<OrderStatusModel> statusModelArrayAdapter;
    private List<OrderStatusModel> orderStatusModelList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_detail_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include6.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog("XÁC NHẬN", "Bạn chắc chắn muốn cập nhật trạng thái đơn hàng", new ConfirmDialogCallback() {
                    @Override
                    public void onAccept() {
                        doUpdateStatus();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
    }

    private void doUpdateStatus() {
        progressdialog.show();
        OrderStatusModel orderStatusModel = (OrderStatusModel) binding.spinerStatus.getSelectedItem();
        ApiService.getInstance().updateStatusOrder(SharedPreferencesManager.getPrefAccountCode(),orderCode, orderStatusModel.getCode(), new Callback<List<UpdateStatusApiResponse>>() {
            @Override
            public void onResponse(Call<List<UpdateStatusApiResponse>> call, Response<List<UpdateStatusApiResponse>> response) {
                if (response.isSuccessful()){
                    if (response.body().size()>0){
                        progressdialog.show();
                        showSuccessDialog("THÀNH CÔNG", "Cập nhật đơn hàng thành công");
                    }
                    else {
                        progressdialog.show();
                        showErrorDialog("THẤT BẠI","Cập nhật đơn hàng thất bại.");
                    }
                }
                else {
                    progressdialog.show();
                    showErrorDialog("THẤT BẠI","Cập nhật đơn hàng thất bại.");
                }
            }

            @Override
            public void onFailure(Call<List<UpdateStatusApiResponse>> call, Throwable t) {
                progressdialog.show();
                showErrorDialog("THẤT BẠI","Cập nhật đơn hàng thất bại.");
            }
        });
    }

    private void addControls() {
        initProgressDialog("Đang thực hiện","Vui lòng chờ");
        Intent intent = getActivity().getIntent();
        orderCode = intent.getIntExtra(Constant.NAME_PUT_ORDER_CODE,0);

        binding.include6.txtTitle.setText("Chi tiết đơn hàng");

        orderStatusDetails= new ArrayList<>();
        productOrderInfoAdapter = new ProductOrderInfoAdapter(orderStatusDetails);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleProduct.setLayoutManager(linearLayoutManager);
        binding.recycleProduct.setAdapter(productOrderInfoAdapter);

        orderStatusModelList= new ArrayList<>();
        statusModelArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,orderStatusModelList);
        statusModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinerStatus.setAdapter(statusModelArrayAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderDetailViewModel.class);
        mViewModel.loadData(orderCode);
        mViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<OrderStatusApiresponse>() {
            @Override
            public void onChanged(OrderStatusApiresponse orderStatusApiresponse) {
                orderStatusDetails.clear();
                orderStatusDetails.addAll(orderStatusApiresponse.getOrderStatusDetails());
                binding.setItem(orderStatusApiresponse.getOrderStatusHeaders().get(0));
                binding.txtDate.setText("Ngày đặt hàng: "+ Util.formatDate(orderStatusApiresponse.getOrderStatusHeaders().get(0).getoRDDATE()));
                binding.txtShipFee.setText(Util.convertToCurrencyVN(30000));
                double sum = 0;
                for (OrderStatusDetail detail : orderStatusApiresponse.getOrderStatusDetails()){
                    sum+= (detail.getpRDPRICE() * detail.getqUATITY());
                }
                binding.txtSumSale.setText(Util.convertToCurrencyVN(sum));
                binding.txtSumAll.setText(Util.convertToCurrencyVN(sum+30000));
                productOrderInfoAdapter.notifyDataSetChanged();
                if (orderStatusApiresponse.getOrderStatusHeaders().get(0).getsTTCODE()==9){
                    binding.btnSave.setEnabled(false);
                }
                mViewModel.getLiveDataListStatusModel().observe(getViewLifecycleOwner(), new Observer<List<OrderStatusModel>>() {
                    @Override
                    public void onChanged(List<OrderStatusModel> orderStatusModels) {
                        orderStatusModelList.clear();
                        for (OrderStatusModel model : orderStatusModels){
                            if (model.getCode() > orderStatusApiresponse.getOrderStatusHeaders().get(0).getsTTCODE()){
                                orderStatusModelList.add(model);
                            }
                        }
                        statusModelArrayAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}