package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.R;

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

    public void navigateToAddHouse(boolean navigateBack) {
        AddHouseFragment addHouseFragment = new AddHouseFragment();
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
            fragmentManager.popBackStack(1, 0);
        }
    }

    @Override
    public void onNavigateToHousesAdd() {
        navigateToAddHouse(false);
    }
}
