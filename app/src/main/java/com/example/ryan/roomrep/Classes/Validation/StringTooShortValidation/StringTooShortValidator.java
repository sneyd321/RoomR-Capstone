package com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;
import com.example.ryan.roomrep.R;

public abstract class StringTooShortValidator extends Validator<String> {


    private int threshold;

    public StringTooShortValidator(String s) {
        super(s);
    }

    protected void setThreshold(int threshold){
        this.threshold = threshold;
    }

    @Override
    public boolean validate() {
        return value.length() >= threshold;
    }


    @Override
    public String getErrorMessage() {
        return App.getContext().getResources().getString(resourceId) + " " + threshold + " " + App.getContext().getResources().getString(R.string.too_string_error_postfix);
    }

    public int getThreshold() {
        return threshold;
    }
}
