package com.example.ryan.roomrep;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.HousesRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class HouseFragment extends Fragment {
    HousesRecyclerViewAdapter adapter;
    RecyclerView houseList;
    ImageButton addHouse;
    TextView houseTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house, container, false);


        ((MainActivityLandlord)getActivity()).getHouse().clear();

        addHouse = view.findViewById(R.id.iBtnAddHouse);
        houseTitle = view.findViewById(R.id.txtHouseTitle);

        addHouse.setOnClickListener(onAddItem);
        houseList = view.findViewById(R.id.rcyHouses);

        houseList.setLayoutManager(new LinearLayoutManager(getActivity()));

        Task<QuerySnapshot> result = ((MainActivityLandlord)getActivity()).getHouses();
        result.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        House house = new House();
                        house.setAddress(document.get("Address").toString());
                        house.setStorageReference(document.get("StorageReference").toString());
                        house.setLandlord(((MainActivityLandlord)getActivity()).getLandlord());
                        ((MainActivityLandlord)getActivity()).getHouse().add(house);

                    }

                    adapter = new HousesRecyclerViewAdapter(getActivity(), ((MainActivityLandlord)getActivity()).getHouse());
                    //adapter.setClickListener(this);
                    houseList.setAdapter(adapter);
                    adapter.setClickListener(onItemClick);
                    adapter.notifyDataSetChanged();
                    if (!((MainActivityLandlord)getActivity()).getHouse().isEmpty()){
                        houseTitle.setText("Houses");
                    }

                }
            }
        });



        return view;

    }






    private View.OnClickListener onAddItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivityLandlord)getActivity()).setViewPager(1);
        }
    };



    HousesRecyclerViewAdapter.ItemClickListener onItemClick = new HousesRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(getActivity(), "TODO: Add Tenants", Toast.LENGTH_SHORT).show();
            ((MainActivityLandlord)getActivity()).setCurrentPosition(position);
            ((MainActivityLandlord)getActivity()).setViewPager(3);
        }
    };


}
