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
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.HouseRecyclerviewAdapter;
import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LandlordListingsRecyclerviewAdapter;
import com.example.ryan.roomrep.Adapters.LongClickItemListener;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LandlordListingsFragment extends Fragment implements ItemClickListener, AddListingDialogActionListener, LongClickItemListener {


    RecyclerView rcyLandlordListings;
    Button btnPostListing;
    TextView txtNoListings;

    private LandlordRouterAction routerActionListener;
    private List<House> houses;
    private List<House> listedHouses;
    private LandlordListingsRecyclerviewAdapter adapter;

    private Landlord landlord;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_listings, container, false);
        rcyLandlordListings = view.findViewById(R.id.rcyLandlordListings);
        txtNoListings = view.findViewById(R.id.txtLandlordListingsNoListings);
        rcyLandlordListings.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnPostListing = view.findViewById(R.id.btnLandlordListingsPostListing);
        btnPostListing.setOnClickListener(onAddListing);

        listedHouses = new ArrayList<>();
        for (House house : houses){
            if (house.getPosted()){
                listedHouses.add(house);
            }
        }


        if (listedHouses.size() != 0){
            txtNoListings.setVisibility(View.INVISIBLE);

            adapter = new LandlordListingsRecyclerviewAdapter(getActivity(), listedHouses);

            adapter.setItemClickListener(this);
            adapter.setLongClickItemListener(this);
            rcyLandlordListings.setAdapter(adapter);
            return view;
        }

        txtNoListings.setVisibility(View.VISIBLE);
        return view;
    }


    public void setRouterAction(LandlordRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
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

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }


    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public void onAddListing(House house) {
        txtNoListings.setVisibility(View.INVISIBLE);
        if (listedHouses.size() == 0){
            adapter = new LandlordListingsRecyclerviewAdapter(getActivity(), listedHouses);
            adapter.setItemClickListener(this);
            adapter.setLongClickItemListener(this);
            rcyLandlordListings.setAdapter(adapter);
        }
        adapter.addHouse(house, adapter.getItemCount());
    }


    @Override
    public boolean onLongClick(View view, final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        alertDialog.setTitle("Remove Listing");
        alertDialog.setMessage("Are you sure you want to remove this property?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                House house = listedHouses.get(position);
                house.setPosted(false);
                Network network = Network.getInstance();
                network.postListing(house);
                adapter.removeHouse(house);
                if (listedHouses.isEmpty()){
                    txtNoListings.setVisibility(View.VISIBLE);
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Don't Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        return true;
    }
}
