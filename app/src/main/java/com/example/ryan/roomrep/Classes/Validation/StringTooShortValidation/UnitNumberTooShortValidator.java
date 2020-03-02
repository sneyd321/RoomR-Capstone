package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class UnitNumberTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 1;

    public UnitNumberTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.unit_number_too_short_error);
    }


}
