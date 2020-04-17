package com.example.ryan.roomrep.Classes.House;

public class AdditionalTerms {

    private boolean receiveNotice;
    private boolean dayToDayCommunication;
    private String startDate;
    private String endDate;


    public AdditionalTerms(boolean receiveNotice, boolean dayToDayCommunication) {
        this.receiveNotice = receiveNotice;
        this.dayToDayCommunication = dayToDayCommunication;
        this.startDate = "";
        this.endDate = "";
    }

    public boolean isReceiveNotice() {
        return receiveNotice;
    }

    public boolean isDayToDayCommunication() {
        return dayToDayCommunication;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
