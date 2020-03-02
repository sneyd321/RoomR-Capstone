package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class UnitNumberEmptyStringValidator extends EmptyStringValidator {

    public UnitNumberEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.empty_unit_number_error);
    }

}
