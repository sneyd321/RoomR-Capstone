package com.example.ryan.roomrep.LandlordFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotifyRFragment extends Fragment implements NetworkObserver
{

    Spinner spnTenants;
    RecyclerView rcyPayments;




    Button btnGoBack;
    private String TAG;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifyr, container, false);

        rcyPayments = view.findViewById(R.id.rcyNotifyRPayments);
        btnGoBack = view.findViewById(R.id.btnNotifyRGoBack);
        spnTenants = view.findViewById(R.id.spnNotifyRListTenants);
        spnTenants.setOnItemSelectedListener(onListTenants);


        rcyPayments.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;

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


            List<String> tenantNames = convertTenantsToNameList(new ArrayList<>());
            if (!tenantNames.isEmpty()){
                final String[] items = tenantNames.toArray(new String[tenantNames.size()]);
                AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                b.setTitle("Send Reminder To...");
                b.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedName = items[which];
                        for (Tenant tenant : new ArrayList<Tenant>()) {
                            String name = tenant.getFirstName() + " " + tenant.getLastName();
                            if (name.equals(selectedName)){
                                Network network = Network.getInstance();
                            }
                        }
                        dialog.dismiss();

                    }

                });

                b.show();
            }
            else {

            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };



    public void initNetwork() {
        Network network = Network.getInstance();
        network.registerObserver(this);

    }





    @Override
    public void update(String response) {

        JSONArray jsonArray = convertStringToJSONArray(response);
        Gson gson = new Gson();


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

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

