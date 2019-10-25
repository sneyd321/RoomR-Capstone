package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.Tenant.Tenant;

public interface RentRouterAction {

    void onNavigateToPayRent(Tenant tenant);

    void onNavigateToConfirmRent();

    void onNavigateToCompleteRent();
}
