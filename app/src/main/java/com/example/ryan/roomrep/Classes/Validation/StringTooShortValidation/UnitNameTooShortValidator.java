package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class UnitNameTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 1;

    public UnitNameTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.unit_name_too_short_error);
    }


}
