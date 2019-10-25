package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.example.ryan.roomrep.TenantFragments.ConfirmRentFragment;
import com.example.ryan.roomrep.TenantFragments.PayRentFragment;

import java.util.List;

public class RentRouter implements RentRouterAction {




    FragmentManager fragmentManager;

    public RentRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

    }

    public void manageBackstack(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (fragmentManager.findFragmentById(R.id.tenant_rent_container) == null){
            fragmentTransaction.add(R.id.tenant_rent_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if (fragmentManager.findFragmentById(R.id.tenant_rent_container) != null){
            fragmentTransaction.replace(R.id.tenant_rent_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }


    @Override
    public void onNavigateToPayRent(Tenant tenant) {
        PayRentFragment payRentFragment = new PayRentFragment();
        payRentFragment.setActionListener(this);
        payRentFragment.setTenant(tenant);
        manageBackstack(payRentFragment);
    }

    @Override
    public void onNavigateToConfirmRent() {
        ConfirmRentFragment confirmRentFragment = new ConfirmRentFragment();
        manageBackstack(confirmRentFragment);
    }

    @Override
    public void onNavigateToCompleteRent() {
        CompleteRentFragment completeRentFragment = new CompleteRentFragment();
        //completeRentFragment.setActionListener(this);
        manageBackstack(completeRentFragment);
    }


}
