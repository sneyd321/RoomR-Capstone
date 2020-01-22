package com.example.ryan.roomrep.Classes.House.Amenities;


import java.util.ArrayList;
import java.util.List;

public class Amenity {

    private String name;
    private int amount;
    private List<AmenityOption> amenityOptions;


    public Amenity(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.amenityOptions = new ArrayList<>();
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public void addAmenityOption(AmenityOption amenityOption) {
        this.amenityOptions.add(amenityOption);
    }

    public List<AmenityOption> getAmenityOptions() {
        return this.amenityOptions;
    }





}
