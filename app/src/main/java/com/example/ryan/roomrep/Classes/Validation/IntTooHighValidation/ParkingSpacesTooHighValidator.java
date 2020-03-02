package com.example.ryan.roomrep.Classes.Validation.IntTooHighValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class ParkingSpacesTooHighValidator extends IntTooHighValidator {

    private static int MAX_VALUE = 10;

    public ParkingSpacesTooHighValidator(Integer integer) {
        super(integer);
        setThreshold(MAX_VALUE);
        setResourceId(R.string.parking_space_too_long_error);
    }


}
