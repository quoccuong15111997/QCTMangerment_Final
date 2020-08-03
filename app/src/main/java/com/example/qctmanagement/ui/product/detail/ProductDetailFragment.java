package com.example.qctmanagement.ui.product.detail;

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

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ImageListAdapter;
import com.example.qctmanagement.api.model.reponse.ImageURL;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.callback.ImageClickCallback;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.databinding.ProductDetailFragmentBinding;
import com.example.qctmanagement.databinding.ProductDetailFragmentBindingImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment implements ImageClickCallback {

    private ProductDetailViewModel mViewModel;
    private ProductDetailFragmentBinding binding;
    private RecyclerView recyclerViewImage;
    private ImageListAdapter imageListAdapter;
    private List<ImageURL> list;

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
        binding.include3.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void addControls() {
        binding.include3.txtTitle.setText("Thông tin sản phẩm");
        binding.include3.imgEdit.setVisibility(View.VISIBLE);
        binding.include3.imgSave.setVisibility(View.GONE);
        recyclerViewImage=binding.recycleImage;
        list= new ArrayList<>();
        imageListAdapter= new ImageListAdapter(list,this);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewImage.setLayoutManager(linearLayoutManager);
        recyclerViewImage.setAdapter(imageListAdapter);
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
                list.clear();
                list.addAll(productItemApiResponse.getListImageDetails());
                recyclerViewImage.post(new Runnable() {
                    @Override
                    public void run() {
                        imageListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onImageClick(String url) {

    }
}