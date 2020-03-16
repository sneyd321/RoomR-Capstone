package com.example.ryan.roomrep.Classes;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    private Context context;

    public static final int INTERNET_PERMISSION_REQUEST_CODE = 1;


    public Permission(Context context) {
        this.context = context;
    }


    private AlertDialog buildAlertDialog(String title, String rational, String manifestPermission) {
        return new AlertDialog.Builder(this.context)
                .setTitle(title)
                .setMessage(rational)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context, new String[] {manifestPermission}, INTERNET_PERMISSION_REQUEST_CODE);
                    }
                })
                .setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }


    public boolean doesHaveInternetPermission() {
        return ContextCompat.checkSelfPermission(this.context, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.INTERNET)) {
            buildAlertDialog("Internet permission", "Internet is required to access user resources.", Manifest.permission.INTERNET).show();
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.INTERNET}, INTERNET_PERMISSION_REQUEST_CODE);
        }
    }

}
