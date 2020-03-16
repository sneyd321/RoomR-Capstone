package com.example.ryan.roomrep.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.ryan.roomrep.R;

public class ErrorDialog {


    public static AlertDialog buildAlertDialog(Context context, String error) {
        return new AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setTitle("Error")
                .setMessage(error)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }

}
