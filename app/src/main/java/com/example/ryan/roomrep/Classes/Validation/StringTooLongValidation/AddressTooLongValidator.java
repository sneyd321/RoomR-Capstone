package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class AddressTooLongValidator extends StringTooLongValidator {

    private final int MAX_LENGTH = 50;

    public AddressTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.address_too_long_error);
    }

}
