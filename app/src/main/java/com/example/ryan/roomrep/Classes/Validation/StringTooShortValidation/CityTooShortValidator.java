package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class CityTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 1;

    public CityTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.city_too_short_error);
    }


}
