package com.example.qctmanagement.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.qctmanagement.MainActivity;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.user.UserApiResponse;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.common.FragmentCommon;
import com.example.qctmanagement.databinding.LoginFragmentBinding;
import com.example.qctmanagement.sharedpreferences.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends FragmentCommon {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding binding;
    private int CAMERA_PERMISSION_CODE = 1;
    private int STORAGE_PERMISSION_CODE = 2;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        addControls();
        addEvents();

        return binding.getRoot();
    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtUserName.getText().toString().equals("")){
                    if (!binding.edtPassword.getText().toString().equals("")){
                        doLogin();
                    }
                    else {
                        snackBarIconError("Vui lòng nhập mật khẩu");
                    }
                }
                else {
                    snackBarIconError("Vui lòng tài khoản");
                }
            }
        });
    }

    private void doLogin() {
        progressdialog.show();
        ApiService.getInstance().doLogin(binding.edtUserName.getText().toString(), binding.edtPassword.getText().toString(), new Callback<List<UserApiResponse>>() {
            @Override
            public void onResponse(Call<List<UserApiResponse>> call, Response<List<UserApiResponse>> response) {
                if (response.isSuccessful()){
                   if (response.body().size()>0){
                      // snackBarIconSuccess("Đăng nhập thành công");
                       if (binding.chkRemember.isChecked()){
                           SharedPreferencesManager.setPrefIsSavePassword(true);
                           SharedPreferencesManager.setPrefUserName(response.body().get(0).getUsername());
                           SharedPreferencesManager.setPrefPassWord(response.body().get(0).getPassword());
                       }
                       else {
                           SharedPreferencesManager.setPrefIsSavePassword(false);
                       }
                       List<UserApiResponse> userApiResponseList = response.body();
                       UserApiResponse userApiResponse = userApiResponseList.get(0);

                       SharedPreferencesManager.setPrefAccountName(userApiResponse.getItemName());
                       SharedPreferencesManager.setPrefAccountAddress(userApiResponse.getAddress());
                       SharedPreferencesManager.setPrefAccountCode(userApiResponse.getItemCode());
                       SharedPreferencesManager.setPrefAccountEmail(userApiResponse.getEmail());
                       SharedPreferencesManager.setPrefAccountPhone(userApiResponse.getPhone());
                       SharedPreferencesManager.setPrefAccountImage(userApiResponse.getImage());
                       SharedPreferencesManager.setPrefRoleCode(userApiResponse.getRoleCode());


                       Intent intent= new Intent(getContext(), MainActivity.class);
                       startActivity(intent);
                       getActivity().finish();
                   }
                   else {
                       progressdialog.dismiss();
                       snackBarIconError("Sai thông tin đăng nhập");
                   }
                }
                else {
                    progressdialog.dismiss();
                    snackBarIconError("Sai thông tin đăng nhập");
                }
            }

            @Override
            public void onFailure(Call<List<UserApiResponse>> call, Throwable t) {
                progressdialog.dismiss();
                snackBarIconError("Sai thông tin đăng nhập");
            }
        });
    }

    private void addControls() {
        initProgressDialog("Đang đăng nhập","Vui lòng chờ....");
        if (SharedPreferencesManager.getPrefIsSavePassword()){
            binding.edtUserName.setText(SharedPreferencesManager.getPrefUserName());
            binding.edtPassword.setText(SharedPreferencesManager.getPrefPassWord());
            binding.chkRemember.setChecked(true);
        }
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,1);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
        checkPermission(Manifest.permission.CAMERA, 3);
    }
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                getContext(),
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            getActivity(),
                            new String[] { permission },
                            requestCode);
        }
        else {
            /*Toast
                    .makeText(getContext(),
                            "Vui lòng cấp quyền để sử dụng ứng dụng",
                            Toast.LENGTH_SHORT)
                    .show();*/
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(getContext(),
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(getContext(),
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(),
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(getContext(),
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}