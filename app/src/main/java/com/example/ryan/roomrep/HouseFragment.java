package com.example.ryan.roomrep;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ryan.roomrep.Adapters.HousesRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class HouseFragment extends Fragment {
    HousesRecyclerViewAdapter adapter;
    RecyclerView houseList;
    ArrayList<House> houses;
    ImageButton addHouse;
    Button update;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        addHouse = view.findViewById(R.id.iBtnAddHouse);
        update = view.findViewById(R.id.btnUpdate);
        addHouse.setOnClickListener(onAddItem);
        update.setOnClickListener(onUpdate);
        houses = new ArrayList<>();
        houses.add(((MainActivityLandlord)getActivity()).getHouse());





        houseList = view.findViewById(R.id.rcyHouses);

        houseList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HousesRecyclerViewAdapter(getActivity(), houses);
        //adapter.setClickListener(this);
        houseList.setAdapter(adapter);


        return view;

    }

    View.OnClickListener onUpdate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            adapter.notifyDataSetChanged();
        }
    };







    private View.OnClickListener onAddItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivityLandlord)getActivity()).setViewPager(1);
        }
    };




}
