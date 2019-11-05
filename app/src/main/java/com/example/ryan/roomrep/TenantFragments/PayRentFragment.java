package com.example.ryan.roomrep.TenantFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
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


    House house;
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
    private TenantRouterAction actionListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_rent, container, false);
        Utility utility = new Utility("Hydro", 150, "Weekly");
        List<Utility> utilities = new ArrayList<>();
        utilities.add(utility);
        //house = new House("3327 Raspberry Bush Trail", 600, 0, 0, 0, null, utilities, "rts1234567@hotmail.com", "", "", false, null, null);


        progressBar = view.findViewById(R.id.payRentCircularProgress);
        txtMonth = view.findViewById(R.id.txtPayRentMonthRent);
        txtMonth.setText(getCurrentMonth() + " Payment:");
        txtFinalAmount = view.findViewById(R.id.txtPayRentFinalAmount);
        txtFinalAmount.setText("$" + getFinalAmount(house));
        txtDueDate = view.findViewById(R.id.txtPayRentDueDate);
        txtDueDate.setText(getDueDate());
        txtRentPrice = view.findViewById(R.id.txtPayRentRentPrice);
        txtRentPrice.setText(formatRent(house.getRent()));
        txtHydroPrice = view.findViewById(R.id.txtPayRentHydroPrice);
        txtHydroPrice.setText("$0.00");
        txtInternetPrice = view.findViewById(R.id.txtPayRentInternetPrice);
        txtInternetPrice.setText("$0.00");
        txtCablePrice = view.findViewById(R.id.txtPayRentCablePrice);
        txtCablePrice.setText("$0.00");
        txtPhoneLine = view.findViewById(R.id.txtPayRentPhonePrice);
        txtPhoneLine.setText("$0.00");
        for (Utility util : house.getUtilities()) {
            parseUtility(util);
        }
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

    private String getFinalAmount(House house) {
        int amount = house.getRent();
        for (Utility util : house.getUtilities()) {
            amount += util.getDoubleAmount();
        }
        finalAmount = amount;
        return Integer.toString(amount);
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

    private void parseUtility(Utility utility) {
        switch (utility.getName()){
            case "Hydro":
                txtHydroPrice.setText(utility.getAmount());
                break;
            case "Internet":
                txtInternetPrice.setText(utility.getAmount());
                break;
            case "Cable":
                txtCablePrice.setText(utility.getAmount());
                break;
            case "Phone Line":
                txtPhoneLine.setText(utility.getAmount());
                break;
        }
    }

    public void setHouse(House house) {
        this.house = house;
    }


    private View.OnClickListener onPayRent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Network network = Network.getInstance();
            network.registerObserver(PayRentFragment.this);
            String tenantName = tenant.getFirstName() + " " + tenant.getLastName();
            Payment payment = new Payment(tenantName, house.getAddress(), getDueDate(), house.getLandlordEmail(), finalAmount,null);

            network.makePayment(payment);
        }
    };

    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
    }


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
