package com.example.ryan.roomrep.Classes.Homeowner;

import com.example.ryan.roomrep.Classes.Lease.HomeownerLocation;

public class Homeowner {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private HomeownerLocation homeownerLocation;


    public Homeowner(String firstName, String lastName, String email, String phoneNumber, String password, HomeownerLocation homeownerLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.homeownerLocation = homeownerLocation;
    }

    public Homeowner(String firstName, String lastName, String email, String phoneNumber, HomeownerLocation homeownerLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeownerLocation = homeownerLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HomeownerLocation getHomeownerLocation() {
        return homeownerLocation;
    }

    public void setHomeownerLocation(HomeownerLocation homeownerLocation) {
        this.homeownerLocation = homeownerLocation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
