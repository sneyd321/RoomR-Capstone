package com.example.ryan.roomrep.Classes.Profile;

import com.example.ryan.roomrep.Classes.Tenant.Validator;
import com.google.gson.Gson;

import java.util.Map;

public class Profile {

    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String houseAddress;

    public Profile(String firstName, String lastName, String email, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
    }

    public Map<Integer, String> getValidator() {
        Validator validator = new ProfileValidator();
        return validator.validate(this);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String convertToJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}