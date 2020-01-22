package com.example.ryan.roomrep.Classes.Router;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.example.ryan.roomrep.TenantFragments.ListingsFragment;
import com.example.ryan.roomrep.TenantFragments.PayRentFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;
import com.example.ryan.roomrep.TenantFragments.TenantLandingFragment;


import java.util.ArrayList;

public class TenantRouter implements TenantRouterAction {

    FragmentManager fragmentManager;

    Tenant tenant;
    House house;


    public TenantRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void manageBackstack(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (fragmentManager.findFragmentById(R.id.tenant_container) == null){
            fragmentTransaction.add(R.id.tenant_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if (fragmentManager.findFragmentById(R.id.tenant_container) != null){
            fragmentTransaction.replace(R.id.tenant_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }





    @Override
    public void onNavigateToTenantLanding(House house, Tenant tenant) {
        TenantLandingFragment tenantLandingFragment = new TenantLandingFragment();
        this.house = house;
        this.tenant = tenant;
        tenantLandingFragment.setHouse(house);
        tenantLandingFragment.setTenant(tenant);
        manageBackstack(tenantLandingFragment);
    }

    @Override
    public void onNavigateToSearch() {
        SearchFragment searchFragment = new SearchFragment();
        //searchFragment.setActionListener(this);
        manageBackstack(searchFragment);
    }


    @Override
    public void onNavigateToListings() {
        ListingsFragment listingsFragment = new ListingsFragment();
        listingsFragment.setHouses(new ArrayList<House>());
        //listingsFragment.setActionListener(this);
        manageBackstack(listingsFragment);
    }



    @Override
    public void onNavigateToPayRent(Tenant tenant) {
        PayRentFragment payRentFragment = new PayRentFragment();
        payRentFragment.setActionListener(this);
        payRentFragment.setTenant(tenant);
        payRentFragment.setHouse(this.house);
        manageBackstack(payRentFragment);
    }



    @Override
    public void onNavigateToCompleteRent() {
        CompleteRentFragment completeRentFragment = new CompleteRentFragment();
        //completeRentFragment.setActionListener(this);
        manageBackstack(completeRentFragment);
    }


    @Override
    public void popBackStack() {
        fragmentManager.popBackStack(fragmentManager.getBackStackEntryCount() - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void setTenantAndHouse(Tenant tenant, House house) {
        this.tenant = tenant;
        this.house = house;
    }

}
