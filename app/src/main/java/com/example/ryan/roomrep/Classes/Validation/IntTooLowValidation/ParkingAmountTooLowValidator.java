package com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class ParkingAmountTooLowValidator extends IntTooLowValidator {

    private final int MIN_VALUE = -1;

    public ParkingAmountTooLowValidator(Integer integer) {
        super(integer);
        setThreshold(MIN_VALUE);
        setResourceId(R.string.parking_amount_too_short_error);
    }


}
