package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class CityEmptyStringValidator extends EmptyStringValidator {
    public CityEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.empty_city_error);
    }


}
