package com.example.ryan.roomrep.LandlordFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RepairHistoryLandlordFragment extends Fragment implements FragmentEventListener, ItemClickListener {

    LandlordRouterAction routerActionListener;

    RecyclerView rcyRepairsLandlord;

    TextView txtIsThereRepairs;

    private List<Repair> repairs;

    List<House> houses;

    Spinner spn_houseAddresses;

    ProgressDialog progressDialog;

    String houseAddress;

    List<String> addresses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_history_landlord, container, false);


        rcyRepairsLandlord = view.findViewById(R.id.rcyRepairsLandlord);

        txtIsThereRepairs = view.findViewById(R.id.txtIsThereRepairsLandlord);

        spn_houseAddresses = view.findViewById(R.id.spnhouseAddresses);

        rcyRepairsLandlord.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(!(addresses == null)){
            ArrayAdapter<String> houseAddressesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, addresses);
            houseAddressesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_houseAddresses.setAdapter(houseAddressesAdapter);
            spn_houseAddresses.setOnItemSelectedListener(onListHouseAddress);
        }
        else{
            txtIsThereRepairs.setText("No Houses Detected, So no repairs");
        }

        if(!(repairs == null || repairs.isEmpty())){
            txtIsThereRepairs.setText("Repairs");
            RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);
            //progressDialog = new ProgressDialog(getActivity());
            //progressDialog.setMessage("Getting all Repairs...");
            //progressDialog.show();

            rcyRepairsLandlord.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
            adapter.notifyDataSetChanged();
        }else{
            txtIsThereRepairs.setText("No Repairs");
        }


        return view;
    }

    Spinner.OnItemSelectedListener onListHouseAddress = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selectedAddress = (String) adapterView.getSelectedItem();
            if (selectedAddress.equals("- Select An Address -")){
                return;
            }else{
                houseAddress = selectedAddress;
                getRepairsFromServer();
                if (repairs == null || repairs.isEmpty()){
                    txtIsThereRepairs.setText("No Repairs");
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

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

    public void setActionListener(LandlordRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    public void getRepairsFromServer() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getRepairs(houseAddress);
    }


    @Override
    public void onItemClick(View view, int position) {
        //i will se the repair in the other view with the routercall.
        //Repair.
        Repair repair = repairs.get(position);
        routerActionListener.onNavigateToLandlordRepairView(repair, position);
        //here i will pass to the router the repair and router will set the view on the router call!!!
    }

    @Override
    public void update(String response) {
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
                    txtIsThereRepairs.setText("No Repairs have been posted");
                }
                else{
                    txtIsThereRepairs.setText("Repairs");
                }
                RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);

                rcyRepairsLandlord.setAdapter(adapter);
                adapter.setOnItemClickListener(RepairHistoryLandlordFragment.this);
                adapter.notifyDataSetChanged();
            }
        });
        //progressDialog.dismiss();
    }
}
