package com.example.ryan.roomrep.Classes.Lease;

import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;

public abstract class Location {



    private ValidationFacade validationFacade;
    private String streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;


    public Location(String address, String city, String province, String postalCode) {
        validationFacade = new ValidationFacade();
        String[] street = getStreetFromAddress(address);
        this.streetNumber = street[0];
        this.streetName = street[1];
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public Location(String streetNumber, String streetName, String city, String province, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    private String[] getStreetFromAddress(String address) {
        return address.split(" ", 2);
    }

    public String getStreetNumber() {
        return sanitizeString(streetNumber);
    }

    public String getStreetName() {
        return sanitizeString(streetName);
    }

    public String getCity() {
        return sanitizeString(city);
    }

    public String getProvince() {
        return sanitizeString(province);
    }

    public String getPostalCode() {
        return sanitizeString(postalCode);
    }

    protected String sanitizeString(String s) {
        s = s.replaceAll("\\s+", "");
        s = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        return s;
    }


}
