package com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class RentAmountTooLowValidator extends IntTooLowValidator{

    private final int MIN_VALUE = 1;

    public RentAmountTooLowValidator(Integer integer) {
        super(integer);
        setThreshold(MIN_VALUE);
        setResourceId(R.string.rent_amount_too_short_error);
    }


}
