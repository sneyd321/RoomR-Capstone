package com.example.ryan.roomrep.Classes.Lease;


import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;

import java.util.List;

public class OntarioLeaseBuilder extends LeaseBuilder {

    private OntarioLease ontarioLease;

    public OntarioLeaseBuilder() {
        this.ontarioLease = new OntarioLease();
    }

    @Override
    public OntarioLeaseBuilder setRentalUnitLocation(RentalUnitLocation rentalUnitLocation) {
        this.ontarioLease.setRentalUnitLocation(rentalUnitLocation);
        return this;
    }

    @Override
    public OntarioLeaseBuilder setHomeownerLocation(HomeOwnerLocation homeOwnerLocation) {
        this.ontarioLease.setHomeownerLocation(homeOwnerLocation);
        return this;
    }



    @Override
    public OntarioLeaseBuilder setRentDetail(RentDetail rentDetail) {
        this.ontarioLease.setRentDetail(rentDetail);
        return this;
    }

    @Override
    public OntarioLeaseBuilder setServices(List<Service> services) {
        this.ontarioLease.setServices(services);
        return this;
    }

    @Override
    public OntarioLeaseBuilder setUtilities(List<Utility> utilities) {
        this.ontarioLease.setUtilities(utilities);
        return this;
    }
    @Override
    public OntarioLeaseBuilder setAdditionalTerms(AdditionalTerms additionalTerms) {
        this.ontarioLease.setAdditionalTerms(additionalTerms);
        return this;
    }
}
