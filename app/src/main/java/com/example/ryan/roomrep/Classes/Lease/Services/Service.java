package com.example.ryan.roomrep.Classes.Lease.Services;

public abstract class Service {

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
}
