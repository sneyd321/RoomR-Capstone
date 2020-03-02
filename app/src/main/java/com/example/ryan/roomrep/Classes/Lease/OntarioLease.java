package com.example.ryan.roomrep.Classes.Lease;


import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;

import java.util.List;

public class OntarioLease implements Lease {

    private RentalUnitLocation rentalUnitLocation;
    private HomeOwnerLocation homeownerLocation;
    private RentDetail rentDetail;
    private List<Service> services;
    private List<Utility> utilities;
    private AdditionalTerms additionalTerms;


    @Override
    public void setRentalUnitLocation(RentalUnitLocation location) {
        this.rentalUnitLocation = location;
    }

    @Override
    public void setHomeownerLocation(HomeOwnerLocation homeownerLocation) {
        this.homeownerLocation = homeownerLocation;
    }

    @Override
    public void setRentDetail(RentDetail rentDetail) {
        this.rentDetail = rentDetail;
    }

    @Override
    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public void setUtilities(List<Utility> utilities) {
        this.utilities = utilities;
    }

    @Override
    public void setAdditionalTerms(AdditionalTerms additionalTerms) {
        this.additionalTerms = additionalTerms;
    }
}
