package com.example.ryan.roomrep.Classes.Router;

import com.example.ryan.roomrep.Classes.House.House;

import java.util.List;

public interface ProfileRouterAction {

    void onNavigateToAddProfile();

    void onNavigateToProfileListings(List<House> houses);

    void onNavigateToSearchListings();

    void onNavigateToViewListings(House house);

    void popBackStack();

}
