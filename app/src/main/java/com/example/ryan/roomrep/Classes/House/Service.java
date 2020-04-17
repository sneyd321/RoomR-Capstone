package com.example.ryan.roomrep.Classes.House;

import android.os.Parcel;
import android.os.Parcelable;

public class Service  {

    private String name;
    private int amount;
    private boolean isChecked;


    public Service(String name, int amount, boolean isChecked) {
        this.name = name;
        this.amount = amount;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
