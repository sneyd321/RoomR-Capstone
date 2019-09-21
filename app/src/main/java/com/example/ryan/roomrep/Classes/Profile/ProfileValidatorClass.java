package com.example.ryan.roomrep.Classes.Profile;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfileValidatorClass implements ProfileValidator {
    @Override
    public Map<Integer, String> validateProfile(Profile profile) {
        Map<Integer, String> validator = new LinkedHashMap<>();
        validator.put(0, isFirstNameEmpty(profile.getFirstName()));
        validator.put(1, isLastNameEmpty(profile.getLastName()));
        validator.put(2, isEmailEmpty(profile.getEmail()));
        validator.put(3, isBioEmpty(profile.getBio()));
        validator.put(4, isBioTooLong(profile.getBio()));

        return validator;

    }


    private String isFirstNameEmpty(String firstName) {
        if (firstName.isEmpty()){
            return "Please enter a first name.";
        }
        return "";
    }

    private String isLastNameEmpty(String lastName) {
        if (lastName.isEmpty()){
            return "Please enter a last name.";
        }
        return "";
    }

    private String isEmailEmpty(String email) {
        if (email.isEmpty()) {
            return "Please enter an email.";
        }
        return "";
    }

    private String isBioEmpty(String bio) {
        if (bio.isEmpty()){
            return "Please enter a bio. Tell your landlord who you are.";
        }
        return "";
    }

    private String isBioTooLong(String bio) {
        if (bio.length() > 200) {
            return "Bio too long. Please limit to 200 characters";
        }
        return "";
    }

}
