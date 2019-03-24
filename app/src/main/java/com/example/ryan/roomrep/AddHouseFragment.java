package com.example.ryan.roomrep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.HousesRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.MessagrRecycleViewAdapter;
import com.example.ryan.roomrep.Classes.House;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;


public class AddHouseFragment extends Fragment {
    HousesRecyclerViewAdapter adapter;
    RecyclerView houseList;
    ArrayList<House> houses;
    ImageButton addHouse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);
        addHouse = view.findViewById(R.id.iBtnAddHouse);
        addHouse.setOnClickListener(onAddItem);
        houses = new ArrayList<>();
        Bitmap houseImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.examplehouse);


        House house = new House("123 Example St.", bitmapToString(houseImage));


        houses.add(house);
        houseList = view.findViewById(R.id.rcyHouses);

        houseList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HousesRecyclerViewAdapter(getActivity(), houses);
        //adapter.setClickListener(this);
        houseList.setAdapter(adapter);

        houseList.setItemAnimator(new DefaultItemAnimator());
        return view;

    }





    private View.OnClickListener onAddItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bitmap houseImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.house);
            House house = new House("123 Example St.", bitmapToString(houseImage));
            houses.add(house);
            adapter.notifyDataSetChanged();
        }
    };


    private String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream btmp = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, btmp);
        byte [] bytes = btmp.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

}
