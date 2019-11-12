package com.example.ryan.roomrep.LandlordFragments;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Adapters.HouseRecyclerviewAdapter;
import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HousesFragment extends Fragment implements ItemClickListener, FragmentEventListener {


    Button btnAddHouse;

    LandlordRouterAction routerActionListener;

    Button btnViewListings;

    RecyclerView rcyHouses;

    Button btnNavigateAddTenant;

    private List<House> houses;

    Landlord landlord;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);



        btnAddHouse = view.findViewById(R.id.btnHousesAddHouse);
        rcyHouses = view.findViewById(R.id.rcyHouses);
        btnViewListings = view.findViewById(R.id.btnHousesViewListings);
        btnNavigateAddTenant = view.findViewById(R.id.button6);
        btnNavigateAddTenant.setOnClickListener(onNavigateToAddTenant);
        rcyHouses.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (houses != null) {
            HouseRecyclerviewAdapter adapter = new HouseRecyclerviewAdapter(getActivity(), houses);
            adapter.setOnItemClickListener(HousesFragment.this);
            rcyHouses.swapAdapter(adapter, true);
            adapter.notifyDataSetChanged();
        }

        btnAddHouse.setOnClickListener(onAddHouse);

        btnViewListings.setOnClickListener(onViewListings);

        btnNavigateAddTenant.setOnClickListener(onNavigateToAddTenant);

        return view;
    }

    private View.OnClickListener onNavigateToAddTenant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (routerActionListener != null){
                routerActionListener.onNaviagateToAddTenant();
            }


        }
    };

    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (routerActionListener != null){
                routerActionListener.onNavigateToHousesAdd(landlord);
            }
        }
    };


    private View.OnClickListener onViewListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (routerActionListener != null){
                routerActionListener.onNavigateToLandlordListings();
            }
        }
    };

    public void setRouterAction(LandlordRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
    }


    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public void getHousesFromServer() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getLandlordHouses(landlord);
    }


    @Override
    public void onItemClick(View view, int position) {
        House house = houses.get(position);
        if (house.getProfiles() == null || house.getTenants() == null){
            house.initProfileTenant();
        }
        if (routerActionListener != null){
            routerActionListener.onNavigateToShowTenant(house);
        }
    }

    @Override
    public void update(String response) {
        JSONArray jsonArray = convertStringToJSONArray(response);
        Gson gson = new Gson();
        Iterator iterator = new JSONArrayIterator(jsonArray);
        while (iterator.hasNext()){
            House house = gson.fromJson(iterator.next().toString(), House.class);

            houses.add(house);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HouseRecyclerviewAdapter adapter = new HouseRecyclerviewAdapter(getActivity(), houses);
                adapter.setOnItemClickListener(HousesFragment.this);
                rcyHouses.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private JSONArray convertStringToJSONArray(String response) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(response);
            return jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
