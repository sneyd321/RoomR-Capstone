package com.example.ryan.roomrep.TenantFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.R;

import org.json.JSONException;
import org.json.JSONObject;


public class CompleteRentFragment extends Fragment {

    TextView textViewId;
    TextView textViewStatus;
    TextView textViewAmount;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_complete_rent, container, false);

        textViewId =  view.findViewById(R.id.paymentId);
        textViewStatus=  view.findViewById(R.id.paymentStatus);
        textViewAmount = view.findViewById(R.id.paymentAmount);





        return view;

    }


    private void showDetails(JSONObject jsonDetails, int paymentAmount) throws JSONException {
        //Views

        //Showing the details from json object
        textViewId.setText(jsonDetails.getString("id"));
        textViewStatus.setText(jsonDetails.getString("state"));
        textViewAmount.setText(Integer.toString(paymentAmount) +" USD");
    }

}
