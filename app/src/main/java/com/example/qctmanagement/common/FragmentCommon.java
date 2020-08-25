package com.example.qctmanagement.common;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.qctmanagement.R;
import com.example.qctmanagement.callback.ConfirmDialogCallback;
import com.google.android.material.snackbar.Snackbar;


import java.text.SimpleDateFormat;

public abstract class FragmentCommon extends Fragment {
    protected android.app.AlertDialog alertDialog;
    protected ProgressDialog progressdialog;
    protected SimpleDateFormat simpleDateFormatDisplay;
    protected SimpleDateFormat simpleDateFormatSystem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simpleDateFormatSystem= new SimpleDateFormat("yyyy-MM-dd");

    }

    protected void snackBarIconError(String mess) {
        final Snackbar snackbar = Snackbar.make(getView(), "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(mess);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_baseline_close_24);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.red_600));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    protected void snackBarIconSuccess(String mess) {
        final Snackbar snackbar = Snackbar.make(getView(), "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(mess);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_outline_done_all_24);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.green_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }
    protected void showConfirmDialog(String title, String message, ConfirmDialogCallback confirmDialogCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmDialogCallback.onAccept();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmDialogCallback.onCancel();
            }
        });
        builder.show();
    }
    protected void initProgressDialog(String title, String mess){
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setTitle(title);
        progressdialog.setMessage(mess);
        progressdialog.setCancelable(false);
    }
    protected void showErrorDialog(String title, String mess) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView txtTitle = dialog.findViewById(R.id.title);
        TextView txtContent = dialog.findViewById(R.id.content);

        txtTitle.setText(title);
        txtContent.setText(mess);


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    protected void showSuccessDialog(String title, String mess) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_info);
        dialog.setCancelable(true);

        TextView txtTitle = dialog.findViewById(R.id.title);
        TextView txtContent = dialog.findViewById(R.id.content);

        txtTitle.setText(title);
        txtContent.setText(mess);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
