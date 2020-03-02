package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class UnitNameEmptyStringValidator extends EmptyStringValidator {
    public UnitNameEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.empty_unit_name_error);
    }


}
