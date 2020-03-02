package com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class ParkingSpaceTooLowValidator extends IntTooLowValidator {

    private final int MIN_VALUE = 0;

    public ParkingSpaceTooLowValidator(Integer integer) {
        super(integer);
        setThreshold(MIN_VALUE);
        setResourceId(R.string.parking_space_too_short_error);
    }


}
