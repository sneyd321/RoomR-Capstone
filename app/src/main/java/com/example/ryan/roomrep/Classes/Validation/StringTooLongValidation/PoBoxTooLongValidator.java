package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class PoBoxTooLongValidator extends StringTooLongValidator {

    private final int MAX_LENGTH = 5;

    public PoBoxTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.po_box_too_long_error);
    }


}
