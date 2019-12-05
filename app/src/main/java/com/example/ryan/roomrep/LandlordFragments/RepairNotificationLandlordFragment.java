package com.example.ryan.roomrep.LandlordFragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepairNotificationLandlordFragment extends Fragment implements FragmentEventListener, ItemClickListener {

    LandlordRouterAction routerActionListener;

    Spinner spnNotifyHousesAddresses;
    RecyclerView rycNotificationRepairs;

    private List<Repair> repairs;
    List<String> addresses;
    List<House> houses;
    ProgressDialog progressDialog;
    String houseAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.notification_repair_fragment, container, false);
        spnNotifyHousesAddresses = view.findViewById(R.id.spnNotifyHouseAddresses);
        rycNotificationRepairs = view.findViewById(R.id.rcyNotifyRrepairs);
        rycNotificationRepairs.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(!(addresses == null)){
            ArrayAdapter<String> houseAddressesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, addresses);
            houseAddressesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnNotifyHousesAddresses.setAdapter(houseAddressesAdapter);
            spnNotifyHousesAddresses.setOnItemSelectedListener(onListHouseAddress);
            if(!(repairs == null || repairs.isEmpty())){
                //txtIsThereRepairs.setText("Repairs");
                Collections.reverse(repairs);
                RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);

                rycNotificationRepairs.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                adapter.notifyDataSetChanged();
            }else{
                //txtIsThereRepairs.setText("No Repairs");
            }
        }

        return view;
    }


    Spinner.OnItemSelectedListener onListHouseAddress = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selectedAddress = (String) adapterView.getSelectedItem();
            if (selectedAddress.equals("- Select An Address -")){
                //txtIsThereRepairs.setText("Select a House to get Repairs");
                repairs = new ArrayList<>();
                RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);

                rycNotificationRepairs.setAdapter(adapter);
                adapter.setOnItemClickListener(RepairNotificationLandlordFragment.this);
                adapter.notifyDataSetChanged();
                return;
            }else{
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Getting all Repairs...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                houseAddress = selectedAddress;
                getRepairsFromServer();
                if (repairs == null || repairs.isEmpty()){
                    //progressDialog.dismiss();
                    //txtIsThereRepairs.setText("No Repairs");
                }
                else{
                    progressDialog.dismiss();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    public void getRepairsFromServer() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getRepairs(houseAddress);
    }


    public void setRepairs(List<Repair> repairs){
        this.repairs = repairs;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
        convertHousesToAddressList(houses);
    }

    private List<String> convertHousesToAddressList(List<House> houses) {
        addresses = new ArrayList<>();
        addresses.add("- Select An Address -");
        for (House house : houses){
            addresses.add(house.getAddress());
        }
        return addresses;
    }

    public void setActionListener(LandlordRouterAction landlordRouterAction){
        this.routerActionListener = landlordRouterAction;
    }
    @Override
    public void onItemClick(View view, int position) {
        //TODO: Implement Notification sent to Tenant. When tenant creates he sends notification to landlord. But landlord needs to be able to send one.

    }

    @Override
    public void update(String response) {
        progressDialog.dismiss();
        JSONArray jsonArray;
        List<Repair> lstRepairs = new ArrayList<>();
        if (!response.equals("{'error':'Not such repairs for this house.'}")){
            try {
                jsonArray = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Repair repair = new Repair(
                            jsonObject.getString("Description"),
                            jsonObject.getString("Name"),
                            jsonObject.getString("Date"),
                            jsonObject.getString("Status"),
                            jsonObject.getString("PhotoRef"),
                            jsonObject.getString("DateUpdated"),
                            houseAddress);
                    lstRepairs.add(repair);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //routerActionListener.onSetRepairs(repairs);
            }
            repairs = lstRepairs;
            routerActionListener.onSetRepairs(repairs);
        }
        else{
            repairs = new ArrayList<>();
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (repairs == null || repairs.isEmpty()){
                    //txtIsThereRepairs.setText("No Repairs have been posted");
                }
                else{
                    //txtIsThereRepairs.setText("Repairs");
                    Collections.reverse(repairs);
                }
                RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);

                rycNotificationRepairs.setAdapter(adapter);
                adapter.setOnItemClickListener(RepairNotificationLandlordFragment.this);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
