package com.example.ryan.roomrep.Classes.Lease;


import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;

import java.util.List;

public interface Lease {



    void setRentalUnitLocation(RentalUnitLocation rentalUnitLocation);
    void setHomeownerLocation(HomeOwnerLocation homeownerLocation);
    void setRentDetail(RentDetail rentDetail);
    void setServices(List<Service> services);
    void setUtilities(List<Utility> utilities);
    void setAdditionalTerms(AdditionalTerms additionalTerms);

}
