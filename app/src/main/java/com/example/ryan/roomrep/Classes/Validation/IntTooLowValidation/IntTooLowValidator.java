package com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;
import com.example.ryan.roomrep.R;

public abstract class IntTooLowValidator extends Validator<Integer> {

    private int threshold;

    public IntTooLowValidator(Integer integer) {
        super(integer);
    }

    @Override
    public boolean validate() {
        return this.value >= threshold;
    }


    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String getErrorMessage() {
        return App.getContext().getResources().getString(resourceId) + " " + threshold + App.getContext().getResources().getString(R.string.too_number_error_postfix);
    }

    public int getThreshold() {
        return threshold;
    }
}
