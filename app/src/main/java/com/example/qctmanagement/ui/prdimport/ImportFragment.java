package com.example.qctmanagement.ui.prdimport;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.ImportAdapter;
import com.example.qctmanagement.api.model.reponse.prdimport.ImportApiResponse;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.databinding.ImportFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportFragment extends Fragment {

    private ImportViewModel mViewModel;
    private List<ImportApiResponse> list;
    private List<ImportApiResponse> listCurent;
    private ImportAdapter importAdapter;
    private ImportFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.import_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include8.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        importAdapter.setImportOnClickListener(new ImportAdapter.ImportOnClickListener() {
            @Override
            public void onOnItemClick(ImportApiResponse item) {

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
                dofilter(s.toString().toLowerCase());
            }
        });
    }

    private void dofilter(String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.addAll(listCurent
                    .stream()
                    .filter(x -> x.getImportHeaders().get(0).getiMPORTCODE().toString().equals(s))
                    .collect(Collectors.toList()));
        }
        binding.recycle.post(new Runnable() {
            @Override
            public void run() {
                importAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addControls() {
        binding.include8.txtTitle.setText("Danh sách phiếu nhập");

        list = new ArrayList<>();
        listCurent= new ArrayList<>();
        importAdapter = new ImportAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycle.setAdapter(importAdapter);
        binding.recycle.setLayoutManager(linearLayoutManager);

        loadData();
    }

    private void loadData() {
        ApiService.getInstance().getAllImport(new Callback<List<ImportApiResponse>>() {
            @Override
            public void onResponse(Call<List<ImportApiResponse>> call, Response<List<ImportApiResponse>> response) {
                if (response.isSuccessful()){
                    List<ImportApiResponse> importApiResponses = response.body();
                    list.clear();
                    listCurent.clear();
                    listCurent.addAll(importApiResponses);
                    list.addAll(listCurent);

                    binding.txtNumber.setText("Số phiếu: "+list.size());
                    importAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ImportApiResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ImportViewModel.class);
        // TODO: Use the ViewModel
    }

}