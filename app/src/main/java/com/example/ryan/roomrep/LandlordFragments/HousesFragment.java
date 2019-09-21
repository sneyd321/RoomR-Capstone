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
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
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
import java.util.List;

public class HousesFragment extends Fragment {


    Button btnAddHouse;

    LandlordRouterAction routerActionListener;

    Button btnViewListings;

    RecyclerView rcyHouses;

    Button btnNavigateAddTenant;

    private List<House> houses;

    Landlord landlord;

    private static final int BACK_CAMERA_REQUEST = 1889;


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

        HouseRecyclerviewAdapter adapter = new HouseRecyclerviewAdapter(getActivity(), houses);

        rcyHouses.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnAddHouse.setOnClickListener(onAddHouse);

        btnViewListings.setOnClickListener(onViewListings);

        return view;
    }

    private View.OnClickListener onNavigateToAddTenant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, BACK_CAMERA_REQUEST);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap frontPhoto = (Bitmap) data.getExtras().get("data");
        File file = saveImageToInternalStorage(frontPhoto, "Photo", "TestNetworkRepair.png");
        Network network = Network.getInstance();
        network.uploadRepair(file);

    }

    public File saveImageToInternalStorage(Bitmap bitmap, String directoryName, String filename) {
        ContextWrapper contextWrapper = new ContextWrapper(getActivity());

        File directory = contextWrapper.getDir(directoryName, Context.MODE_PRIVATE);

        File path = new File(directory, filename);

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        return path;

    }


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


}
