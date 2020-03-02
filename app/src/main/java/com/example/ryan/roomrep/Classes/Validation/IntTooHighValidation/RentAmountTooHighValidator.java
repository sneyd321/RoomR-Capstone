package com.example.ryan.roomrep.Classes.Validation.IntTooHighValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class RentAmountTooHighValidator extends IntTooHighValidator {

    private static int MAX_VALUE = 2000;

    public RentAmountTooHighValidator(Integer integer) {
        super(integer);
        setThreshold(MAX_VALUE);
        setResourceId(R.string.rent_amount_too_long_error);
    }


}
