package com.example.ryan.roomrep.Classes.Validation.IntIsZeroValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class RentAmountIsZeroValidator extends IntIsZeroValidator {
    public RentAmountIsZeroValidator(Integer integer) {
        super(integer);
        this.setResourceId(R.string.rent_amount_zero_empty);
    }

    public String getErrorMessage() {
        return App.getContext().getResources().getString(resourceId);
    }
}
