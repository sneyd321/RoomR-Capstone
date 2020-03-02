package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class RentMadePayableTooLongValidator extends StringTooLongValidator {

    private final int MAX_LENGTH = 30;

    public RentMadePayableTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.rent_made_payable_too_long_error);
    }


}
