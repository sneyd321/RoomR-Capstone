package com.example.ryan.roomrep.Classes.Lease;

import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;

import java.util.List;

public abstract class LeaseBuilder {

    protected Lease lease;

    public LeaseBuilder() {
        this.lease = null;
    }

    protected abstract LeaseBuilder setRentalUnitLocation(RentalUnitLocation rentalUnitLocation) ;

    protected abstract LeaseBuilder setHomeownerLocation(HomeOwnerLocation homeOwnerLocation);

    protected abstract LeaseBuilder setRentDetail(RentDetail rentDetail);

    protected abstract LeaseBuilder setServices(List<Service> services);

    protected abstract LeaseBuilder setUtilities(List<Utility> utilities);

    protected abstract LeaseBuilder setAdditionalTerms(AdditionalTerms additionalTerms);

    public Lease build() {
        return this.lease;
    }


}
