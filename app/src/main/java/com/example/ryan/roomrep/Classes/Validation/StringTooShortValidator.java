package com.example.ryan.roomrep.Classes.Validation;

public class StringTooShortValidator extends Validator<String> {

    @Override
    public boolean validate(String s) {
        return s.length() >= threshold;
    }
}
