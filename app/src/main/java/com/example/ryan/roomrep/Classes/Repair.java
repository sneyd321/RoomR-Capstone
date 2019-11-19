package com.example.ryan.roomrep.Classes;


public class Repair {
    private String description;
    private String name;
    private String date;
    private String status;
    private String photoRef;
    private String dateUpdated;
    private String houseAddress;

    public Repair(String description, String name, String date, String status, String photoRef, String dateUpdated, String houseAddress){
        this.description = description;
        this.name = name;
        this.date = date;
        this.status = status;
        this.photoRef = photoRef;
        this.dateUpdated = dateUpdated;
        this.houseAddress = houseAddress;

    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }


}
