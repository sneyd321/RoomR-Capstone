package com.example.ryan.roomrep.Classes.Lease;

public class HomeOwnerLocation extends Location{

    private String poBox;
    private String unitNumber;

    public HomeOwnerLocation(String address, String city, String province, String postalCode, String unitNumber, String poBox) {
        super(address, city, province, postalCode, unitNumber);
        this.poBox = poBox;
        this.unitNumber = unitNumber;
    }

    public String getPoBox() {
        return sanitizeString(poBox);
    }

    public String getUnitNumber() {
        return sanitizeString(unitNumber);
    }
}
