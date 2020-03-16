package com.example.ryan.roomrep.Classes.Lease;

import android.os.Parcel;
import android.os.Parcelable;

public class RentDetail {

    private String rentDueDate;
    private int baseRent;
    private String rentMadePayable;
    private boolean paymentMethod1;
    private boolean paymentMethod2;
    private boolean paymentMethod3;

    public RentDetail(String rentDueDate, int baseRent, String rentMadePayable, boolean paymentOption1, boolean paymentMethod2, boolean paymentOption3) {
        this.rentDueDate = rentDueDate;
        this.baseRent = baseRent;
        this.rentMadePayable = rentMadePayable;
        this.paymentMethod1 = paymentOption1;
        this.paymentMethod2 = paymentMethod2;
        this.paymentMethod3 = paymentOption3;

    }


    public String getRentDueDate() {
        return rentDueDate;
    }

    public int getBaseRent() {
        return baseRent;
    }

    public String getRentMadePayable() {
        return rentMadePayable;
    }


    public boolean isPaymentMethod1() {
        return paymentMethod1;
    }

    public boolean isPaymentMethod2() {
        return paymentMethod2;
    }

    public boolean isPaymentMethod3() {
        return paymentMethod3;
    }


}
