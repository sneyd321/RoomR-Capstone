package com.example.ryan.roomrep.Classes.Router;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.LandlordFragments.AddAgreementFragment;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.AddHouseStatePagerFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.LandlordFragments.LandlordListingsFragment;
import com.example.ryan.roomrep.LandlordFragments.NotifyRFragment;

import java.util.List;

public class LandlordRouter implements LandlordRouterAction {


    private FragmentManager fragmentManager;


    private List<House> houses;




    public LandlordRouter(FragmentManager fragmentManager, List<House> houses){
        this.fragmentManager = fragmentManager;
        this.houses = houses;
    }


    public void manageBackstack(Fragment fragment) {


    }


    @Override
    public void onNavigateToHousesAdd() {
        AddHouseStatePagerFragment addHouseStatePagerFragment = new AddHouseStatePagerFragment();
        addHouseStatePagerFragment.setFragment(new AddHouseFragment());
        addHouseStatePagerFragment.setFragment(new AddAgreementFragment());
        manageBackstack(addHouseStatePagerFragment);
    }

    @Override
    public void onAddHouse(House house) {
        HousesFragment housesFragment = new HousesFragment();

        this.houses.add(house);
        manageBackstack(housesFragment);
    }

    @Override
    public void onNavigateToHouses(Landlord landlord) {
        HousesFragment housesFragment = new HousesFragment();


        manageBackstack(housesFragment);
    }


    @Override
    public void onNavigateToLandlordListings(Landlord landlord) {
        LandlordListingsFragment landlordListingsFragment = new LandlordListingsFragment();
        landlordListingsFragment.setLandlord(landlord);
        landlordListingsFragment.setHouses(this.houses);
        landlordListingsFragment.setRouterAction(this);
        manageBackstack(landlordListingsFragment);
    }

    @Override
    public void onNavigateToTenantProfile(House house) {

    }





    @Override
    public void onNavigateToNotifyR() {
        NotifyRFragment notifyRFragment = new NotifyRFragment();
        notifyRFragment.setHouse(this.houses);
        notifyRFragment.initNetwork();
        manageBackstack(notifyRFragment);
    }



    @Override
    public void popBackStack() {
        fragmentManager.popBackStack(fragmentManager.getBackStackEntryCount() - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


}
