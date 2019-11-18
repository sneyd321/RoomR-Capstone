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

    public ProfileRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

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
    public void onNavigateToProfileListings(List<House> houses) {
        ListingsFragment listingsFragment = new ListingsFragment();
        listingsFragment.setHouses(houses);
        listingsFragment.setRouterAction(this);
        manageBackstack(listingsFragment);
    }

    @Override
    public void onNavigateToSearchListings() {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setActionListener(this);
        manageBackstack(searchFragment);
    }

    @Override
    public void onNavigateToViewListings(House house) {
        TenantViewListingFragment tenantViewListingFragment = new TenantViewListingFragment();
        tenantViewListingFragment.setHouse(house);
        tenantViewListingFragment.setRouterAction(this);
        manageBackstack(tenantViewListingFragment);
    }

    @Override
    public void popBackStack() {
        fragmentManager.popBackStack(fragmentManager.getBackStackEntryCount() - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
