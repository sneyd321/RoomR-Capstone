package com.example.ryan.roomrep.Classes.Validation.IntIsZeroValidation;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;

public abstract class IntIsZeroValidator extends Validator<Integer> {

    public IntIsZeroValidator(Integer integer) {
        super(integer);
    }

    @Override
    public boolean validate() {
        return !this.value.equals(0);
    }







}
