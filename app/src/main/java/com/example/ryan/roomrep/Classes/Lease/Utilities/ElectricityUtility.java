package com.example.ryan.roomrep.Classes.Lease.Utilities;

import android.os.Parcel;

import com.example.ryan.roomrep.Classes.Lease.Services.TenantInsuranceService;

public class ElectricityUtility extends Utility {
    public ElectricityUtility(String responsibilityOf) {
        super(responsibilityOf);
        this.name = "Electricity";
    }
    public ElectricityUtility(Parcel in) {
        super(in);
    }

    public static final Creator<ElectricityUtility> CREATOR = new Creator<ElectricityUtility>() {
        @Override
        public ElectricityUtility createFromParcel(Parcel in){
            return new ElectricityUtility(in);
        }

        @Override
        public ElectricityUtility[] newArray(int size) {
            return new ElectricityUtility[size];
        }
    };
}
