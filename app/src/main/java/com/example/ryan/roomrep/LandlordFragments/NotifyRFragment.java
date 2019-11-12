package com.example.ryan.roomrep.LandlordFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ryan.roomrep.Adapters.HouseRecyclerviewAdapter;
import com.example.ryan.roomrep.Adapters.LandlordPaymentRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotifyRFragment extends Fragment implements FragmentEventListener
{

    Spinner spnTenants;
    RecyclerView rcyPayments;
    Landlord landlord;

    List<Payment> payments;

    Button btnGoBack;

    List<House> houses;
    private String TAG;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifyr, container, false);

        rcyPayments = view.findViewById(R.id.rcyNotifyRPayments);
        btnGoBack = view.findViewById(R.id.btnNotifyRGoBack);
        spnTenants = view.findViewById(R.id.spnNotifyRListTenants);
        ArrayAdapter<String> tenantAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, convertHousesToAddressList(houses));
        tenantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTenants.setAdapter(tenantAdapter);
        spnTenants.setOnItemSelectedListener(onListTenants);


        rcyPayments.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (payments == null) {
            payments = new ArrayList<>();
            LandlordPaymentRecyclerviewAdapter adapter = new LandlordPaymentRecyclerviewAdapter(getActivity(), payments);
            rcyPayments.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

        return view;

    }


    private List<String> convertHousesToAddressList(List<House> houses) {
        List<String> addresses = new ArrayList<>();
        addresses.add("- Select An Address -");
        for (House house : houses){
            addresses.add(house.getAddress());
        }
        return addresses;
    }

    private House getHouseFromAddress(String address) {
        for (House house : houses) {
            if (house.getAddress().equals(address)){
                return house;
            }
        }
        return null;
    }

    private List<String> convertTenantsToNameList(List<Tenant> tenants) {
        List<String> tenantNames = new ArrayList<>();
        for (Tenant tenant : tenants){
            tenantNames.add(tenant.getFirstName() + " " + tenant.getLastName());
        }
        return tenantNames;
    }




    Spinner.OnItemSelectedListener onListTenants = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedAddress = (String) parent.getSelectedItem();
            if (selectedAddress.equals("- Select An Address -")){
                return;
            }
            final House house = getHouseFromAddress(selectedAddress);
            if (house == null){
                return;
            }
            List<String> tenantNames = convertTenantsToNameList(house.getTenants());
            if (!tenantNames.isEmpty()){
                final String[] items = tenantNames.toArray(new String[tenantNames.size()]);
                AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                b.setTitle("Send Reminder To...");
                b.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedName = items[which];
                        for (Tenant tenant : house.getTenants()) {
                            String name = tenant.getFirstName() + " " + tenant.getLastName();
                            if (name.equals(selectedName)){
                                Network network = Network.getInstance();
                                network.sendPaymentReminder(tenant);
                            }
                        }
                        dialog.dismiss();

                    }

                });

                b.show();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };



    public void initNetwork() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getPayments(this.landlord);
    }


    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public void setHouse(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public void update(String response) {
        payments = new ArrayList<>();
        JSONArray jsonArray = convertStringToJSONArray(response);
        Gson gson = new Gson();
        Iterator iterator = new JSONArrayIterator(jsonArray);
        while (iterator.hasNext()){
            Payment payment = gson.fromJson(iterator.next().toString(), Payment.class);
            payments.add(payment);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LandlordPaymentRecyclerviewAdapter adapter = new LandlordPaymentRecyclerviewAdapter(getActivity(), payments);
                rcyPayments.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private JSONArray convertStringToJSONArray(String response) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(response);
            return jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

