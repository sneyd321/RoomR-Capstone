package com.example.ryan.roomrep.Classes.Lease.Services;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ryan.roomrep.Classes.Lease.RentDetail;

public abstract class Service implements Parcelable {

    protected String name;
    private int amount;


    public Service(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    protected Service(Parcel in) {
        name = in.readString();
        amount = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(amount);

    }



}
