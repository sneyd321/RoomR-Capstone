package com.example.ryan.roomrep.Classes.House;

import java.util.ArrayList;

public class House extends UtilityComponent {

    private String address;
    private int rent;
    private int size;

    private int bedNumber;
    private int bathNumber;

    private ArrayList<Utility> utilities;


    public House(){
        address = "";
        rent = 0;
        size = 0;
        bedNumber = 0;
        bathNumber = 0;
        utilities = new ArrayList<>();
    }

    public House(String address, int rent, int size, int bedNumber, int bathNumber){
        this.address = address;
        this.rent = rent;
        this.size = size;
        this.bedNumber = bedNumber;
        this.bathNumber = bathNumber;
        utilities = new ArrayList<>();
    }


    public String getAddress() {
        return address;
    }

    public int getRent() {
        return rent;
    }

    public int getSize() {
        return size;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public int getBathNumber() {
        return bathNumber;
    }

    public ArrayList<Utility> getUtilities() {
        return utilities;
    }

    @Override
    public void add(Utility utility) {
        utilities.add(utility);
    }

    @Override
    public void remove(Utility utility) {
        utilities.remove(utility);
    }

    @Override
    public Utility getChild(int position) {
        return utilities.get(position);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public void setBathNumber(int bathNumber) {
        this.bathNumber = bathNumber;
    }
}




