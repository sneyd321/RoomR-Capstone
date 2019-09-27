package com.example.ryan.roomrep.LandlordFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.TenantProfileRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

public class TenantProfilesFragment extends Fragment implements ItemClickListener {


    RecyclerView rcyTenantProfile;
    TenantProfileRecyclerviewAdapter adapter;


    private House house;
    List<Profile> profiles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_profiles, container, false);

        //Tenant tenant = new Tenant("Ryan", "Sneyd", "a@s.com", "a");
        //tenantProfiles.add(tenant);

         profiles = house.getProfiles();

        rcyTenantProfile = view.findViewById(R.id.rcyTenantProfiles);
        rcyTenantProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TenantProfileRecyclerviewAdapter(getActivity(), profiles);
        adapter.setItemClickListener(this);
        rcyTenantProfile.setAdapter(adapter);



        return view;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public void onItemClick(View view, final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        final Profile profile = profiles.get(position);
        alertDialog.setTitle("Contact " + profile.getFirstName() + " " + profile.getLastName());
        alertDialog.setMessage("Would you like to contact this tenant?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Contact", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Network network = Network.getInstance();
                network.contactProfile(profile);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                profiles.remove(position);
                house.setApplicants(house.getApplicants() - 1);
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
