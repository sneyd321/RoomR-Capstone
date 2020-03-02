package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.R;

public class PoBoxTooShortValidator extends StringTooShortValidator {

    private final int MIN_LENGTH = 1;

    public PoBoxTooShortValidator(String s) {
        super(s);
        this.setThreshold(MIN_LENGTH);
        setResourceId(R.string.po_box_too_short_error);
    }


}
