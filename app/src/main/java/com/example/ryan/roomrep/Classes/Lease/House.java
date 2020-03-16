package com.example.ryan.roomrep.Classes.Lease;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "house_table")
public class House {

    @PrimaryKey(autoGenerate = true)
    private int houseId;

    private boolean isPosted;

    @Embedded
    private RentalUnitLocation rentalUnitLocation;

    @Ignore
    private Lease lease;

    public House(Lease lease) {
        this.isPosted = false;
        this.lease = lease;
        this.rentalUnitLocation = lease.getRentalUnitLocation();
    }

    public House(boolean isPosted, RentalUnitLocation rentalUnitLocation) {
        this.isPosted = isPosted;
        this.rentalUnitLocation = rentalUnitLocation;
        this.lease = null;
    }

    public Lease getLease() {
        return lease;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public RentalUnitLocation getRentalUnitLocation() {
        return rentalUnitLocation;
    }

    public void setRentalUnitLocation(RentalUnitLocation rentalUnitLocation) {
        this.rentalUnitLocation = rentalUnitLocation;
    }
}
