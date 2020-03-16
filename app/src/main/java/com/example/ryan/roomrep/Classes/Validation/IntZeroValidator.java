package com.example.ryan.roomrep.Classes.Validation;

public class IntZeroValidator extends Validator<Integer> {
    @Override
    public boolean validate(Integer integer) {
        return !integer.equals(0);
    }
}
