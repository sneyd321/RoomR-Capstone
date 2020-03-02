package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class UnitNumberTooLongValidator extends StringTooLongValidator {

    private final int MAX_LENGTH = 8;

    public UnitNumberTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.unit_number_too_long_error);
    }


}
