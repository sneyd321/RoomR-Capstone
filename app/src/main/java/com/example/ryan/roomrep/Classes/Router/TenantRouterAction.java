package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;

public interface TenantRouterAction {


    void onNavigateToSearch();

    void onNavigateToListings();



    void onNavigateToPayRent(Tenant tenant);

    void onNavigateToCompleteRent();

    void onNavigateToTenantLanding(House house, Tenant tenant);

    void popBackStack();

}
