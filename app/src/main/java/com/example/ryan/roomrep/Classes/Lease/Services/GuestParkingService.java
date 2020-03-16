package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;

public class GuestParkingService extends Service {

    public GuestParkingService(int amount) {
        super(amount);
        this.name = "Guest Parking";
    }
    public GuestParkingService(Parcel in) {
        super(in);
    }

    public static final Creator<GuestParkingService> CREATOR = new Creator<GuestParkingService>() {
        @Override
        public GuestParkingService createFromParcel(Parcel in){
            return new GuestParkingService(in);
        }

        @Override
        public GuestParkingService[] newArray(int size) {
            return new GuestParkingService[size];
        }
    };
}
