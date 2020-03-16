package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;

public class TenantInsuranceService extends Service {
    public TenantInsuranceService(int amount) {
        super(amount);
        this.name = "Tenant Insurance";
    }
    public TenantInsuranceService(Parcel in) {
        super(in);
    }

    public static final Creator<TenantInsuranceService> CREATOR = new Creator<TenantInsuranceService>() {
        @Override
        public TenantInsuranceService createFromParcel(Parcel in){
            return new TenantInsuranceService(in);
        }

        @Override
        public TenantInsuranceService[] newArray(int size) {
            return new TenantInsuranceService[size];
        }
    };

}
