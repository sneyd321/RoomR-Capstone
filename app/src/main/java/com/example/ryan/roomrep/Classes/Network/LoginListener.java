package com.example.ryan.roomrep.Classes.Network;

import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Tenant;

public interface LoginListener {

    void onLoginLandlord(Landlord landlord);

    void onLoginTenant(Tenant tenant);


}
