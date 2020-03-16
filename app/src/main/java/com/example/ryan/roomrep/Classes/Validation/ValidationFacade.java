package com.example.ryan.roomrep.Classes.Validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationFacade {


    private static ValidationFacade instance = null;
    private List<Validator> validatorList;


    public static ValidationFacade getInstance() {
        if (instance == null) {
            return new ValidationFacade();
        }
        return instance;
    }

    private ValidationFacade() {
        validatorList = new ArrayList<>();
    }

    public void addEmptyValidator(String errorMessage) {
        Validator validator = new EmptyStringValidator();
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }

    public void addTooShortValidator(int threshold, String errorMessage) {
        Validator validator = new StringTooShortValidator();
        validator.setThreshold(threshold);
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }

    public void addTooLongValidator(int threshold, String errorMessage) {
        Validator validator = new StringTooLongValidator();
        validator.setThreshold(threshold);
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }

    public void addTooHighValidator(int threshold, String errorMessage) {
        Validator validator = new IntTooHighValidator();
        validator.setThreshold(threshold);
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }
    public void addTooLowValidator(int threshold, String errorMessage) {
        Validator validator = new IntTooLowValidator();
        validator.setThreshold(threshold);
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }

    public void addZeroValidator(String errorMessage) {
        Validator validator = new IntZeroValidator();
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }

    public void addIntEmptyValidator(String errorMessage) {
        Validator validator = new EmptyIntValidator();
        validator.setErrorMessage(errorMessage);
        this.validatorList.add(validator);
    }

    public String validate(String s) {
        for (Validator validator : validatorList) {
            if (!validator.validate(s)) {
                return validator.getErrorMessage();
            }
        }
        return "";
    }

    public String validate(int i) {
        for (Validator validator : validatorList) {
            if (!validator.validate(i)) {
                return validator.getErrorMessage();
            }
        }
        return "";
    }
}
