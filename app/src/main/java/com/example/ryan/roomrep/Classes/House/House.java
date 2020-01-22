package com.example.ryan.roomrep.Classes.House;


import com.example.ryan.roomrep.Classes.Tenant.Validator;


import java.util.Map;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "house_table")
public class House {



    @PrimaryKey(autoGenerate = true)
    private int houseId;
    @Embedded
    private Location location;
    @Embedded
    private Rent rent;
    private int size;
    private int bedNumber;
    private int bathNumber;
    private boolean isPosted;
    private String unitType;
    @Embedded
    private Agreement agreement;

    public House(Location location, Rent rent, int size, int bedNumber, int bathNumber, boolean isPosted, String unitType, Agreement agreement) {
        this.location = location;
        this.rent = rent;
        this.size = size;
        this.bedNumber = bedNumber;
        this.bathNumber = bathNumber;
        this.isPosted = isPosted;
        this.unitType = unitType;
        this.agreement = agreement;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int id) {
        this.houseId = id;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getBathNumber() {
        return bathNumber;
    }

    public void setBathNumber(int bathNumber) {
        this.bathNumber = bathNumber;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public Map<Integer, String> validateHouse() {
        Validator validator = new HouseValidator();
        return validator.validate(this);
    }





}




