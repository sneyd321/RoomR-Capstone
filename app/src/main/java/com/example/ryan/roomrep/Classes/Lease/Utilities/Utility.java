package com.example.ryan.roomrep.Classes.Lease.Utilities;

public abstract class Utility {

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
}
