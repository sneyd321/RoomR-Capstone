package com.example.ryan.roomrep.Classes.Lease;

public abstract class Location {

    private int streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;


    public Location(String address, String city, String province, String postalCode) {
        this.streetNumber = Integer.parseInt(splitAddress(address)[0]);
        this.streetName = splitAddress(address)[1];
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public Location(int streetNumber, String streetName, String city, String province, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    private String[] splitAddress(String address) {
        return address.split(" ", 2);
    }


    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }



    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
