package com.example.ryan.roomrep.Classes.Lease.Utilities;

import android.os.Parcel;

public class HeatUtility extends Utility {
    public HeatUtility(String responsibilityOf) {
        super(responsibilityOf);
        this.name = "Heat";
    }

    public HeatUtility(Parcel in) {
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
