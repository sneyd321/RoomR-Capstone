package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class CityTooLongValidator extends StringTooLongValidator {


    private final int MAX_LENGTH = 20;

    public CityTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.city_too_long_error);
    }

}
