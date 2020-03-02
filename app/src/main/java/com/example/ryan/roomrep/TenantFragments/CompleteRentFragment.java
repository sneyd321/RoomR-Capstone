package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONException;
import org.json.JSONObject;


public class CompleteRentFragment extends Fragment implements FragmentEventListener {



    CircularProgressBar circularProgressBar;
    ImageView imgCheckbox;
    TextView txtShowPaymentStatus;
    Button btnGoBack;

    ProgressDialog progressDialog;

    Tenant tenant;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_complete_rent, container, false);
        circularProgressBar = view.findViewById(R.id.completeRentCircularProgress);
        imgCheckbox = view.findViewById(R.id.imgCheckbox);
        txtShowPaymentStatus = view.findViewById(R.id.txtShowPaymentStatus);
        btnGoBack = view.findViewById(R.id.btnCompleteRentGoBack);
        btnGoBack.setOnClickListener(onGoBack);
        txtShowPaymentStatus.setText("Payment Sent");

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String firstName = sharedPref.getString("TenantFirstName", "");
        String lastName = sharedPref.getString("TenantLastName", "");
        String tenantEmail = sharedPref.getString("TenantTenantEmail", "");
        String landlordEmail = sharedPref.getString("TenantLandlordEmail", "");
        String password = sharedPref.getString("TenantPassword", "");
        String password2 = sharedPref.getString("TenantPassword2", "");

        tenant = new Tenant(firstName, lastName, tenantEmail, password, password2, landlordEmail);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getTenantHouse(tenant);


        long duration = 2000;
        circularProgressBar.setProgressWithAnimation(10, duration);

        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        fadeIn.setStartTime(duration);
        imgCheckbox.startAnimation(fadeIn);



        return view;

    }

    View.OnClickListener onGoBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    @Override
    public void update(String response) {
        progressDialog.dismiss();
        JSONObject jsonObject = convertStringToJSONObject(response);
        Gson gson = new Gson();
    }

    private JSONObject convertStringToJSONObject(String response) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
