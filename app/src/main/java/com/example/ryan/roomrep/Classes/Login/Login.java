package com.example.ryan.roomrep.Classes.Login;

public class Login {
    private String email;
    private String password;
    private UserType userType;

    public Login(String email, String password, UserType userType){
        this.email = email;
        this.password = password;
        this.userType = userType;
    }


    public String getUserType() {
        return this.userType.getName();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
