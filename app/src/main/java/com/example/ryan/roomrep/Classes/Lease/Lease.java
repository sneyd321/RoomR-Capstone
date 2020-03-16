package com.example.ryan.roomrep.Classes.Lease;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public abstract class Lease {

    private RentalUnitLocation rentalUnitLocation;
    private HomeownerLocation homeownerLocation;
    private RentDetail rentDetail;
    private List<Service> services;
    private List<Utility> utilities;
    private AdditionalTerms additionalTerms;

    public Lease() {
        services = new ArrayList<>();
        utilities = new ArrayList<>();
    }



    protected void setRentalUnitLocation(RentalUnitLocation location) {
        this.rentalUnitLocation = location;
    }


    protected void setHomeownerLocation(HomeownerLocation homeownerLocation) {
        this.homeownerLocation = homeownerLocation;
    }


    protected void setRentDetail(RentDetail rentDetail) {
        this.rentDetail = rentDetail;
    }


    protected void addService(Service service) {
        this.services.add(service);
    }


    protected void addUtility(Utility utility) {
        this.utilities.add(utility);
    }


    protected void setAdditionalTerms(AdditionalTerms additionalTerms) {
        this.additionalTerms = additionalTerms;
    }



    public RentalUnitLocation getRentalUnitLocation() {
        return rentalUnitLocation;
    }

    public List<Service> getServices() {
        return services;
    }
}
