package com.example.qctmanagement.ui.order;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.databinding.OrderFragmentBinding;

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