package com.example.ryan.roomrep.Classes.Validation;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;
import com.example.ryan.roomrep.R;

public class StringTooLongValidator extends Validator<String> {

    @Override
    public boolean validate(String s) {
        return s.length() <= threshold;
    }
}
