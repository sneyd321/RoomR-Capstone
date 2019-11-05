package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.LandlordPaymentRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RatingTenantFragment extends Fragment implements FragmentEventListener {
    String tenantName;
    LandlordRouterAction routerActionListener;
    RatingBar paymentSpeedBar;
    RatingBar tenantRepondingSpeedBar;
    TextView tenantNameTxt;
    Landlord landlord;
    List<Payment> payments;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ratingrenant, container, false);
        paymentSpeedBar = view.findViewById(R.id.paymentRatingBar);
        tenantRepondingSpeedBar = view.findViewById(R.id.tenantRepondingRatingBar);
        tenantNameTxt = view.findViewById(R.id.ratingTitle);

        tenantNameTxt.setText(tenantName);
        //payments.get(1).

        return view;
    }

    public void initNetwork() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getPayments(this.landlord);
    }



    public void setRouterAction(LandlordRouter routerActionListener) {
        this.routerActionListener = routerActionListener;
    }

    public void setTenantName(String tenantName) {this.tenantName = tenantName;
    }

    public void setLandlord(Landlord landlord) {this.landlord = landlord;
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
