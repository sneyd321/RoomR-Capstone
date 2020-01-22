package com.example.ryan.roomrep;

import com.example.ryan.roomrep.Classes.House.House;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestHouse  {

    /*
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
        house = new House("", 0, 0, 0, 0, amenities, null, "");
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

    @Test
    public void EmptyAddressTest() {
        house = new House("", 1234, 1234, 0, 0, null, null, "");
        Map<Integer,String> validate = house.getValidator();
        String expected = validate.get(0);

        Assert.assertEquals("Address empty validation failed", expected, "Please enter an address.");

    }

    @Ignore
    public void ValidAddressTest() {
        house = new House("123 AddressName", 1234, 1234, 0, 0, null, null, "");
        Map<Integer,String> validate = house.getValidator();
        String expected = validate.get(1);
        Assert.assertEquals("Address regex validation failed", expected, "");
    }

    @Ignore
    public void InvalidAddressTest() {
        house = new House("vfadfdasfdasfads", 1234, 1234, 0, 0, null, null, "");
        Map<Integer,String> validate = house.getValidator();
        String expected = validate.get(1);
        Assert.assertEquals("Address regex validation failed", expected, "Please enter a valid address in the format '123 AddressName'.");
    }


    @Test
    public void RentIsZeroTest() {
        house = new House("123 AddressName", 0, 1234, 0, 0, null, null, "");
        Map<Integer,String> validate = house.getValidator();
        String expected = validate.get(2);
        Assert.assertEquals("Payment validation failed", expected, "Please enter a rent greater than 0.");
    }

    @Test
    public void SizeIsZeroTest() {
        house = new House("123 AddressName", 1324, 0, 0, 0, null, null, "");
        Map<Integer,String> validate = house.getValidator();
        String expected = validate.get(3);
        Assert.assertEquals("Size validation failed", expected, "Please enter a size greater than 0.");
    }
    */



}
