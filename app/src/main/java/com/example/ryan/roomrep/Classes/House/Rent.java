package com.example.ryan.roomrep.Classes.House;

import com.example.ryan.roomrep.Classes.House.Amenities.Amenity;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rent_table")
public class Rent {

    @PrimaryKey(autoGenerate = true)
    private int rentId;

    private int baseRent;

    @Ignore
    private List<Amenity> amenities;

    private List<Utility> utilities;

    public Rent(int baseRent, List<Utility> utilities) {
        this.baseRent = baseRent;
        this.utilities = utilities;
    }

    @Ignore
    public Rent() {
        this.baseRent = 0;
        this.amenities = new ArrayList<>();
        this.utilities = new ArrayList<>();
    }


    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getBaseRent() {
        return baseRent;
    }

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
    }



    public void addUtilities(Utility utility) {
        this.utilities.add(utility);
    }

    public List<Utility> getUtilities() {
        return this.utilities;
    }

    public void addAmenity(Amenity amenity) {
        this.amenities.add(amenity);
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }
    private int calculateAmenityTotal() {
        int total = 0;
        for (Amenity amenity : this.amenities) {
            total += amenity.getAmount();
        }
        return total;
    }

    private int calculateUtilityTotal() {
        int total = 0;
        for (Utility utility : this.utilities) {
            total += utility.getAmount();
        }
        return total;
    }

    public int calculateTotalRent() {
        return baseRent + calculateAmenityTotal() + calculateUtilityTotal();
    }


    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}
