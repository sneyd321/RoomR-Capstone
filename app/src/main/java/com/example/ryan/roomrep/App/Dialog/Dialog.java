package com.example.ryan.roomrep.App.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.ryan.roomrep.R;

public class Dialog {


    private Context context;

    private AlertDialog.Builder dialog;

    public Dialog(Context context) {
        this.context = context;
        this.dialog = new AlertDialog.Builder(this.context, R.style.AlertDialogTheme);
    }

    public AlertDialog buildErrorDialog() {
        return this.dialog.setTitle("Error").setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }


    public void setMessage(String message) {
        this.dialog.setMessage(message);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.dialog.setOnDismissListener(onDismissListener);
    }


}
