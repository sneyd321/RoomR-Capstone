package com.example.ryan.roomrep.Classes.Lease;

import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;

public abstract class LeaseBuilder {



    protected abstract LeaseBuilder setRentalUnitLocation(RentalUnitLocation rentalUnitLocation) ;

    protected abstract LeaseBuilder setHomeownerLocation(HomeownerLocation homeOwnerLocation);

    protected abstract LeaseBuilder setRentDetail(RentDetail rentDetail);

    protected abstract LeaseBuilder addService(Service service);

    protected abstract LeaseBuilder addUtility(Utility utility);

    protected abstract LeaseBuilder setAdditionalTerms(AdditionalTerms additionalTerms);




}
