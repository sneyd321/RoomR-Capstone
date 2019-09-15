package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TenantFragments.AddProfileFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;

public class ProfileRouter implements ProfileRouterAction{



    FragmentManager fragmentManager;

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
    public void onNavigateToProfileListings() {

    }

    @Override
    public void onNavigateToSearchListings() {
        SearchFragment searchFragment = new SearchFragment();
        manageBackstack(searchFragment);
    }
}
