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

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LandlordListingsRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Router.RouterActionListener;
import com.example.ryan.roomrep.R;

import java.util.List;

public class LandlordListingsFragment extends Fragment implements ItemClickListener {


    RecyclerView rcyLandlordListings;

    private RouterActionListener routerActionListener;
    private List<House> houses;
    private LandlordListingsRecyclerviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_listings, container, false);
        rcyLandlordListings = view.findViewById(R.id.rcyLandlordListings);
        rcyLandlordListings.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LandlordListingsRecyclerviewAdapter(getActivity(), houses);
        adapter.setItemClickListener(this);
        rcyLandlordListings.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }


    public void setRouterAction(RouterActionListener routerActionListener) {
        this.routerActionListener = routerActionListener;
    }


    public void setHouseList(List<House> houses){
        this.houses = houses;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (routerActionListener != null){
            routerActionListener.onNavigateToTenantProfile();
        }
    }
}
