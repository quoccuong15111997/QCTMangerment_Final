package com.example.qctmanagement.ui.product.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ImageListAdapter;
import com.example.qctmanagement.api.model.reponse.Category;
import com.example.qctmanagement.api.model.reponse.ImageURL;
import com.example.qctmanagement.api.model.reponse.ProductItemApiResponse;
import com.example.qctmanagement.api.model.reponse.Unit;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.callback.ConfirmDialogCallback;
import com.example.qctmanagement.callback.ImageClickCallback;
import com.example.qctmanagement.common.Constant;
import com.example.qctmanagement.common.FragmentCommon;
import com.example.qctmanagement.databinding.ProductDetailFragmentBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends FragmentCommon implements ImageClickCallback {

    private ProductDetailViewModel mViewModel;
    private ProductDetailFragmentBinding binding;
    private RecyclerView recyclerViewImage;
    private ImageListAdapter imageListAdapter;
    private List<ImageURL> list;
    private ArrayAdapter<Category> categoryArrayAdapter;
    private List<Category> categoryList;
    private ArrayAdapter<Unit> unitArrayAdapter;
    private List<Unit> unitList;
    private ProductItemApiResponse productItemApiResponse;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.product_detail_fragment, container, false);

        getData();
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void getData() {
        Intent intent= getActivity().getIntent();
        productItemApiResponse = (ProductItemApiResponse) intent.getSerializableExtra(Constant.KEY_PUT_PRODUCT);
    }

    private void addEvents() {
        binding.include3.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.include3.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.include3.imgEdit.setVisibility(View.INVISIBLE);
                binding.include3.imgSave.setVisibility(View.VISIBLE);
                binding.spinerCate.setEnabled(true);
                binding.spinerUnit.setEnabled(true);
                binding.txtDescription.setEnabled(true);
            }
        });
        binding.include3.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog("XÁC NHẬN", "Luu thay đổi?", new ConfirmDialogCallback() {
                    @Override
                    public void onAccept() {
                        progressdialog.show();
                        Category category = (Category) binding.spinerCate.getSelectedItem();
                        Unit unit = (Unit) binding.spinerUnit.getSelectedItem();
                        productItemApiResponse.setcATEGORYCODE(category.getItemCode());
                        productItemApiResponse.setuNITCODE(unit.getItemCode());
                        productItemApiResponse.setdESCRIPTION(binding.txtDescription.getText().toString());
                        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(productItemApiResponse), JsonObject.class);
                        ApiService.getInstance().updateProduct(jsonObject, new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.isSuccessful()){
                                    if (response.body()){
                                        progressdialog.dismiss();
                                        snackBarIconSuccess("Cập nhật thành công");
                                        mViewModel.loadData(productItemApiResponse.getiTEMCODE());
                                        binding.spinerCate.setEnabled(false);
                                        binding.spinerUnit.setEnabled(false);
                                        binding.txtDescription.setEnabled(false);
                                        binding.include3.imgEdit.setVisibility(View.VISIBLE);
                                        binding.include3.imgSave.setVisibility(View.INVISIBLE);
                                        return;
                                    }
                                }
                                progressdialog.dismiss();
                                snackBarIconError("Cập nhật thất bại");

                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
    }

    private void addControls() {
        initProgressDialog("Đang thực hiện","Vui lòng chờ...");

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

        unitList = new ArrayList<>();
        unitArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,unitList);
        unitArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerUnit.setAdapter(unitArrayAdapter);

        categoryList = new ArrayList<>();
        categoryArrayAdapter= new  ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,categoryList);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerCate.setAdapter(categoryArrayAdapter);

        binding.spinerCate.setEnabled(false);
        binding.spinerUnit.setEnabled(false);
        binding.txtDescription.setEnabled(false);
        loadData();
    }

    private void loadData() {
        ApiService.getInstance().getAllCategory(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    categoryList.clear();
                    categoryList.addAll(response.body());
                    categoryArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
        ApiService.getInstance().getAllUnit(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                if (response.isSuccessful()){
                    unitList.clear();
                    unitList.addAll(response.body());
                    unitArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductDetailViewModel.class);
        getData();
        mViewModel.loadData(productItemApiResponse.getiTEMCODE());
        mViewModel.getProductData().observe(getViewLifecycleOwner(), new Observer<ProductItemApiResponse>() {
            @Override
            public void onChanged(ProductItemApiResponse productItemApiResponse) {
                binding.setProduct(productItemApiResponse);
                Glide.with(getActivity()).load(productItemApiResponse.getiMAGE()).into(binding.imgProduct);
                list.clear();
                for (int i=0;i<categoryList.size();i++){
                    Category category = categoryList.get(i);
                    if (category.getItemCode()==productItemApiResponse.getcATEGORYCODE()){
                        binding.spinerCate.setSelection(i, true);
                        break;
                    }
                }
                for (int i=0;i<unitList.size();i++){
                    Unit unit = unitList.get(i);
                    if (unit.getItemCode()==productItemApiResponse.getuNITCODE()){
                        binding.spinerUnit.setSelection(i, true);
                        break;
                    }
                }
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