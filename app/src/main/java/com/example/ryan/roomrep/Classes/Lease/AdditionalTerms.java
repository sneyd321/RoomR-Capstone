package com.example.ryan.roomrep.Classes.Lease;

import android.os.Parcel;
import android.os.Parcelable;

public class AdditionalTerms {

    private boolean receiveNotice;
    private boolean dayToDayCommunication;

    public AdditionalTerms(boolean receiveNotice, boolean dayToDayCommunication) {
        this.receiveNotice = receiveNotice;
        this.dayToDayCommunication = dayToDayCommunication;
    }

    public boolean isReceiveNotice() {
        return receiveNotice;
    }

    public boolean isDayToDayCommunication() {
        return dayToDayCommunication;
    }


}
