package com.example.qctmanagement.ui.product.list;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ProductAdapter;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.callback.ProductListCallback;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.databinding.ProductListFragmentBinding;
import com.example.qctmanagement.ui.product.add.AddProductActivity;
import com.example.qctmanagement.ui.product.detail.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListFragment extends Fragment {

    private ProductListViewModel mViewModel;
    private ProductListFragmentBinding binding;
    private RecyclerView recyclerViewProduct;
    private List<ProductItemApiResponse> list;
    private List<ProductItemApiResponse> listCurrent;
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
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddProductActivity.class);
                startActivity(intent);
            }
        });
        binding.include2.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                doSearchProduct(s.toString());
            }
        });
    }

    private void doSearchProduct(final String s) {
        List<ProductItemApiResponse> listProductFiltered = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listProductFiltered.addAll(listCurrent
                    .stream()
                    .filter(x -> x.getiTEMNAME().contains(s) || x.getiTEMCODE().contains(s))
                    .collect(Collectors.<ProductItemApiResponse>toList()));
        }
        list.clear();
        list.addAll(listProductFiltered);
        productAdapter.notifyDataSetChanged();

    }

    private void addControls() {
        binding.include2.txtTitle.setText("Danh sách sản phẩm");
        recyclerViewProduct=binding.recycleProduct;
        list= new ArrayList<>();
        listCurrent= new ArrayList<>();
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
                listCurrent.addAll(productItemApiResponses);
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

    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel!=null){
            mViewModel.loadData();
        }
    }
}