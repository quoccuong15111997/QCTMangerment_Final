package com.example.qctmanagement.ui.product.add;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.qctmanagement.R;
import com.example.qctmanagement.api.model.reponse.Category;
import com.example.qctmanagement.api.model.reponse.Unit;
import com.example.qctmanagement.api.model.request.product.ProductRequest;
import com.example.qctmanagement.api.service.ApiService;
import com.example.qctmanagement.callback.ConfirmDialogCallback;
import com.example.qctmanagement.callback.UploadImageCallback;
import com.example.qctmanagement.common.FragmentCommon;
import com.example.qctmanagement.common.NumberTextWatcher;
import com.example.qctmanagement.common.UploadProvider;
import com.example.qctmanagement.databinding.AddProductFragmentBinding;
import com.example.qctmanagement.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddProductFragment extends FragmentCommon {

    private AddProductViewModel mViewModel;
    private AddProductFragmentBinding binding;
    private ArrayAdapter adapterUnit;
    private List<Unit> units;
    private List<Category> categories;
    private ArrayAdapter adapterCategory;
    private String urlImage ="";
    private Bitmap bitmapCamera;
    private int REQUEST_CODE_IMAGE = 1;
    private int REQUEST_CODE_IMAGE_STORAGE = 2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_product_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.edtProductPrice.addTextChangedListener(new NumberTextWatcher(binding.edtProductPrice));
        binding.edtProductSellPrice.addTextChangedListener(new NumberTextWatcher(binding.edtProductSellPrice));

        binding.include3.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        binding.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(binding.imgProduct);
            }
        });
    }

    private void validateData() {
        if (!binding.txtOrderCode.getText().toString().equals("")){
            if (!binding.edtProductSellPrice.getText().toString().equals("")){
                if (!binding.edtProductPrice.getText().toString().equals("")){
                    if (!binding.edtProductQuatity.getText().toString().equals("")){
                        if (!binding.txtSDescription.getText().toString().equals("")){
                            if (!binding.txtDescription.getText().toString().equals("")){
                                if (binding.chkWhite.isChecked() || binding.chkBlack.isChecked() || binding.chkRed.isChecked()){
                                    showConfirmDialog("Xác nhận", "Bạn chắc chắn muốn thêm sản phẩm này", new ConfirmDialogCallback() {
                                        @Override
                                        public void onAccept() {
                                            doSave();
                                        }

                                        @Override
                                        public void onCancel() {

                                        }
                                    });
                                }
                                else {
                                    snackBarIconError("Vui lòng chọn màu sắc");
                                    return;
                                }
                            }
                            else {
                                snackBarIconError("Vui lòng nhập mô tả chi tiết sản phẩm");
                                return;
                            }
                        }
                        else {
                            snackBarIconError("Vui lòng nhập mô tả ngắn");
                            return;
                        }
                    }
                    else {
                        snackBarIconError("Vui lòng nhập số lượng nhập");
                    }
                }
                else {
                    snackBarIconError("Vui lòng nhập giá nhập");
                    return;
                }
            }
            else {
                snackBarIconError("Vui lòng nhập giá bán");
                return;
            }
        }
        else {
            snackBarIconError("Vui lòng nhập tên sản phẩm");
            return;
        }
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater()
                .inflate(R.menu.context_menu_image, menu);
    }

    private void doSave() {
       try {
           progressdialog.show();
           Unit unit = (Unit) binding.spinerUnit.getSelectedItem();
           Category category = (Category) binding.spinerCate.getSelectedItem();
           ProductRequest productRequest = new ProductRequest();
           productRequest.setiTEMNAME(binding.txtOrderCode.getText().toString());
           productRequest.setuNITCODE(unit.getItemCode());
           productRequest.setcATEGORYCODE(category.getItemCode());
           productRequest.setsDESCRIPTION(binding.txtSDescription.getText().toString());
           productRequest.setdESCRIPTION(binding.txtDescription.getText().toString());
           productRequest.setpRICE(Double.parseDouble(binding.edtProductPrice.getText().toString().replace(".","").trim()));
           productRequest.setsELLPRICE(Double.parseDouble(binding.edtProductSellPrice.getText().toString().replace(".","").trim()));
           productRequest.setdISCOUNTPRICE(productRequest.getsELLPRICE()+500000);
           productRequest.setdISCOUNT(false);
           productRequest.setcOLOR1(binding.chkRed.isChecked() ? "#ff0000" : "");
           productRequest.setcOLOR2(binding.chkBlack.isChecked() ? "#000000" : "");
           productRequest.setcOLOR3(binding.chkWhite.isChecked() ? "#ffffff" : "");
           productRequest.setiMAGE(urlImage);
           productRequest.setqUANTITY(Double.parseDouble(binding.edtProductQuatity.getText().toString()));

           productRequest.setEmployeeCode(SharedPreferencesManager.getPrefAccountCode());

           JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(productRequest), JsonObject.class);

           ApiService.getInstance().insertNewProduct(jsonObject, new Callback<Boolean>() {
               @Override
               public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                   if (response.isSuccessful()){
                       if (response.body().booleanValue()){
                           progressdialog.dismiss();
                           showSuccessDialog("Thành công", "Thêm sản phẩm thành công");
                       }
                       else {
                           progressdialog.dismiss();
                           showErrorDialog("Thất bại","Không thể thêm sản phẩm này");
                       }
                   }
                   else {
                       progressdialog.dismiss();
                       showErrorDialog("Thất bại","Không thể thêm sản phẩm này");
                   }
               }

               @Override
               public void onFailure(Call<Boolean> call, Throwable t) {
                   progressdialog.dismiss();
                   showErrorDialog("Thất bại","Không thể thêm sản phẩm này");
               }
           });
       }
       catch (Exception ex){
           progressdialog.dismiss();
           showErrorDialog("Thất bại","Không thể thêm sản phẩm này");
           ex.fillInStackTrace();
       }

    }
    private void doView() {
        showDialogImageFull(SharedPreferencesManager.getPrefAccountImage(), SharedPreferencesManager.getPrefAccountName());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemView:
                doView();
                break;
            case R.id.itemChange:
                doChange();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void doChange() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Ảnh từ");
        builder.setNegativeButton("Mở máy ảnh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        }).setPositiveButton("Bộ sưu tập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_CODE_IMAGE_STORAGE);
            }
        }).show();
    }
    private void addControls() {
        initProgressDialog("Đang thực hiện","Vui lòng chờ...");
        binding.include3.txtTitle.setText("Thêm sản phẩm mới");
        binding.include3.imgEdit.setVisibility(View.INVISIBLE);
        units = new ArrayList<>();
        adapterUnit= new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,units);
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerUnit.setAdapter(adapterUnit);

        categories = new ArrayList<>();
        adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerCate.setAdapter(adapterCategory);

        loadData();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);
    }
    private void loadData() {
        ApiService.getInstance().getAllCategory(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    categories.clear();
                    categories.addAll(response.body());
                    adapterCategory.notifyDataSetChanged();
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
                    units.clear();
                    units.addAll(response.body());
                    adapterUnit.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {

            }
        });
    }
    private void doUploadStroge(String path) {
        try{
            bitmapCamera=getThumbnail(path);
            doUpload();
        }
        catch (Exception ex){
            Log.e("LOI",ex.toString());
        }
    }

    private void doUpload() {
        progressdialog.show();
        String name = "product"+System.currentTimeMillis();
        try {
            UploadProvider.getInstance().doUploadImage(bitmapCamera, name, new UploadImageCallback() {
                @Override
                public void onUpLoadSucces(String url) {
                    urlImage = url;
                    Glide.with(getActivity()).load(url).into(binding.imgProduct);
                    progressdialog.dismiss();
                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    public Bitmap getThumbnail(String pathHinh)
    {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathHinh, bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;
        int originalSize = (bounds.outHeight > bounds.outWidth) ?
                bounds.outHeight
                : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 500;
        return BitmapFactory.decodeFile(pathHinh, opts);
    }
    private void showDialogImageFull(String url,String title) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_image);
        ImageView img = dialog.findViewById(R.id.img);
        TextView txtTitle =dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        Glide.with(getContext()).load(url).into(img);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            bitmapCamera = (Bitmap) data.getExtras().get("data");
            doUpload();
        }
        else if (requestCode == REQUEST_CODE_IMAGE_STORAGE && resultCode == RESULT_OK && data != null) {
            Uri uri=data.getData();
            String path=getRealPathFromURI(uri);
            doUploadStroge(path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}