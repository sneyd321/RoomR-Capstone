package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;

public class Router implements RouterActionListener{


    FragmentManager fragmentManager;

    public Router(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }


    public void navigateToHouses(boolean navigateBack){
        HousesFragment housesFragment = new HousesFragment();
        housesFragment.setRouterAction(this);
        manageBackstack(housesFragment, navigateBack);
    }

    public void navigateToHouses(boolean navigateBack, House house){
        HousesFragment housesFragment = new HousesFragment();
        housesFragment.setRouterAction(this);
        housesFragment.addHouse(house);
        manageBackstack(housesFragment, navigateBack);
    }

    public void navigateToAddHouse(boolean navigateBack) {
        AddHouseFragment addHouseFragment = new AddHouseFragment();
        addHouseFragment.setRouterAction(this);
        manageBackstack(addHouseFragment, navigateBack);
    }

    public void manageBackstack(Fragment fragment, boolean navigateBack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!navigateBack) {
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onNavigateToHousesAdd() {
        navigateToAddHouse(false);
    }

    @Override
    public void onNavigateToHouses(House house) {
        navigateToHouses(false, house);
    }


}
