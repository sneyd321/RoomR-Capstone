package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;

import java.util.List;

public interface LandlordRouterAction {

    void onNavigateToHousesAdd();

    void onAddHouseToHouses(House house);

    void onNavigateToHouses();

    void onNavigateToLandlordListings(List<House> houses);

    void onNavigateToTenantProfile();



}
