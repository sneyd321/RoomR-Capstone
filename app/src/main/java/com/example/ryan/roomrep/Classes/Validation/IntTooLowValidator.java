package com.example.ryan.roomrep.Classes.Validation;

public  class IntTooLowValidator extends Validator<Integer> {

    @Override
    public boolean validate(Integer integer) {
        return integer >= threshold;
    }
}
