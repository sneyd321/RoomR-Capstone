package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.LandlordFragments.LandlordListingsFragment;
import com.example.ryan.roomrep.LandlordFragments.TenantProfilesFragment;
import com.example.ryan.roomrep.R;

import java.util.List;

public class LandlordRouter implements LandlordRouterAction {


    FragmentManager fragmentManager;
    HousesFragment housesFragment;

    public LandlordRouter(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
        housesFragment = new HousesFragment();
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
    public void onNavigateToHousesAdd(Landlord landlord) {
        AddHouseFragment addHouseFragment = new AddHouseFragment();
        addHouseFragment.setRouterAction(this);
        addHouseFragment.setLandlord(landlord);
        manageBackstack(addHouseFragment);
    }

    @Override
    public void onAddHouseToHouses(House house) {
        housesFragment.setRouterAction(this);
        housesFragment.addHouse(house);
        manageBackstack(housesFragment);
    }

    @Override
    public void onNavigateToHouses(Landlord landlord) {
        housesFragment.setRouterAction(this);
        housesFragment.setLandlord(landlord);
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
