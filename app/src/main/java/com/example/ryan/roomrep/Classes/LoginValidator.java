package com.example.ryan.roomrep.Classes;

import android.util.Log;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginValidator implements Validator {
    @Override
    public <T> Map<Integer, String> validator(T object) {
        Login login = (Login) object;
        Map<Integer, String> validator = new LinkedHashMap<>();
        validator.put(0, isEmailEmpty(login.getUserName()));
        validator.put(1, isPasswordEmpty(login.getPassword()));
        validator.put(2, isPasswordLogEnough(login.getPassword()));
        return validator;
    }

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


}
