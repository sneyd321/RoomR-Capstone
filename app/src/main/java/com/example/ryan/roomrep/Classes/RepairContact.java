package com.example.ryan.roomrep.Classes;

public class RepairContact {
    private String repairCompanyName;
    private String repairCompanyAddress;
    private String phoneNumber;
    private String operationHours;
    private String rating;

    public RepairContact(String repairCompanyName, String repairCompanyAddress, String phoneNumber, String operationHours, String rating){
        this.repairCompanyName = repairCompanyName;
        this.repairCompanyAddress = repairCompanyAddress;
        this.phoneNumber = phoneNumber;
        this.operationHours = operationHours;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRepairCompanyName() {
        return repairCompanyName;
    }

    public void setRepairCompanyName(String repairCompanyName) {
        this.repairCompanyName = repairCompanyName;
    }

    public String getRepairCompanyAddress() {
        return repairCompanyAddress;
    }

    public void setRepairCompanyAddress(String repairCompanyAddress) {
        this.repairCompanyAddress = repairCompanyAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOperationHours() {
        return operationHours;
    }

    public void setOperationHours(String operationHours) {
        this.operationHours = operationHours;
    }
}
