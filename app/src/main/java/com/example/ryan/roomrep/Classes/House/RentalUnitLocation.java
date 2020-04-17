package com.example.ryan.roomrep.Classes.House;


public class RentalUnitLocation {


    private int streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;
    private String unitName;
    private boolean isCondo;
    private int parkingSpaces;


    public RentalUnitLocation(String address, String city, String province, String postalCode, String unitName, boolean isCondo, int parkingSpaces) {
        String[] street = address.split(" ", 2);
        this.streetNumber = Integer.parseInt(street[0]);
        this.streetName = street[1];
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.unitName = unitName;
        this.isCondo = isCondo;
        this.parkingSpaces = parkingSpaces;
    }

    public RentalUnitLocation(int streetNumber, String streetName, String city, String province, String postalCode, String unitName, boolean isCondo, int parkingSpaces) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.unitName = unitName;
        this.isCondo = isCondo;
        this.parkingSpaces = parkingSpaces;
    }

    public String getFormattedPrimaryAddress() {
        return getStreetNumber() + " " + getStreetName();
    }

    public String getFormattedSecondaryAddress() {
        return getCity() + ", " + getProvince() + " " + getPostalCode();
    }

    public boolean isCondo() {
        return isCondo;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setCondo(boolean condo) {
        isCondo = condo;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
