package com.example.ryan.roomrep.Classes.Landlord;

import java.util.LinkedHashMap;
import java.util.Map;

public class LandlordValidatorClass implements LandlordValidator {



    @Override
    public Map<Integer, String> validateLandlord(Landlord landlord) {
        Map <Integer,String> map = new LinkedHashMap<>();
        map.put(0, isFirstNameEmpty(landlord.getFirstName()));
        //map.put(1, isFirstNameValid(landlord.getFirstName()));
        map.put(2, isLastNameEmpty(landlord.getLastName()));
        //map.put(3, isLastNameValid(landlord.getLastName()));
        map.put(4, isPasswordEmpty(landlord.getPassword()));
        map.put(5, isPasswordLongEnough(landlord.getPassword()));
        map.put(6, isPassword2Empty(landlord.getPassword2()));
        map.put(7, doPasswordsMatch(landlord.getPassword(), landlord.getPassword2()));
        map.put(8, isEmailEmpty(landlord.getEmail()));
        //map.put(9, isEmailValid(landlord.getEmail()));


        return map;
    }



    private String isFirstNameEmpty(String firstName) {
        if (firstName.isEmpty()){
            return "Please enter your first name.";
        }
        return "";
    }

    private String isFirstNameValid(String firstName) {
        if (firstName.matches("^[a-z ,.'-]+$")){
            return "";
        }
        return "Please enter a valid first name.";
    }

    private String isLastNameEmpty(String lastName) {
        if (lastName.isEmpty()){
            return "Please enter your last name.";
        }
        return "";
    }

    private String isLastNameValid(String lastName) {
        if (lastName.matches("/^[a-z ,.'-]+$/i")){
            return "";
        }
        return "Please enter a valid last name.";
    }

    private String isPasswordEmpty(String password) {
        if (password.isEmpty()){
            return "Please enter a password.";
        }
        return "";
    }

    private String isPasswordLongEnough(String password) {
        if (password.length() < 6){
            return "Please enter a password longer than six characters.";
        }
        return "";
    }

    private String isPassword2Empty(String password) {
        if (password.isEmpty()){
            return "Please re-type your password.";
        }
        return "";
    }

    private String doPasswordsMatch(String password1, String password2) {
        if (password1.equals(password2)){
            return "";
        }
        return "Passwords do not match.";
    }

    private String isEmailEmpty(String email) {
        if (email.isEmpty()){
            return "Please enter an email address.";
        }
        return "";
    }

    private String isEmailValid(String email) {
        if (email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
            return "";
        }
        return "Please enter a valid email.";
    }



}
