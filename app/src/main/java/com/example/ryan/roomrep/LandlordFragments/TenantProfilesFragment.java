package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.TenantProfileRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

public class TenantProfilesFragment extends Fragment {


    RecyclerView rcyTenantProfile;
    TenantProfileRecyclerviewAdapter adapter;

    List<Tenant> tenantProfiles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_profiles, container, false);

        Tenant tenant = new Tenant("Ryan", "Sneyd", "a@s.com", "a");
        tenantProfiles.add(tenant);

        rcyTenantProfile = view.findViewById(R.id.rcyTenantProfiles);
        rcyTenantProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TenantProfileRecyclerviewAdapter(getActivity(), tenantProfiles);

        rcyTenantProfile.setAdapter(adapter);



        return view;
    }
}
