package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;

public class SmokingService extends Service {
    public SmokingService(int amount) {
        super(amount);
        this.name = "Smoking";
    }
    public SmokingService(Parcel in) {
        super(in);
    }

    public static final Creator<SmokingService> CREATOR = new Creator<SmokingService>() {
        @Override
        public SmokingService createFromParcel(Parcel in){
            return new SmokingService(in);
        }

        @Override
        public SmokingService[] newArray(int size) {
            return new SmokingService[size];
        }
    };

}
