package com.example.ryan.roomrep.App.Factories;

import android.view.View;

import com.example.ryan.roomrep.App.DatePicker.SelectDateDialog;
import com.example.ryan.roomrep.R;

public class SelectDateFactory extends AbstractFactory {

    public SelectDateFactory(View view) {
        super(view);
    }


    public SelectDateDialog getSelectDateDialog(int resourceId) {
        switch (resourceId) {
            case R.id.edtGenerateLeaseStartDate:
                return new SelectDateDialog(getView(), resourceId);
            case R.id.edtGenerateLeaseEndDate:
                return new SelectDateDialog(getView(), resourceId);
            default:
                return null;
        }
    }

}
