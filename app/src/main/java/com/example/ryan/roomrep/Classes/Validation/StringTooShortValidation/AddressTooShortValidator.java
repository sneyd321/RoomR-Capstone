package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class AddressTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 3;

    public AddressTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.address_too_short_error);
    }


}
