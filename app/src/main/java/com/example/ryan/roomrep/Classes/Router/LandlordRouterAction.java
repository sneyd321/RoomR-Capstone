package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;

import java.util.List;

public interface LandlordRouterAction {

    void onNavigateToHousesAdd();

    void onAddHouse(House house);

    void onNavigateToHouses(Landlord landlord);

    void onNavigateToLandlordListings(Landlord landlord);

    void onNavigateToTenantProfile(House house);









    void onNavigateToNotifyR();

    void popBackStack();







}
