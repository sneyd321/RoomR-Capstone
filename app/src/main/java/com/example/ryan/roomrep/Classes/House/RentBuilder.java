package com.example.ryan.roomrep.Classes.House;

import com.example.ryan.roomrep.Classes.House.Amenities.Amenity;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;

public class RentBuilder {



    private Rent rent;

    public RentBuilder(){
        this.rent = new Rent();
    }

    public Rent addBaseRent(int baseRent) {
        this.rent.setBaseRent(baseRent);
        return rent;
    }

    public Rent addAmenity(Amenity amenity) {
        rent.addAmenity(amenity);
        return rent;
    }

    public Rent addUtiliy(Utility utility) {
        rent.addUtilities(utility);
        return rent;
    }




}
