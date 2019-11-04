package com.example.ryan.roomrep.TenantFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;


public class RatingLandlordFragment extends Fragment {

    String landlordName;
    TenantRouterAction tenantRouterActionListen;
    RatingBar repairSpeedBar;
    RatingBar landlordRepondingSpeedBar;
    TextView landlordNameTxt;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating_landlord, container, false);
        landlordNameTxt = view.findViewById(R.id.ratingTitleLandlord);
        repairSpeedBar = view.findViewById(R.id.repairRatingBar);
        landlordRepondingSpeedBar = view.findViewById(R.id.landlordRepondingRatingBar);
        landlordNameTxt.setText(landlordName);

        return view;
    }

    public void setLandlordName(String landlordName) {this.landlordName = landlordName;
    }

    public void setRouterAction(TenantRouter tenantRouter) {
        this.tenantRouterActionListen = tenantRouter;
    }
}
