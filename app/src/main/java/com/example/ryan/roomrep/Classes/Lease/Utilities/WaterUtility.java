package com.example.ryan.roomrep.Classes.Lease.Utilities;

import android.os.Parcel;

public class WaterUtility extends Utility {
    public WaterUtility(String responsibilityOf) {
        super(responsibilityOf);
        this.name = "Water";
    }

    public WaterUtility(Parcel in) {
        super(in);
    }

    public static final Creator<WaterUtility> CREATOR = new Creator<WaterUtility>() {
        @Override
        public WaterUtility createFromParcel(Parcel in){
            return new WaterUtility(in);
        }

        @Override
        public WaterUtility[] newArray(int size) {
            return new WaterUtility[size];
        }
    };
}
