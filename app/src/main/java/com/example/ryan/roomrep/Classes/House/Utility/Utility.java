package com.example.ryan.roomrep.Classes.House.Utility;


import java.text.NumberFormat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Utility {
    private String name;
    private double amount;
    private String frequency;
    private String responsibility;

    public Utility(String name, double amount, String frequency, String responsibility) {
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
        this.responsibility = responsibility;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFormmatedAmount() {
       NumberFormat formatter = NumberFormat.getCurrencyInstance();
       return formatter.format(this.amount);
    }



}
