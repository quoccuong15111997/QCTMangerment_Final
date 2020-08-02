package com.example.qctmanagement.ui.product.detail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.databinding.ProductDetailFragmentBinding;
import com.example.qctmanagement.databinding.ProductDetailFragmentBindingImpl;

public class ProductDetailFragment extends Fragment {

    private ProductDetailViewModel mViewModel;
    private ProductDetailFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.product_detail_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void getData() {
        Intent intent= getActivity().getIntent();
        ProductItemApiResponse productItemApiResponse = (ProductItemApiResponse) intent.getSerializableExtra(Constant.KEY_PUT_PRODUCT);
        if (productItemApiResponse!=null){
            mViewModel.setProductData(productItemApiResponse);
        }
    }

    private void addEvents() {
    }

    private void addControls() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductDetailViewModel.class);
        getData();
        mViewModel.getProductData().observe(getViewLifecycleOwner(), new Observer<ProductItemApiResponse>() {
            @Override
            public void onChanged(ProductItemApiResponse productItemApiResponse) {
                binding.setProduct(productItemApiResponse);
                Glide.with(getActivity()).load(productItemApiResponse.getImage()).into(binding.imgProduct);
            }
        });
    }

}