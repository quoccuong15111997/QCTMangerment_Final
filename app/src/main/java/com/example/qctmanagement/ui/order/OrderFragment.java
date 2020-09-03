package com.example.qctmanagement.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.qctmanagement.R;
import com.example.qctmanagement.databinding.OrderFragmentBinding;
import com.example.qctmanagement.ui.order.list.OrderListActivity;
import com.example.qctmanagement.ui.prdimport.ImportActivity;

public class OrderFragment extends Fragment {

    private OrderViewModel mViewModel;
    private OrderFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_fragment, container, false);

        addControls();
        addEvnets();
        return binding.getRoot();
    }

    private void addEvnets() {
        binding.constraintLayoutImportList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImportActivity.class);
                startActivity(intent);
            }
        });
        binding.constraintLayoutOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        binding.include.txtTitle.setText(getResources().getString(R.string.the_order));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

    }

}