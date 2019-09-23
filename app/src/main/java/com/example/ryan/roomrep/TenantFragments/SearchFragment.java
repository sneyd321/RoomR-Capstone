package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Router.ProfileRouter;
import com.example.ryan.roomrep.Classes.Router.ProfileRouterAction;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;

import java.util.List;


public class SearchFragment extends Fragment {


    Button search;
    String provicneSelected;
    String citySelected;

    List<House> houses;


    private ProfileRouterAction actionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);




        search = view.findViewById(R.id.btnSearchListings);
        search.setOnClickListener(onSearchListings);
        return view;
    }


    Spinner.OnItemSelectedListener onProvinceSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String s = (String) parent.getItemAtPosition(position);


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private View.OnClickListener onSearchListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (actionListener != null) {


                actionListener.onNavigateToProfileListings();
            }

        }
    };


    public void setHouses(List<House> houses) {
        this.houses = houses;
    }


    public void setActionListener(ProfileRouterAction actionListener) {
        this.actionListener = actionListener;
    }
}
