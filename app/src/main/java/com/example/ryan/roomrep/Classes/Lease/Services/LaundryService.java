package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;

public class LaundryService extends Service {
    public LaundryService(int amount) {
        super(amount);
        this.name = "Laundry";
    }

    public LaundryService(Parcel in) {
        super(in);
    }

    public static final Creator<LaundryService> CREATOR = new Creator<LaundryService>() {
        @Override
        public LaundryService createFromParcel(Parcel in){
            return new LaundryService(in);
        }

        @Override
        public LaundryService[] newArray(int size) {
            return new LaundryService[size];
        }
    };
}
