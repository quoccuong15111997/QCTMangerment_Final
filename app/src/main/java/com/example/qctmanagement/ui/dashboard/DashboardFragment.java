package com.example.qctmanagement.ui.dashboard;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.model.reponse.SumProductApiResponse;
import com.example.qctmanagement.common.Util;
import com.example.qctmanagement.databinding.DashboardFragmentBinding;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel mViewModel;
    private DashboardFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addControls() {
    }

    private void addEvents() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        mViewModel.getSumProductApiResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<SumProductApiResponse>() {
            @Override
            public void onChanged(SumProductApiResponse sumProductApiResponse) {
                binding.warehouse.textView40.setText(String.valueOf((int)sumProductApiResponse.getSumProduct()));
                binding.warehouse.textView42.setText(Util.convertToCurrencyVN(sumProductApiResponse.getSumPrice()));
                binding.warehouse.textView421.setText(Util.convertToCurrencyVN(sumProductApiResponse.getSumPriceSale()));
                binding.warehouse.textView4211.setText(Util.convertToCurrencyVN(sumProductApiResponse.getSumProfit()));
            }
        });
        mViewModel.getListMutableLiveDataCustommerNewToday().observe(getViewLifecycleOwner(), new Observer<List<CustomerApiResponse>>() {
            @Override
            public void onChanged(List<CustomerApiResponse> customerApiResponses) {
                binding.textView2.setText(String.valueOf(customerApiResponses.size()));
            }
        });
        mViewModel.getNumberOrderComplete().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView9.setText(String.valueOf(integer));
            }
        });
        mViewModel.getNumberOrder().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView12.setText(String.valueOf(integer));
            }
        });
        mViewModel.getNumberOrderCancel().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView15.setText(String.valueOf(integer));
            }
        });
        mViewModel.getNumberOrderReturn().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView18.setText(String.valueOf(integer));
            }
        });
    }

}