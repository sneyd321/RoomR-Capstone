package com.example.ryan.roomrep.Classes;

public class Utility {

    private String name;
    private double amount;
    private String frequency;


    public Utility(String name, double amount, String frequency) {
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        String formattedAmount = String.format("%.2f", this.amount);
        formattedAmount = "$" + formattedAmount;
        return formattedAmount;
    }

    public String getFrequency() {
        return frequency;
    }
}
