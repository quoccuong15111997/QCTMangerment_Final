package com.example.qctmanagement.ui.product.list;

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

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ProductAdapter;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.callback.ProductListCallback;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.databinding.ProductListFragmentBinding;
import com.example.qctmanagement.ui.product.detail.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private ProductListViewModel mViewModel;
    private ProductListFragmentBinding binding;
    private RecyclerView recyclerViewProduct;
    private List<ProductItemApiResponse> list;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.product_list_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
    }

    private void addControls() {
        recyclerViewProduct=binding.recycleProduct;
        list= new ArrayList<>();
        productAdapter= new ProductAdapter(list, new ProductListCallback() {
            @Override
            public void onProductClick(ProductItemApiResponse product) {
                Intent intent= new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra(Constant.KEY_PUT_PRODUCT,product);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewProduct.setAdapter(productAdapter);
        recyclerViewProduct.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        mViewModel.getItemApiResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ProductItemApiResponse>>() {
            @Override
            public void onChanged(List<ProductItemApiResponse> productItemApiResponses) {
                list.clear();
                list.addAll(productItemApiResponses);
                recyclerViewProduct.post(new Runnable() {
                    @Override
                    public void run() {
                        productAdapter.notifyDataSetChanged();
                        binding.txtSum.setText(list.size()+" sản phẩm");
                    }
                });
            }
        });
    }

}