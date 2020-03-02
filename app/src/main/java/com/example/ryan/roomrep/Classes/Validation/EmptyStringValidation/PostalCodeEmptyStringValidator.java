package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;
import com.example.ryan.roomrep.R;

public class PostalCodeEmptyStringValidator extends EmptyStringValidator {
    public PostalCodeEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.po_box_too_short_error);
    }


}
