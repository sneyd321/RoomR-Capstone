package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.HouseRecyclerviewAdapter;
import com.example.ryan.roomrep.Adapters.LandlordPaymentRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotifyRFragment extends Fragment implements FragmentEventListener
{


    RecyclerView rcyPayments;
    Landlord landlord;

    List<Payment> payments;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifyr, container, false);

        rcyPayments = view.findViewById(R.id.rcyNotifyRPayments);

        rcyPayments.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (payments == null) {
            payments = new ArrayList<>();
            LandlordPaymentRecyclerviewAdapter adapter = new LandlordPaymentRecyclerviewAdapter(getActivity(),payments);
            rcyPayments.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }





        return view;

    }


    public void initNetwork() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getPayments(this.landlord);
    }


    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
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
                LandlordPaymentRecyclerviewAdapter adapter = new LandlordPaymentRecyclerviewAdapter(getActivity(),payments);
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

