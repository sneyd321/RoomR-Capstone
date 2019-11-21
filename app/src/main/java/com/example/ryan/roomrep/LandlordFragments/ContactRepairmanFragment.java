package com.example.ryan.roomrep.LandlordFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.RepairContactAdapter;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.RepairContact;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ContactRepairmanFragment extends Fragment implements FragmentEventListener, ItemClickListener {

    LandlordRouterAction routerActionListener;

    RecyclerView rcyContactRepairman;

    private List<RepairContact> repairContacts;

    TextView txtIsThereRepairmans;

    ProgressDialog progressDialog;

    Button btn_goback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_contactrepairman, container, false);

        rcyContactRepairman = view.findViewById(R.id.rcyRepairContact);

        txtIsThereRepairmans = view.findViewById(R.id.txtIsThereRepairsman);

        btn_goback = view.findViewById(R.id.btn_goBack_contactRepairman);

        rcyContactRepairman.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(!(repairContacts == null)){
            txtIsThereRepairmans.setText("Repairmans");
        }

        RepairContactAdapter adapter = new RepairContactAdapter(getActivity(), repairContacts);

        rcyContactRepairman.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Getting Repairmen Near you...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        return view;
    }


    public void setActionListener(LandlordRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    public void getRepairmansFromServer(String address, String category){
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getRepairmans(address, category);
    }


    public void setRepairContacts(List<RepairContact> repairContacts) {
        this.repairContacts = repairContacts;
    }

    @Override
    public void update(String response) {
        progressDialog.dismiss();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                String hours = "";
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONArray arr = jsonObject.getJSONArray("hours");
                for (int j = 0; j < arr.length(); j++) {
                    if (j == 0) {
                        hours = arr.get(0).toString();
                    } else {
                        hours = hours + "\n" + arr.get(j).toString();
                    }
                }
                RepairContact repairContact = new RepairContact(
                        jsonObject.getString("name"),
                        jsonObject.getString("address"),
                        jsonObject.getString("number"),
                        hours,
                        jsonObject.getString("rating"));
                repairContacts.add(repairContact);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RepairContactAdapter adapter = new RepairContactAdapter(getActivity(), repairContacts);
                rcyContactRepairman.setAdapter(adapter);
                adapter.setOnItemClickListener(ContactRepairmanFragment.this);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        RepairContact repairContact = repairContacts.get(position);
        String phoneNumber = repairContact.getPhoneNumber();
    }
}
