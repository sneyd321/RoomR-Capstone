package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class UnitNameTooLongValidator extends StringTooLongValidator {

    private final int MAX_LENGTH = 50;

    public UnitNameTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.unit_name_too_long_error);
    }


}
