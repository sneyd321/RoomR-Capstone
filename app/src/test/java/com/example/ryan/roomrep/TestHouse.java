package com.example.ryan.roomrep;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.HouseBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestHouse {


    private Map<String, Boolean> amenities;
    House house;
    HouseBuilder builder;

    @Before
    public void initTest() {
        amenities = new LinkedHashMap<>();
        amenities.put("Pets", false);
        amenities.put("Smoking", false);
        amenities.put("Public Transit", false);
        amenities.put("Laundry", false);
        amenities.put("Snow Removal", false);
        amenities.put("Air Conditioning", false);
        house = new House("", 0, 0, 0, 0, amenities, null);
        builder = new HouseBuilder();
    }


    @Test
    public void SingleAmenityDescriptionTest() {
        amenities.replace("Pets", true);
        builder.addAmenities(house);
        String description = house.getAmenityDescription();
        Assert.assertEquals("Description is not valid on single entry added.", description, "This house offers one amenity, Pets.");
    }

    @Test
    public void TwoAmenityDescriptionTest() {
        amenities.replace("Pets", true);
        amenities.replace("Smoking", true);
        builder.addAmenities(house);
        String description = house.getAmenityDescription();
        Assert.assertEquals("Description is not valid on two entries added.", description, "This house offers amenities such as: Pets and Smoking.");
    }

    @Test
    public void FourAmenityDescriptionTest() {
        amenities.replace("Pets", true);
        amenities.replace("Smoking", true);
        amenities.replace("Public Transit", true);
        amenities.replace("Laundry", true);
        builder.addAmenities(house);
        String description = house.getAmenityDescription();
        Assert.assertEquals("Description is not valid on multiple entries added.", description, "This house offers amenities such as: Pets, Smoking, Public Transit and Washer and Dryer.");
    }

    @Test
    public void NoAmenityDescriptionTest() {
        builder.addAmenities(house);
        String description = house.getAmenityDescription();
        Assert.assertEquals("Description is not valid on no entries added.", description, "This house does not offer any amenities.");
    }




}
