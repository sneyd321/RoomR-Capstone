package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;

public interface ProfileRouterAction {

    void onNavigateToAddProfile();

    void onNavigateToProfileListings();

    void onNavigateToSearchListings();

    void onNavigateToViewListings(House house);

}
