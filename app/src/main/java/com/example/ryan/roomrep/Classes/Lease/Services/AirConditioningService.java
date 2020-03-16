package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;

public class AirConditioningService extends Service {
    public AirConditioningService(int amount) {
        super(amount);
        this.name = "Air Conditioning";
    }

    public AirConditioningService(Parcel in) {
        super(in);
    }

    public static final Creator<AirConditioningService> CREATOR = new Creator<AirConditioningService>() {
        @Override
        public AirConditioningService createFromParcel(Parcel in){
            return new AirConditioningService(in);
        }

        @Override
        public AirConditioningService[] newArray(int size) {
            return new AirConditioningService[size];
        }
    };
}
