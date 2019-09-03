package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;

import java.util.List;

public interface LandlordRouterAction {

    void onNavigateToHousesAdd();

    void onNavigateToHouses(House house);

    void onNavigateToLandlordListings(List<House> houses);

    void onNavigateToTenantProfile();



}
