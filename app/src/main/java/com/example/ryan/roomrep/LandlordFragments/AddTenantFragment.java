package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.AddTenantRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.HousesRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.ViewModels.HouseViewModel;
import com.example.ryan.roomrep.ViewModels.TenantViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddTenantFragment extends Fragment {


    RecyclerView rcyAddTenant;
    AddTenantRecyclerViewAdapter adapter;
    TenantViewModel tenantViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tenant, container, false);
        rcyAddTenant = view.findViewById(R.id.rcyNotifyRTenants);
        rcyAddTenant.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Tenant> notApproved = new ArrayList<>();
        adapter = new AddTenantRecyclerViewAdapter(getActivity(), notApproved);
        rcyAddTenant.setAdapter(adapter);
        tenantViewModel = ViewModelProviders.of(this).get(TenantViewModel.class);
        tenantViewModel.getAllTenants().observe(getActivity(), new Observer<List<Tenant>>() {
            @Override
            public void onChanged(List<Tenant> tenants) {
                if (tenants.isEmpty()){
                    return;
                }
                for (Tenant tenant : tenants) {
                    if (!tenant.isApproved()) {
                        notApproved.add(tenant);
                    }
                }
                adapter = new AddTenantRecyclerViewAdapter(getActivity(), notApproved);
                rcyAddTenant.swapAdapter(adapter, true);
            }
        });
        return view;
    }




}
