package com.example.ryan.roomrep;

import com.example.ryan.roomrep.Classes.House.Amenities.Amenity;
import com.example.ryan.roomrep.Classes.House.Rent;
import com.example.ryan.roomrep.Classes.House.RentBuilder;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;

import org.junit.Assert;
import org.junit.Test;

public class TestRent {

    @Test
    public void TestRentWithoutAmanitiesOrUtilities(){
        Rent rent = new Rent(100);
        int amount = rent.calculateTotalRent();
        Assert.assertEquals("Error calculating total rent", 100, amount);
    }


    @Test
    public void TestRentWithAmenity() {
        Rent rent = new Rent(100);
        Amenity amenity = new AirConditioningAmenity();
        amenity.setAmount(200);
        rent.addAmenity(amenity);
        int amount = rent.calculateTotalRent();
        Assert.assertEquals("Error calculating total rent and amenities", 300, amount);
    }

    @Test
    public void TestRentBuilderWithAmenity() {
        RentBuilder builder = new RentBuilder(100);

        Amenity amenity1 = new AirConditioningAmenity();
        amenity1.setAmount(200);
        Amenity amenity2 = new AirConditioningAmenity();
        amenity2.setAmount(200);

        builder.addAmenity(amenity1);
        Rent rent = builder.addAmenity(amenity2);

        int amount = rent.calculateTotalRent();
        Assert.assertEquals("Error calculating total rent and amenities", 500, amount);
    }


    @Test
    public void TestRentWithUtility() {
        Rent rent = new Rent(100);
        Utility utility = new HydroUtility();
        utility.setAmount(200);
        rent.addUtilities(utility);
        int amount = rent.calculateTotalRent();
        Assert.assertEquals("Error calculating total rent and utilities", 300, amount);
    }

}
