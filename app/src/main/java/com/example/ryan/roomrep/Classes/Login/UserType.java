package com.example.ryan.roomrep.Classes.Login;

public enum UserType {

    Landlord("Landlord"),
    Tenant("Tenant");


    private String name;

    UserType(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
