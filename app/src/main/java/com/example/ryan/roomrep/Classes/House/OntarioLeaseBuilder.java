package com.example.ryan.roomrep.Classes.House;


import java.util.Collections;

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
    public OntarioLeaseBuilder setHomeownerLocation(HomeownerLocation homeOwnerLocation) {
        this.ontarioLease.setHomeownerLocation(homeOwnerLocation);
        return this;
    }

    @Override
    public OntarioLeaseBuilder setRentDetail(RentDetail rentDetail) {
        this.ontarioLease.setRentDetail(rentDetail);
        return this;
    }

    @Override
    public OntarioLeaseBuilder addService(Service service) {
        this.ontarioLease.addService(service);
        return this;
    }

    @Override
    public OntarioLeaseBuilder addUtility(Utility utility) {
        this.ontarioLease.addUtility(utility);
        return this;
    }


    @Override
    public OntarioLeaseBuilder setAdditionalTerms(AdditionalTerms additionalTerms) {
        this.ontarioLease.setAdditionalTerms(additionalTerms);
        return this;
    }

    public OntarioLease build() {
        this.ontarioLease.getServices().removeAll(Collections.singleton(null));
        return this.ontarioLease;
    }
}
