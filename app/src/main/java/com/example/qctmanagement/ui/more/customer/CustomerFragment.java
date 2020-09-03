package com.example.qctmanagement.ui.more.customer;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.CustomerAdapter;
import com.example.qctmanagement.api.model.reponse.CustomerApiResponse;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.common.FragmentCommon;
import com.example.qctmanagement.databinding.CustomerFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends FragmentCommon {

    private CustomerViewModel mViewModel;
    private CustomerFragmentBinding binding;
    private List<CustomerApiResponse> customerApiResponseList;
    private List<CustomerApiResponse> listCurrent;
    private CustomerAdapter customerAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.customer_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doFilter(editable.toString().toLowerCase());
            }
        });
        binding.radioButtonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerApiResponseList.clear();
                customerApiResponseList.addAll(listCurrent);
                binding.recycleCust.post(new Runnable() {
                    @Override
                    public void run() {
                        customerAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radioButtonActived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    customerApiResponseList.clear();
                    customerApiResponseList.addAll(listCurrent
                            .stream()
                            .filter(x -> x.getsTATUS() == 1)
                            .collect(Collectors.toList()));
                }
                binding.recycleCust.post(new Runnable() {
                    @Override
                    public void run() {
                        customerAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        binding.radioButtonNonActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    customerApiResponseList.clear();
                    customerApiResponseList.addAll(listCurrent
                            .stream()
                            .filter(x -> x.getsTATUS() == 0)
                            .collect(Collectors.toList()));
                }
                binding.recycleCust.post(new Runnable() {
                    @Override
                    public void run() {
                        customerAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        customerAdapter.setCustomerClickCallback(new CustomerAdapter.CustomerClickCallback() {
            @Override
            public void onClickActive(CustomerApiResponse customer) {
                progressdialog.show();
                ApiService.getInstance().activeCustomer(customer.getuSERNAME(), customer.getsTATUS() == 0 ? 1 : 0, new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()){
                            if (response.body() != 0 ){
                                binding.recycleCust.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mViewModel.loadData();
                                        customerAdapter.notifyDataSetChanged();
                                    }
                                });
                                progressdialog.dismiss();
                                snackBarIconSuccess("Cập nhật thành công");
                            }
                            else {
                                progressdialog.dismiss();
                                snackBarIconError("Cập nhật thất bại!");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        progressdialog.dismiss();
                        snackBarIconError("Cập nhật thất bại!");
                    }
                });
            }
        });
    }

    private void doFilter(String key) {
        customerApiResponseList.clear();
        if (binding.radioButtonAll.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                customerApiResponseList.addAll(listCurrent
                        .stream()
                        .filter(x -> x.getiTEMNAME().toLowerCase().contains(key) || x.getpHONE().toLowerCase().contains(key))
                        .collect(Collectors.toList()));
            }
        }
        if (binding.radioButtonActived.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                customerApiResponseList.addAll(listCurrent
                        .stream()
                        .filter(x -> (x.getiTEMNAME().toLowerCase().contains(key)|| x.getpHONE().toLowerCase().contains(key)) && x.getsTATUS() == 1)
                        .collect(Collectors.toList()));
            }
        }
        if (binding.radioButtonNonActive.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                customerApiResponseList.addAll(listCurrent
                        .stream()
                        .filter(x -> (x.getiTEMNAME().toLowerCase().contains(key)|| x.getpHONE().toLowerCase().contains(key)) && x.getsTATUS() == 0)
                        .collect(Collectors.toList()));
            }
        }
        binding.recycleCust.post(new Runnable() {
            @Override
            public void run() {
                customerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addControls() {
        initProgressDialog("Đang thực hiện", "Vui lòng chờ");
        progressdialog.show();

        listCurrent= new ArrayList<>();
        customerApiResponseList = new ArrayList<>();
        customerAdapter = new CustomerAdapter(customerApiResponseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleCust.setAdapter(customerAdapter);
        binding.recycleCust.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation());
        binding.recycleCust.addItemDecoration(dividerItemDecoration);

        binding.radioButtonAll.setChecked(true);

        binding.include5.txtTitle.setText("Khách hàng");

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<CustomerApiResponse>>() {
            @Override
            public void onChanged(List<CustomerApiResponse> list) {
                customerApiResponseList.clear();
                listCurrent.clear();
                listCurrent.addAll(list);
                customerApiResponseList.addAll(listCurrent);
                customerAdapter.notifyDataSetChanged();
                progressdialog.dismiss();
            }
        });
    }

}