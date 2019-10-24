package com.example.ryan.roomrep.Classes.Rent;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {

    private String tenantName;
    private String houseAddress;
    private String dueDate;
    private String landlordEmail;
    private double amount;


    public Payment(String tenantName, String houseAddress, String dueDate, String landlordEmail, double amount) {
        this.tenantName = tenantName;
        this.houseAddress = houseAddress;
        this.dueDate = dueDate;
        this.landlordEmail = landlordEmail;
        this.amount = amount;
    }


    protected Payment(Parcel in) {
        tenantName = in.readString();
        houseAddress = in.readString();
        dueDate = in.readString();
        landlordEmail = in.readString();
        amount = in.readDouble();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    public String getTenantName() {
        return tenantName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getLandlordEmail() {
        return landlordEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tenantName);
        dest.writeString(houseAddress);
        dest.writeString(dueDate);
        dest.writeString(landlordEmail);
        dest.writeDouble(amount);
    }
}
