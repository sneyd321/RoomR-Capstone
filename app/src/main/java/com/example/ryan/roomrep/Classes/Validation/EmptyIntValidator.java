package com.example.ryan.roomrep.Classes.Validation;

public class EmptyIntValidator extends Validator<Integer> {
    @Override
    public boolean validate(Integer integer) {
        try {
            String s = Integer.toString(integer);
            return !s.isEmpty();
        }
        catch (NumberFormatException ex) {
            return false;
        }

    }
}
