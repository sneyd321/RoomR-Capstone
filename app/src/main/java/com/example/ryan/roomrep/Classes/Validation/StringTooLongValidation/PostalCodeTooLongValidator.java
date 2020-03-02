package com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class PostalCodeTooLongValidator extends StringTooLongValidator{

    private final int MAX_LENGTH = 7;

    public PostalCodeTooLongValidator(String s) {
        super(s);
        setThreshold(MAX_LENGTH);
        setResourceId(R.string.postal_code_too_long_error);
    }


}
