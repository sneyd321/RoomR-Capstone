package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.RepairContact;

import java.util.List;

public interface LandlordRouterAction {

    void onNavigateToHousesAdd(Landlord landlord);

    void onAddHouse(House house);

    void onNavigateToHouses(Landlord landlord);

    void onNavigateToLandlordListings();

    void onNavigateToTenantProfile(House house);

    void onNaviagateToAddTenant();

    void onNaviagateToSearchTenant(House house);

    void onNavigateToMessagePage();

    void onNavigateToShowTenant(House house);

    void onNavigateToLandlordRepairView(Repair repair, int positon);

    void onNavigateToLandlordRepairs();

    void onNavigateToLandlordRepairsWithNoUpdate();

    void onNavigateToLandlordRepairsWithUpdate(Repair repair, int position);

    void onNavigateToNotifyR();

    void onNavigateToContactRepairman(String address, String category);

    void onSetRepairs(List<Repair> repairs);

    void onSetRepairmans(List<RepairContact> repairContacts);

}
