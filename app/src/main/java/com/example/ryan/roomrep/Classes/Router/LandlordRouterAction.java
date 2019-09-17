package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;

import java.util.List;

public interface LandlordRouterAction {

    void onNavigateToHousesAdd(Landlord landlord);

    void onAddHouseToHouses(House house);

    void onNavigateToHouses(Landlord landlord);

    void onNavigateToLandlordListings(List<House> houses);

    void onNavigateToTenantProfile();



}
