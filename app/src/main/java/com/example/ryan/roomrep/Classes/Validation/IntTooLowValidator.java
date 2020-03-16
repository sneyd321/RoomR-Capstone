package com.example.ryan.roomrep.Classes.Validation;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;
import com.example.ryan.roomrep.R;

public  class IntTooLowValidator extends Validator<Integer> {

    @Override
    public boolean validate(Integer integer) {
        return integer >= threshold;
    }
}
