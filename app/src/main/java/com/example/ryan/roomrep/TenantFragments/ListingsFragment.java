package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.TenantListingsRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Router.ProfileRouterAction;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class ListingsFragment extends Fragment implements ItemClickListener, FragmentEventListener {


    RecyclerView rcyTenantListings;
    Button btnExitListings;
    ProfileRouterAction profileRouterAction;
    List<House> houses;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listings, container, false);
        rcyTenantListings = view.findViewById(R.id.rcyTenantListings);
        btnExitListings = view.findViewById(R.id.btnExitListings);

        rcyTenantListings.setLayoutManager(new LinearLayoutManager(getActivity()));

        TenantListingsRecyclerViewAdapter adapter = new TenantListingsRecyclerViewAdapter(getActivity(), houses);
        adapter.setItemClickListener(this);
        rcyTenantListings.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }


    public void setRouterAction(ProfileRouterAction profileRouterAction) {
        this.profileRouterAction = profileRouterAction;
    }

    public void setHouses(List<House> houses){
        this.houses = houses;
    }



    @Override
    public void update(String response) {

    }

    @Override
    public void onItemClick(View view, int position) {
        if (profileRouterAction != null)  {
            profileRouterAction.onNavigateToViewListings(houses.get(position));
        }
    }
}
