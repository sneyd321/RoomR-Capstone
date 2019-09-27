package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.LandlordFragments.LandlordListingsFragment;
import com.example.ryan.roomrep.LandlordFragments.MessageLandlordFragment;
import com.example.ryan.roomrep.LandlordFragments.SearchTenantFragment;
import com.example.ryan.roomrep.LandlordFragments.ShowTenantFragment;
import com.example.ryan.roomrep.LandlordFragments.TenantProfilesFragment;
import com.example.ryan.roomrep.R;

import java.util.List;

public class LandlordRouter implements LandlordRouterAction {


    FragmentManager fragmentManager;


    List<House> houses;


    public LandlordRouter(FragmentManager fragmentManager, List<House> houses){
        this.fragmentManager = fragmentManager;
        this.houses = houses;
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
    public void onAddHouse(House house) {
        HousesFragment housesFragment = new HousesFragment();
        housesFragment.setRouterAction(this);
        this.houses.add(house);
        housesFragment.setHouses(this.houses);
        manageBackstack(housesFragment);
    }

    @Override
    public void onNavigateToHouses(Landlord landlord) {
        HousesFragment housesFragment = new HousesFragment();
        housesFragment.setRouterAction(this);
        housesFragment.setLandlord(landlord);
        housesFragment.setHouses(this.houses);
        manageBackstack(housesFragment);
    }


    @Override
    public void onNavigateToLandlordListings() {
        LandlordListingsFragment landlordListingsFragment = new LandlordListingsFragment();
        landlordListingsFragment.setHouseList(this.houses);
        landlordListingsFragment.setRouterAction(this);
        manageBackstack(landlordListingsFragment);
    }

    @Override
    public void onNavigateToTenantProfile(House house) {
        TenantProfilesFragment tenantProfilesFragment = new TenantProfilesFragment();
        tenantProfilesFragment.setHouse(house);
        manageBackstack(tenantProfilesFragment);
    }

    @Override
    public void onNaviagateToAddTenant() {
        ShowTenantFragment showTenantFragment = new ShowTenantFragment();
        showTenantFragment.setRouterAction(this);
        manageBackstack(showTenantFragment);
    }

    @Override
    public void onNaviagateToSearchTenant() {
        SearchTenantFragment searchTenantFragment = new SearchTenantFragment();
        searchTenantFragment.setRouterAction(this);
       manageBackstack(searchTenantFragment);
    }

    @Override
    public void onNavigateToMessagePage() {
        MessageLandlordFragment messageLandlordFragment = new MessageLandlordFragment();
        messageLandlordFragment.setRouterAction(this);
        manageBackstack(messageLandlordFragment);
    }
}
