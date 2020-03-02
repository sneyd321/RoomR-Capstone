package com.example.ryan.roomrep.Classes.Lease;

import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;

public class RentalUnitLocation extends Location {

    private boolean isCondo;
    private int parkingSpaces;
    private String unitName;


    public RentalUnitLocation(String address, String city, String province, String postalCode, String unitName, boolean isCondo, int parkingSpaces) {
        super(address, city, province, postalCode);
        this.isCondo = isCondo;
        this.parkingSpaces = parkingSpaces;
        this.unitName = unitName;
    }


    public boolean isCondo() {
        return isCondo;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }


}
