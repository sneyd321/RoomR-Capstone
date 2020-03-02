package com.example.ryan.roomrep.TenantFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;

import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PayRentFragment extends Fragment implements FragmentEventListener {


    TextView txtMonth;
    TextView txtFinalAmount;
    TextView txtDueDate;
    TextView txtRentPrice;
    TextView txtHydroPrice;
    TextView txtInternetPrice;
    TextView txtCablePrice;
    TextView txtPhoneLine;
    CircularProgressBar progressBar;

    int finalAmount = 0;
    Button btnPayRent;


    private Tenant tenant;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_rent, container, false);
        //Utility utility = new Utility("Hydro", 150, "Weekly");
        //utilities.add(utility);
        //house = new House("3327 Raspberry Bush Trail", 600, 0, 0, 0, null, utilities, "rts1234567@hotmail.com", "", "", false, null, null);


        progressBar = view.findViewById(R.id.payRentCircularProgress);
        txtMonth = view.findViewById(R.id.txtPayRentMonthRent);
        txtMonth.setText(getCurrentMonth() + " Payment:");
        txtFinalAmount = view.findViewById(R.id.txtPayRentFinalAmount);
        txtDueDate = view.findViewById(R.id.txtPayRentDueDate);
        txtDueDate.setText(getDueDate());
        txtRentPrice = view.findViewById(R.id.txtPayRentRentPrice);
        //txtRentPrice.setText(formatRent(house.getRent().getBaseRent()));
        txtHydroPrice = view.findViewById(R.id.txtPayRentHydroPrice);
        txtHydroPrice.setText("$0.00");
        txtInternetPrice = view.findViewById(R.id.txtPayRentInternetPrice);
        txtInternetPrice.setText("$0.00");
        txtCablePrice = view.findViewById(R.id.txtPayRentCablePrice);
        txtCablePrice.setText("$0.00");
        txtPhoneLine = view.findViewById(R.id.txtPayRentPhonePrice);
        txtPhoneLine.setText("$0.00");

        getProgress();
        btnPayRent = view.findViewById(R.id.btnPayRentPay);
        btnPayRent.setOnClickListener(onPayRent);


        return view;
    }




    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    private void getProgress() {
        Calendar calendar = Calendar.getInstance();
        int value = calendar.get(Calendar.MONTH);
        long duration = 2000;
        progressBar.setProgressWithAnimation(value, duration);
    }




    private String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CANADA);
    }

    private String formatRent(int rent) {
        String formmettedString = Integer.toString(rent);
        return "$" + formmettedString + ".00";
    }

    private String getDueDate() {
        Calendar cal = Calendar.getInstance();
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        String date = getCurrentMonth() + " " + lastDay;
        return "Due on " + date;

    }



    private View.OnClickListener onPayRent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Network network = Network.getInstance();
            network.registerObserver(PayRentFragment.this);
            String tenantName = tenant.getFirstName() + " " + tenant.getLastName();

        }
    };




    @Override
    public void update(String response) {



        try {
            JSONObject jsonObject = new JSONObject(response);
            final String result = jsonObject.get("response_url").toString();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
            startActivity(browserIntent);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
