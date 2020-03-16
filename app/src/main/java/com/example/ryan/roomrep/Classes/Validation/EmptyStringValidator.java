package com.example.ryan.roomrep.Classes.Validation;

public class EmptyStringValidator extends Validator<String> {

    @Override
    public boolean validate(String s) {
        return !s.isEmpty();
    }
}
