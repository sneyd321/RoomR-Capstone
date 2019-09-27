package com.example.ryan.roomrep.Classes.Profile;

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
        ProfileValidator validator = new ProfileValidatorClass();
        return validator.validateProfile(this);
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
}