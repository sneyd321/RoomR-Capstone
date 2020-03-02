package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class RentAmountTooShortValidator extends StringTooShortValidator {
    
    private final int MIN_LENGTH = 1;
    
    public RentAmountTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.rent_amount_too_short_error);
    }


}
