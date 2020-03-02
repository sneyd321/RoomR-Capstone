package com.example.ryan.roomrep.Classes.Lease;

import com.example.ryan.roomrep.Classes.Rent.Payment;

import java.util.List;

public class RentDetail {

    private String rentDueDate;
    private int baseRent;
    private String rentMadePayable;
    private boolean paymentOption1;
    private boolean paymentOption2;
    private boolean paymentOption3;

    public RentDetail(String rentDueDate, int baseRent, String rentMadePayable, boolean paymentOption1, boolean paymentOption2, boolean paymentOption3) {
        this.rentDueDate = rentDueDate;
        this.baseRent = baseRent;
        this.rentMadePayable = rentMadePayable;
        this.paymentOption1 = paymentOption1;
        this.paymentOption2 = paymentOption2;
        this.paymentOption3 = paymentOption3;

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


    public boolean isPaymentOption1() {
        return paymentOption1;
    }

    public boolean isPaymentOption2() {
        return paymentOption2;
    }

    public boolean isPaymentOption3() {
        return paymentOption3;
    }
}
