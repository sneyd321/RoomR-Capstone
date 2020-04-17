package com.example.ryan.roomrep.Classes.House;

public abstract class LeaseBuilder {



    protected abstract LeaseBuilder setRentalUnitLocation(RentalUnitLocation rentalUnitLocation) ;

    protected abstract LeaseBuilder setHomeownerLocation(HomeownerLocation homeOwnerLocation);

    protected abstract LeaseBuilder setRentDetail(RentDetail rentDetail);

    protected abstract LeaseBuilder addService(Service service);

    protected abstract LeaseBuilder addUtility(Utility utility);

    protected abstract LeaseBuilder setAdditionalTerms(AdditionalTerms additionalTerms);




}
