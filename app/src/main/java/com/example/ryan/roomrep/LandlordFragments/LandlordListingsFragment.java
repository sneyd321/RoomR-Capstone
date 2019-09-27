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
import android.widget.Button;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LandlordListingsRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

public class LandlordListingsFragment extends Fragment implements ItemClickListener, AddListingDialogActionListener {


    RecyclerView rcyLandlordListings;
    Button btnPostListing;

    private LandlordRouterAction routerActionListener;
    private List<House> houses;
    private List<House> listedHouses;
    private LandlordListingsRecyclerviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_listings, container, false);
        rcyLandlordListings = view.findViewById(R.id.rcyLandlordListings);
        rcyLandlordListings.setLayoutManager(new LinearLayoutManager(getActivity()));


        listedHouses = new ArrayList<>();
        for (House house : houses){
            if (house.getPosted()){
                listedHouses.add(house);
            }
        }
        adapter = new LandlordListingsRecyclerviewAdapter(getActivity(), listedHouses);
        adapter.setItemClickListener(this);
        rcyLandlordListings.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnPostListing = view.findViewById(R.id.btnLandlordListingsPostListing);
        btnPostListing.setOnClickListener(onAddListing);

        return view;
    }


    public void setRouterAction(LandlordRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
    }


    public void setHouseList(List<House> houses){
        this.houses = houses;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (routerActionListener != null){
            House house = listedHouses.get(position);
            routerActionListener.onNavigateToTenantProfile(house);
        }
    }

    View.OnClickListener onAddListing = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddListingDialogFragment addListingDialogFragment = new AddListingDialogFragment();
            addListingDialogFragment.setHouses(houses);
            addListingDialogFragment.setAddListingDialogActionListener(LandlordListingsFragment.this);
            addListingDialogFragment.show(getActivity().getFragmentManager(), null);


        }
    };


    @Override
    public void onAddListing(House house) {
        this.listedHouses.add(house);
        adapter.notifyDataSetChanged();
    }




}
