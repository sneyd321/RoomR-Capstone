package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class PostalCodeTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 1;

    public PostalCodeTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.postal_code_too_short_error);
    }


}
