package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.Classes.SimpleLineChart;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




/**
 * A simple {@link Fragment} subclass.
 */
public class SlottingLandlord extends Fragment implements FragmentEventListener {

    private SimpleLineChart mSimpleLineChart;
    LandlordRouterAction routerActionListener;
    Button showYear;
    Button showMonth;
    List<Payment> payments;
    Landlord landlord;
    String tenantName;
    int dueDateMonth;
    int dueDateDay;
    int paidDateMonth;
    int paidDateDay;
    int score= 50;
    int score2 =0;
    String monthName;
    List<Integer> xnumber = new ArrayList<>();
    List<String> yname = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

          View view =   inflater.inflate(R.layout.fragment_slotting_landlord, container, false);
        mSimpleLineChart = view.findViewById(R.id.simpleLineChartLandlord);
        showMonth = view.findViewById(R.id.buttonMonthLandlord);
        showYear = view.findViewById(R.id.buttonYearLandlord);
        final String[] xItem = {"1","2","3","4","5","6","7"};
        String[] yItem = {"10","20","30","40","50"};
        if(mSimpleLineChart == null)
            Log.e("gg","null!!!!");
        mSimpleLineChart.setXItem(xItem);
        mSimpleLineChart.setYItem(yItem);
        HashMap<Integer,Integer> pointMap = new HashMap();
        for(int i = 0;i<xItem.length;i++){
            pointMap.put(i, (int) (Math.random()*5));
        }
        //mSimpleLineChart.setData(pointMap);

        showYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] xItem = {"2019"};
                String[] yItem = {"5.0","","","","","4.5","","","","","4.0","","","","","3.5","","","","","3.0","","","","","2.5","","","","","2.0","","","","","1.5","","","","","1.0"};
                if(mSimpleLineChart == null)
                    Log.e("wing","null!!!!");
                mSimpleLineChart.setXItem(xItem);
                mSimpleLineChart.setYItem(yItem);
                HashMap<Integer,Integer> pointMap = new HashMap();
                for(int i = 0;i<xItem.length;i++){
                    pointMap.put(i, score2);
                }
                mSimpleLineChart.setData(pointMap);
                Toast.makeText(getActivity(),tenantName,Toast.LENGTH_SHORT).show();
            }
        });

        showMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xnumber.clear();
                yname.clear();
                //String[] xItem = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
                //String[] yItem = {"5.0","4.9","4.8","4.7","4.6","4.5","4.4","4.3","4.2","4.1","4.0","3.9","3.8","3.7","3.6","3.5","3.4","3.3","3.2","3.1","3.0","2.9","2.8","2.7","2.6","2.5","2.4","2.3","2.2","2.1","2.0","1.9","1.8","1.7","1.6","1.5","1.4","1.3","1.2","1.1","1.0"};
                String[] yItem = {"5.0","","","","","4.5","","","","","4.0","","","","","3.5","","","","","3.0","","","","","2.5","","","","","2.0","","","","","1.5","","","","","1.0"};
                getPaymentRating();

                if(mSimpleLineChart == null)
                    Log.e("wing","null!!!!");
                String[] ynnameList = yname.toArray(new String[]{});
                mSimpleLineChart.setXItem(ynnameList);

                mSimpleLineChart.setYItem(yItem);
                HashMap<Integer,Integer> pointMap = new HashMap();
                for(int i = 0;i<ynnameList.length;i++){
                    //pointMap.put(i, (int) (Math.random()*41));
                    pointMap.put(i, xnumber.get(i));
                }
                mSimpleLineChart.setData(pointMap);

            }
        });




        return view;
    }

    public void initNetwork() {
        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getPayments(this.landlord);
    }

    public void setActionListener(LandlordRouter routerActionListener) {

            this.routerActionListener = routerActionListener;

    }

    public void setLandlord(Landlord landlord) {this.landlord = landlord;
    }

    public void setTenantName(String tenantName) {this.tenantName = tenantName;
    }

    public void formatPaymentDueDate(String date){
        String[] strSplit = date.split(" ");
        //Due on November 30
        dueDateMonth = convertMonthToNumber(strSplit[2]);
        monthName = strSplit[2];
        dueDateDay = Integer.parseInt(strSplit[3]);

    }

    public void formatDatePaid(String date){
        String[] strSplit=date.split("-");
        //2019-10-21

//        int number1 = Integer.parseInt(strSplit[0]);
        paidDateMonth = Integer.parseInt(strSplit[1]);
        paidDateDay = Integer.parseInt(strSplit[2]);

    }


    public void getPaymentRating(){
        for (int i=0; i<payments.size();i++){
            if(payments.get(i).getTenantName().equals(tenantName)) {
//            if(payments.get(i).getTenantName()== tenantName){
//                Toast.makeText(getActivity(),tenantName+" "+payments.get(i).getTenantName(),Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(getActivity(),"gg "+tenantName+" "+payments.get(i).getTenantName(),Toast.LENGTH_SHORT).show();
//            }
                formatPaymentDueDate(payments.get(i).getDueDate());
                formatDatePaid(payments.get(i).getDatePaid());
                if (dueDateDay - paidDateDay > 7) {
                    score = score + 2;
                    if (score > 50) {
                        score = 50;
                    }
                    score2 = score2-2;
                    if (score <0) {
                        score = 0;
                    }
                }
                if (dueDateDay - paidDateDay < 7 || dueDateDay - paidDateDay >= 0) {
                    score = score + 1;
                    if (score > 50) {
                        score = 50;
                    }
                    score2 = score2-1;
                    if (score2 <0) {
                        score2 = 0;
                    }


                }
                else {
                    score = score - 1;
                    score2 = score2+ 1;
                    if(score2 >41){
                        score2 = 41;
                    }
                }
                //xnumber.add(payments.get(i).getDueDate())
                xnumber.add(score2);
                yname.add(monthName);
            }
        }

    }

    public int convertMonthToNumber(String month){

        String[]months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int positon = Arrays.binarySearch(months, month);
//        for(int i=0;i<months.length; i++){
//            if(months[i].equals(month)){
//                return months.
//            }
//
//        }
            return positon+1;


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
