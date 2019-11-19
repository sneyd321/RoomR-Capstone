package com.example.ryan.roomrep.TenantFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class CompleteRentFragment extends Fragment {



    CircularProgressBar circularProgressBar;
    ImageView imgCheckbox;
    TextView txtShowPaymentStatus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_complete_rent, container, false);
        circularProgressBar = view.findViewById(R.id.completeRentCircularProgress);
        imgCheckbox = view.findViewById(R.id.imgCheckbox);
        txtShowPaymentStatus = view.findViewById(R.id.txtShowPaymentStatus);
        txtShowPaymentStatus.setText("Payment Sent");


        long duration = 2000;
        circularProgressBar.setProgressWithAnimation(10, duration);

        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        fadeIn.setStartTime(duration);
        imgCheckbox.startAnimation(fadeIn);


        return view;

    }




}
