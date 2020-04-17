package com.example.ryan.roomrep.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.App.Dialog.Dialog;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.App.Permission;
import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.example.ryan.roomrep.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import okhttp3.Request;

public class SignUpStatePagerFragment extends Fragment implements NetworkObserver {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private StatePagerAdapter adapter;
    private Button btnSignUp, btnBack;

    private Permission permission;
    private Network network;

    private HomeownerSignUpFragment homeownerSignUpFragment;
    private TenantSignUpFragment tenantSignUpFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_state_pager, container, false);
        tabLayout = view.findViewById(R.id.signUpTabLayout);
        viewPager = view.findViewById(R.id.signUpViewPager);
        btnSignUp = view.findViewById(R.id.btnTSUsignup);
        btnSignUp.setOnClickListener(onSignUp);
        btnBack = view.findViewById(R.id.btnTSUBack);
        btnBack.setOnClickListener(onBack);
        homeownerSignUpFragment = new HomeownerSignUpFragment();
        tenantSignUpFragment = new TenantSignUpFragment();
        //adapter = setupViewPager();
        //viewPager.setAdapter(adapter);
        //tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.getTabAt(0).setText("Homeowner");
        tabLayout.getTabAt(1).setText("Tenant");

        permission = new Permission(getActivity());
        network = Network.getInstance();
        network.registerObserver(SignUpStatePagerFragment.this);


        return view;
    }

    private View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    if (!homeownerSignUpFragment.getUiTextInputValidation().validateUI(btnSignUp)){
                        return;
                    }
                    if (!permission.doesHaveInternetPermission()){
                        permission.requestInternetPermission();
                        return;
                    }
                    if (!network.isNetworkAvailable(getActivity().getApplication())) {
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setMessage("No Internet");
                        dialog.buildErrorDialog().show();
                        return;
                    }

                    Request homeownerRequest = network.buildPostRequestNoAuth(homeownerSignUpFragment.createHomeowner(), "TempHomeowner");
                    network.send(homeownerRequest);
                    break;
                case 1:
                    if (!tenantSignUpFragment.getUiTextInputValidation().validateUI(btnSignUp)){
                        return;
                    }
                    if (!permission.doesHaveInternetPermission()){
                        permission.requestInternetPermission();
                        return;
                    }
                    if (!network.isNetworkAvailable(getActivity().getApplication())) {
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setMessage("No Internet");
                        dialog.buildErrorDialog().show();
                        return;
                    }
                    Request tenantRequest = network.buildPostRequestNoAuth(tenantSignUpFragment.createTenant(), "Tenant");
                    network.send(tenantRequest);
                    break;
            }
        }
    };



    private View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
        }
    };


    private StatePagerAdapter setupViewPager(){
        //StatePagerAdapter adapter = new StatePagerAdapter(getChildFragmentManager());
        //adapter.addFragment(homeownerSignUpFragment, "");
        //adapter.addFragment(tenantSignUpFragment, "");
        return adapter;
    }

    @Override
    public void update(String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response.equals("Success")) {
                    switch (tabLayout.getSelectedTabPosition()) {
                        case 0:
                            Toast.makeText(getActivity(), "Check email for confirmation.", Toast.LENGTH_SHORT).show();
                            getActivity().onBackPressed();
                            return;
                        case 1:
                            Toast.makeText(getActivity(), "Account created. Waiting on homeowner approval.", Toast.LENGTH_LONG).show();
                            getActivity().onBackPressed();
                            return;
                    }
                }
                Dialog dialog = new Dialog(getActivity());
                dialog.setMessage(response);
                dialog.buildErrorDialog().show();
            }
        });
    }
}
