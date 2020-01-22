package com.example.ryan.roomrep.Classes;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.Map;

public class Login {
    private String userName;
    private String password;




    public Login(String userName, String password){
        this.setUserName(userName);
        this.setPassword(password);

    }

    public Map<Integer, String> getValidator() {
        Validator validator = new LoginValidator();
        return validator.validate(this);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
