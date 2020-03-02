package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class RentAmountTooLongValidator extends StringTooLongValidator {
    
    private final int MAX_LENGTH = 4;
    
    public RentAmountTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.rent_amount_too_long_error);
    }


}
