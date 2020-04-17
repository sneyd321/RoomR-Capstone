package com.example.ryan.roomrep.Classes.House;

public class RentDetail {

    private String rentDueDate;
    private int baseRent;
    private String rentMadePayable;
    private boolean paymentMethod1;
    private boolean paymentMethod2;
    private boolean paymentMethod3;

    public RentDetail(){

    }

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


    public void setRentDueDate(String rentDueDate) {
        this.rentDueDate = rentDueDate;
    }

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
    }

    public void setRentMadePayable(String rentMadePayable) {
        this.rentMadePayable = rentMadePayable;
    }

    public void setPaymentMethod1(boolean paymentMethod1) {
        this.paymentMethod1 = paymentMethod1;
    }

    public void setPaymentMethod2(boolean paymentMethod2) {
        this.paymentMethod2 = paymentMethod2;
    }

    public void setPaymentMethod3(boolean paymentMethod3) {
        this.paymentMethod3 = paymentMethod3;
    }
}
