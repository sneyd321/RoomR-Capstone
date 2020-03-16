package com.example.ryan.roomrep.Classes.Lease.Utilities;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Utility implements Parcelable {

    private String repsonsibiltyOf;
    protected String name;

    public Utility(String responsibilityOf) {
        this.repsonsibiltyOf = responsibilityOf;
    }

    public String getRepsonsibiltyOf() {
        return repsonsibiltyOf;
    }

    public String getName() {
        return name;
    }

    protected Utility(Parcel in) {
        repsonsibiltyOf = in.readString();
        name = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(repsonsibiltyOf);
        parcel.writeString(name);

    }
}
