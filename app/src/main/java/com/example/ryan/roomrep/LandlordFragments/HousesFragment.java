package com.example.ryan.roomrep.LandlordFragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.HouseRecyclerviewAdapter;
import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LongClickItemListener;
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

public class HousesFragment extends Fragment implements ItemClickListener, LongClickItemListener {


    Button btnAddHouse;
    LandlordRouterAction routerActionListener;
    RecyclerView rcyHouses;
    TextView txtLandlordGreeting;
    TextView txtNoHouses;
    ImageView imgUpArrow;


    private List<House> houses;
    HouseRecyclerviewAdapter adapter;

    Landlord landlord;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);


        txtLandlordGreeting = view.findViewById(R.id.txtHousesGreetingName);
        txtLandlordGreeting.setText(landlord.getFirstName() + " " + landlord.getLastName());
        btnAddHouse = view.findViewById(R.id.btnHousesAddHouse);
        rcyHouses = view.findViewById(R.id.rcyHouses);
        txtNoHouses = view.findViewById(R.id.txtHousesNoHouses);
        imgUpArrow = view.findViewById(R.id.imgHousesUpArrow);
        rcyHouses.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnAddHouse.setOnClickListener(onAddHouse);



        if (houses != null) {
            for (House house : houses) {
                if (house.getUrl().isEmpty()){
                    LocationUnknownDialogFragment locationUnknownDialogFragment = new LocationUnknownDialogFragment();
                    locationUnknownDialogFragment.setHouse(house);
                    locationUnknownDialogFragment.show(getActivity().getFragmentManager(), null);
                    house.setUrl("Not Empty");
                }
            }
        }
        if (houses.size() != 0) {
            txtNoHouses.setVisibility(View.INVISIBLE);
            imgUpArrow.setVisibility(View.INVISIBLE);
            adapter = new HouseRecyclerviewAdapter(getActivity(), houses);
            adapter.setOnItemClickListener(HousesFragment.this);
            adapter.setLongClickItemListener(this);
            rcyHouses.swapAdapter(adapter, true);
            adapter.notifyDataSetChanged();
            return view;
        }
        txtNoHouses.setVisibility(View.VISIBLE);
        imgUpArrow.setVisibility(View.VISIBLE);

        Animation animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        Animation animslideup = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top);

        final AnimationSet s = new AnimationSet(true);
        s.setInterpolator(new AccelerateInterpolator());

        s.addAnimation(animslideup);
        s.addAnimation(animFadein);
        imgUpArrow.startAnimation(s);

        return view;
    }



    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (routerActionListener != null){
                routerActionListener.onNavigateToHousesAdd(landlord);
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
    public boolean onLongClick(View view, final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        alertDialog.setTitle("Remove House");
        alertDialog.setMessage("Are you sure you want to remove this property?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                House house = houses.get(position);
                adapter.removeHouse(house);
                Network network = Network.getInstance();
                network.removeHouse(house);

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
