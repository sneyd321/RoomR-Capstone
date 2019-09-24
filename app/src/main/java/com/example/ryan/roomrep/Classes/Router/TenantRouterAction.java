package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.LanguageTranslation;

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

}
