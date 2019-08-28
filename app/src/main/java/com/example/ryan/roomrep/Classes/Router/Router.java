package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.LandlordFragments.LandlordListingsFragment;
import com.example.ryan.roomrep.LandlordFragments.TenantProfilesFragment;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

public class Router implements RouterActionListener{


    FragmentManager fragmentManager;
    HousesFragment housesFragment;

    public Router(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    private void initHouseFragment(){
        housesFragment = new HousesFragment();
    }


    public void navigateToHouses(){
        if (housesFragment == null){
            initHouseFragment();
        }
        housesFragment.setRouterAction(this);
        manageBackstack(housesFragment);
    }






    public void manageBackstack(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (fragmentManager.findFragmentById(R.id.fragment_container) == null){
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if (fragmentManager.findFragmentById(R.id.fragment_container) != null){
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onNavigateToHousesAdd() {
        AddHouseFragment addHouseFragment = new AddHouseFragment();
        addHouseFragment.setRouterAction(this);
        manageBackstack(addHouseFragment);
    }

    @Override
    public void onNavigateToHouses(House house) {
        if (housesFragment == null){
            initHouseFragment();
        }
        housesFragment.setRouterAction(this);
        housesFragment.addHouse(house);
        manageBackstack(housesFragment);
    }

    @Override
    public void onNavigateToLandlordListings(List<House> houses) {
        LandlordListingsFragment landlordListingsFragment = new LandlordListingsFragment();
        landlordListingsFragment.setHouseList(houses);
        landlordListingsFragment.setRouterAction(this);
        manageBackstack(landlordListingsFragment);
    }

    @Override
    public void onNavigateToTenantProfile() {
        TenantProfilesFragment tenantProfilesFragment = new TenantProfilesFragment();
        manageBackstack(tenantProfilesFragment);
    }


}
