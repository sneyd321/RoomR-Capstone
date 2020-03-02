package com.example.ryan.roomrep.Classes;

import android.util.Log;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginValidator implements Validator<Login> {


    private String isEmailEmpty(String email) {
        if(email.isEmpty()) {
            return "Please enter an email.";
        }
        return "";
    }

    private String isPasswordEmpty(String password) {
        if (password.isEmpty()) {
            return "Please enter a password.";
        }
        return "";
    }

    private String isPasswordLogEnough(String password) {
        if (password.length() < 6) {
            return "Please enter a password longer than six characters.";
        }
        return "";
    }


    @Override
    public Map<Integer, String> validate(Login object) {
        Map<Integer, String> validator = new LinkedHashMap<>();
        validator.put(0, isEmailEmpty(object.getUserName()));
        validator.put(1, isPasswordEmpty(object.getPassword()));
        validator.put(2, isPasswordLogEnough(object.getPassword()));
        return validator;
    }
}
