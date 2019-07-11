package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.LandlordFragments.LandlordListingsFragment;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;

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

    public void navigateToHouses(House house){
        if (housesFragment == null){
            initHouseFragment();
        }
        housesFragment.setRouterAction(this);
        housesFragment.addHouse(house);
        manageBackstack(housesFragment);
    }

    public void navigateToAddHouse() {
        AddHouseFragment addHouseFragment = new AddHouseFragment();
        addHouseFragment.setRouterAction(this);
        manageBackstack(addHouseFragment);
    }


    public void navigateToLandlordListings() {
        LandlordListingsFragment landlordListingsFragment = new LandlordListingsFragment();
        landlordListingsFragment.setRouterAction(this);
        manageBackstack(landlordListingsFragment);

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
        navigateToAddHouse();
    }

    @Override
    public void onNavigateToHouses(House house) {
        navigateToHouses(house);
    }

    @Override
    public void onNavigateToLandlordListings() {
        navigateToLandlordListings();
    }


}
