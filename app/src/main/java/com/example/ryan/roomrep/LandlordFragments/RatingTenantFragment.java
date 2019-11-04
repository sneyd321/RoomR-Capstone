package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;


public class RatingTenantFragment extends Fragment {
    String tenantName;
    LandlordRouterAction routerActionListener;
    RatingBar paymentSpeedBar;
    RatingBar tenantRepondingSpeedBar;
    TextView tenantNameTxt;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ratingrenant, container, false);
        paymentSpeedBar = view.findViewById(R.id.paymentRatingBar);
        tenantRepondingSpeedBar = view.findViewById(R.id.tenantRepondingRatingBar);
        tenantNameTxt = view.findViewById(R.id.ratingTitle);

        tenantNameTxt.setText(tenantName);
        return view;
    }



    public void setRouterAction(LandlordRouter routerActionListener) {
        this.routerActionListener = routerActionListener;
    }

    public void setTenantName(String tenantName) {this.tenantName = tenantName;
    }
}
