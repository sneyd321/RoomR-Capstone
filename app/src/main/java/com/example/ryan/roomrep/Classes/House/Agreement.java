package com.example.ryan.roomrep.Classes.House;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "agreement_table")
public class Agreement {

    private String startDate;
    private String endDate;


    public Agreement(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
