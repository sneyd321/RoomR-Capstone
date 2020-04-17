package com.example.ryan.roomrep.Classes.House;

public class HomeownerLocation {



    private int homeownerStreetNumber;
    private String homeownerStreetName;
    private String homeownerCity;
    private String homeownerProvince;
    private String homeownerPostalCode;
    private String poBox;
    private String unitNumber;

    public HomeownerLocation() {

    }

    public HomeownerLocation(String address, String city, String province, String postalCode, String unitNumber, String poBox) {
        String[] street = address.split(" ", 2);
        this.homeownerStreetNumber = Integer.parseInt(street[0]);
        this.homeownerStreetName = street[1];
        this.homeownerCity = city;
        this.homeownerProvince = province;
        this.homeownerPostalCode = postalCode;
        this.poBox = poBox;
        this.unitNumber = unitNumber;
    }


    public HomeownerLocation(int streetNumber, String streetName, String city, String province, String postalCode, String unitNumber, String poBox) {
        this.homeownerStreetNumber = streetNumber;
        this.homeownerStreetName = streetName;
        this.homeownerCity = city;
        this.homeownerProvince = province;
        this.homeownerPostalCode = postalCode;
        this.poBox = poBox;
        this.unitNumber = unitNumber;
    }


    public String getPoBox() {
        return poBox;
    }

    public String getUnitNumber() {
        return unitNumber;
    }


    public int getHomeownerStreetNumber() {
        return homeownerStreetNumber;
    }

    public void setHomeownerStreetNumber(int homeownerStreetNumber) {
        this.homeownerStreetNumber = homeownerStreetNumber;
    }

    public String getHomeownerStreetName() {
        return homeownerStreetName;
    }

    public void setHomeownerStreetName(String homeownerStreetName) {
        this.homeownerStreetName = homeownerStreetName;
    }

    public String getHomeownerCity() {
        return homeownerCity;
    }

    public void setHomeownerCity(String homeownerCity) {
        this.homeownerCity = homeownerCity;
    }

    public String getHomeownerProvince() {
        return homeownerProvince;
    }

    public void setHomeownerProvince(String homeownerProvince) {
        this.homeownerProvince = homeownerProvince;
    }

    public String getHomeownerPostalCode() {
        return homeownerPostalCode;
    }

    public void setHomeownerPostalCode(String homeownerPostalCode) {
        this.homeownerPostalCode = homeownerPostalCode;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}
