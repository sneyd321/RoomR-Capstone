package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ryan.roomrep.Classes.Lease.RentDetail;

public class ParkingService extends Service{


    public ParkingService(int amount) {
        super(amount);
        this.name = "Parking";
    }

    public ParkingService(Parcel in) {
        super(in);
    }

    public static final Creator<ParkingService> CREATOR = new Creator<ParkingService>() {
        @Override
        public ParkingService createFromParcel(Parcel in){
            return new ParkingService(in);
        }

        @Override
        public ParkingService[] newArray(int size) {
            return new ParkingService[size];
        }
    };


}
