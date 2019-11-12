package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

          View view =   inflater.inflate(R.layout.fragment_slotting_landlord, container, false);
        mSimpleLineChart = view.findViewById(R.id.simpleLineChartLandlord);
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

        return view;
    }

    public void setActionListener(LandlordRouter routerActionListener) {

            this.routerActionListener = routerActionListener;

    }
}
