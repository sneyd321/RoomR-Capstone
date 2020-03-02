package com.example.ryan.roomrep.Classes.Lease;

public class House {
    private boolean isPosted;
    private Lease lease;

    public House(Lease lease) {
        this.isPosted = false;
        this.lease = lease;
    }

    public Lease getLease() {
        return lease;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }
}
