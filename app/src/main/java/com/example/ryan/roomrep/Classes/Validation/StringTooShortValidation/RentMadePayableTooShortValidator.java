package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class RentMadePayableTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 1;

    public RentMadePayableTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.rent_made_payable_too_short_error);
    }


}
