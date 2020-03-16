package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;

public class StorageService extends Service {
    public StorageService(int amount) {
        super(amount);
        this.name = "Storage";
    }
    public StorageService(Parcel in) {
        super(in);
    }

    public static final Creator<StorageService> CREATOR = new Creator<StorageService>() {
        @Override
        public StorageService createFromParcel(Parcel in){
            return new StorageService(in);
        }

        @Override
        public StorageService[] newArray(int size) {
            return new StorageService[size];
        }
    };


}
