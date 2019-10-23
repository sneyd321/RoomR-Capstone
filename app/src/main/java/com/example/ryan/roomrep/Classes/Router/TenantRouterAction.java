package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.LanguageTranslation;
import com.example.ryan.roomrep.Classes.Repair;

import java.util.List;

public interface TenantRouterAction {


    void onNavigateToSearch();

    void onNavigateToListings();

    void onNavigateToViewListings();

    void onNavigateToPayRent();

    void onNavigateToConfirmRent();

    void onNavigateToCompleteRent();

    void onNavigateToRepairPicture();

    void onNavigateToExpertSystem();

    void onNavigateToTenantRepair();

    void onNavigateToMessages();

    void onNavigateToMessagesPeopleList();

    void onNavigateToSendRepair(LanguageTranslation languageTranslation);

    void onAddRepair(Repair repair);

    void onNavigateToTenantRepairsList();

    void onNavigateToTenantRepairsListWithNoRepair();

    void onNavigateToTenantRepairListWithUpdatedRepair(Repair repair, int position);

    void onNavigateToTenantRepairUpdate(Repair repair, int position);
}
