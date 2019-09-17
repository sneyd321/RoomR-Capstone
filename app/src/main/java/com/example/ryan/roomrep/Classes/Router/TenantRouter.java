package com.example.ryan.roomrep.Classes.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.example.ryan.roomrep.TenantFragments.ConfirmRentFragment;
import com.example.ryan.roomrep.TenantFragments.ExpertSystemFragment;
import com.example.ryan.roomrep.TenantFragments.ListTargetChatUserFragment;
import com.example.ryan.roomrep.TenantFragments.ListingsFragment;
import com.example.ryan.roomrep.TenantFragments.MessagRFragment;
import com.example.ryan.roomrep.TenantFragments.PayRentFragment;
import com.example.ryan.roomrep.TenantFragments.RepairPictureFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;
import com.example.ryan.roomrep.TenantFragments.SendRepairFragment;
import com.example.ryan.roomrep.TenantFragments.TenantRepairFragment;


import java.util.List;

public class TenantRouter implements TenantRouterAction {

    FragmentManager fragmentManager;

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
    public void onNavigateToSearch() {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setActionListener(this);
        manageBackstack(searchFragment);
    }

    @Override
    public void onNavigateToListings() {
        ListingsFragment listingsFragment = new ListingsFragment();
        //listingsFragment.setActionListener(this);
        manageBackstack(listingsFragment);
    }

    @Override
    public void onNavigateToPayRent() {
        PayRentFragment payRentFragment = new PayRentFragment();
        payRentFragment.setActionListener(this);
        manageBackstack(payRentFragment);
    }

    @Override
    public void onNavigateToConfirmRent() {
        ConfirmRentFragment confirmRentFragment = new ConfirmRentFragment();
        confirmRentFragment.setActionListener(this);
        manageBackstack(confirmRentFragment);
    }

    @Override
    public void onNavigateToCompleteRent() {
        CompleteRentFragment completeRentFragment = new CompleteRentFragment();
        //completeRentFragment.setActionListener(this);
        manageBackstack(completeRentFragment);
    }

    @Override
    public void onNavigateToRepairPicture() {
        RepairPictureFragment repairPictureFragment = new RepairPictureFragment();
        repairPictureFragment.setActionListener(this);
        manageBackstack(repairPictureFragment);
    }

    @Override
    public void onNavigateToExpertSystem() {
        ExpertSystemFragment expertSystemFragment = new ExpertSystemFragment();
        expertSystemFragment.setActionListener(this);
        manageBackstack(expertSystemFragment);
    }

    @Override
    public void onNavigateToTenantRepair() {
        TenantRepairFragment tenantRepairFragment = new TenantRepairFragment();
        //tenantRepairFragment.setActionListener(this);
        manageBackstack(tenantRepairFragment);
    }

    @Override
    public void onNavigateToMessages() {
        MessagRFragment messagRFragment = new MessagRFragment();
        //messagRFragment.setActionListener(this);

        manageBackstack(messagRFragment);

    }

    @Override
    public void onNavigateToMessagesPeopleList() {

        ListTargetChatUserFragment listTargetChatUserFragment = new ListTargetChatUserFragment();
        listTargetChatUserFragment.setActionListener(this);

        manageBackstack(listTargetChatUserFragment);
    }

    @Override
    public void onNavigateToSendRepair() {
        SendRepairFragment sendRepairFragment = new SendRepairFragment();
        sendRepairFragment.setActionListener(this);
        manageBackstack(sendRepairFragment);
    }
}
