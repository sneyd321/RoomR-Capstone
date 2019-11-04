package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.LanguageTranslation;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;

import java.util.List;

public interface TenantRouterAction {


    void onNavigateToSearch(House house);

    void onNavigateToListings();

    void onNavigateToViewListings();

    void onNavigateToPayRent(Tenant tenant);

    void onNavigateToConfirmRent();

    void onNavigateToCompleteRent();

    void onNavigateToRepairPicture();

    void onNavigateToExpertSystem();

    void onNavigateToTenantRepair();

    void onNavigateToMessages();

    void onNavigateToMessagesPeopleList(Tenant tenant, House house);

    void onNavigateToSendRepair(LanguageTranslation languageTranslation);

    void onAddRepair(Repair repair);

    void onNavigateToTenantRepairsList();

    void onNavigateToTenantRepairsListWithNoRepair();

    void onNavigateToTenantRepairListWithUpdatedRepair(Repair repair, int position);

    void onNavigateToTenantRepairUpdate(Repair repair, int position);

    void onSetRepairs(List<Repair> repairs);

    void onNavigateToRatingLandlord(String landlordName);
}
