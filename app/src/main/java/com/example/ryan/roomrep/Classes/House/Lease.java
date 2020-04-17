package com.example.ryan.roomrep.Classes.House;


import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;

public abstract class Lease {

    @Embedded
    private RentalUnitLocation rentalUnitLocation;
    @Embedded
    private HomeownerLocation homeownerLocation;
    @Embedded
    private RentDetail rentDetail;
    private List<Service> services;
    private List<Utility> utilities;
    @Embedded
    private AdditionalTerms additionalTerms;

    public Lease() {
        services = new ArrayList<>();
        utilities = new ArrayList<>();
    }


    public void setRentalUnitLocation(RentalUnitLocation location) {
        this.rentalUnitLocation = location;
    }


    public void setHomeownerLocation(HomeownerLocation homeownerLocation) {
        this.homeownerLocation = homeownerLocation;
    }


    public void setRentDetail(RentDetail rentDetail) {
        this.rentDetail = rentDetail;
    }


    public void addService(Service service) {
        this.services.add(service);
    }


    public void addUtility(Utility utility) {
        this.utilities.add(utility);
    }


    public void setAdditionalTerms(AdditionalTerms additionalTerms) {
        this.additionalTerms = additionalTerms;
    }


    public RentalUnitLocation getRentalUnitLocation() {
        return rentalUnitLocation;
    }

    public List<Service> getServices() {
        return services;
    }

    public HomeownerLocation getHomeownerLocation() {
        return homeownerLocation;
    }

    public RentDetail getRentDetail() {
        return rentDetail;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Utility> getUtilities() {
        return utilities;
    }

    public void setUtilities(List<Utility> utilities) {
        this.utilities = utilities;
    }

    public AdditionalTerms getAdditionalTerms() {
        return additionalTerms;
    }
}
