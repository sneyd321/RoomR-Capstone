package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.Classes.SimpleLineChart;
import com.example.ryan.roomrep.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlottingLandlord extends Fragment {

    private SimpleLineChart mSimpleLineChart;
    LandlordRouterAction routerActionListener;
    Button showYear;
    Button showMonth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

          View view =   inflater.inflate(R.layout.fragment_slotting_landlord, container, false);
        mSimpleLineChart = view.findViewById(R.id.simpleLineChartLandlord);
        showMonth = view.findViewById(R.id.buttonMonthLandlord);
        showYear = view.findViewById(R.id.buttonYearLandlord);
        String[] xItem = {"1","2","3","4","5","6","7"};
        String[] yItem = {"10","20","30","40","50"};
        if(mSimpleLineChart == null)
            Log.e("gg","null!!!!");
        mSimpleLineChart.setXItem(xItem);
        mSimpleLineChart.setYItem(yItem);
        HashMap<Integer,Integer> pointMap = new HashMap();
        for(int i = 0;i<xItem.length;i++){
            pointMap.put(i, (int) (Math.random()*5));
        }
        mSimpleLineChart.setData(pointMap);

        showYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] xItem = {"2019","2020","2021","2022","2022","2023","2024","2025","2026"};
                String[] yItem = {"5.0","4.9","4.8","4.7","4.6","4.5","4.4","4.3","4.2","4.1","4.0","3.9","3.8","3.7","3.6","3.5","3.4","3.3","3.2","3.1","3.0","2.9","2.8","2.7","2.6","2.5","2.4","2.3","2.2","2.1","2.0","1.9","1.8","1.7","1.6","1.5","1.4","1.3","1.2","1.1","1.0"};
                if(mSimpleLineChart == null)
                    Log.e("wing","null!!!!");
                mSimpleLineChart.setXItem(xItem);
                mSimpleLineChart.setYItem(yItem);
                HashMap<Integer,Integer> pointMap = new HashMap();
                for(int i = 0;i<xItem.length;i++){
                    pointMap.put(i, (int) (Math.random()*41));
                }
                mSimpleLineChart.setData(pointMap);
            }
        });

        showMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] xItem = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
                //String[] yItem = {"5.0","4.9","4.8","4.7","4.6","4.5","4.4","4.3","4.2","4.1","4.0","3.9","3.8","3.7","3.6","3.5","3.4","3.3","3.2","3.1","3.0","2.9","2.8","2.7","2.6","2.5","2.4","2.3","2.2","2.1","2.0","1.9","1.8","1.7","1.6","1.5","1.4","1.3","1.2","1.1","1.0"};
                String[] yItem = {"5.0","","","","","4.5","","","","","4.0","","","","","3.5","","","","","3.0","","","","","2.5","","","","","2.0","","","","","1.5","","","","","1.0"};
                if(mSimpleLineChart == null)
                    Log.e("wing","null!!!!");
                mSimpleLineChart.setXItem(xItem);
                mSimpleLineChart.setYItem(yItem);
                HashMap<Integer,Integer> pointMap = new HashMap();
                for(int i = 0;i<xItem.length;i++){
                    pointMap.put(i, (int) (Math.random()*41));
                }
                mSimpleLineChart.setData(pointMap);

            }
        });




        return view;
    }

    public void setActionListener(LandlordRouter routerActionListener) {

            this.routerActionListener = routerActionListener;

    }
}
