package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TenantFragments.AddProfileFragment;
import com.example.ryan.roomrep.TenantFragments.ListingsFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;
import com.example.ryan.roomrep.TenantFragments.TenantViewListingFragment;

import java.util.List;

public class ProfileRouter implements ProfileRouterAction{



    FragmentManager fragmentManager;
    List<House> houses;

    public ProfileRouter(FragmentManager fragmentManager, List<House> houses) {
        this.fragmentManager = fragmentManager;
        this.houses = houses;
    }

    private void manageBackstack(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (fragmentManager.findFragmentById(R.id.fragment_container_profile) == null){
            fragmentTransaction.add(R.id.fragment_container_profile, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if (fragmentManager.findFragmentById(R.id.fragment_container_profile) != null){
            fragmentTransaction.replace(R.id.fragment_container_profile, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
    @Override
    public void onNavigateToAddProfile() {
        AddProfileFragment addProfileFragment = new AddProfileFragment();
        addProfileFragment.setRouterAction(this);
        manageBackstack(addProfileFragment);
    }

    @Override
    public void onNavigateToProfileListings() {
        ListingsFragment listingsFragment = new ListingsFragment();
        listingsFragment.setHouses(this.houses);
        listingsFragment.setRouterAction(this);
        manageBackstack(listingsFragment);
    }

    @Override
    public void onNavigateToSearchListings() {
        SearchFragment searchFragment = new SearchFragment();
        manageBackstack(searchFragment);
    }

    @Override
    public void onNavigateToViewListings(House house) {
        TenantViewListingFragment tenantViewListingFragment = new TenantViewListingFragment();
        tenantViewListingFragment.setHouse(house);
        manageBackstack(tenantViewListingFragment);
    }
}
