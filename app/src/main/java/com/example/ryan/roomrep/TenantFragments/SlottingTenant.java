package com.example.ryan.roomrep.TenantFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.Classes.SimpleLineChart;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


public class SlottingTenant extends Fragment {


    private SimpleLineChart mSimpleLineChart;
    private TenantRouterAction actionListener;
    Button showYear;
    Button showMonth;
    Tenant tenant;
    String landlordEmail;
    String monthName;
    int ratingNumber;
    int score = 0;
    List<String> timelist = new ArrayList<>();
    List<String> monthRatinglist = new ArrayList<>();
    List<Integer> xnumber = new ArrayList<>();
    List<String> yname = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference DocRef = FirebaseFirestore.getInstance().document("RepairRatings/rts1234567@hotmail.com/Rating/November 2019");
    //private CollectionReference Colle = FirebaseFirestore.getInstance().collection("RepairRatings/rts1234567@hotmail.com/Rating");


    @Override
    public void onStart() {
        super.onStart();
        getRatingFromDataBase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slotting_tenant, container, false);
        mSimpleLineChart = view.findViewById(R.id.simpleLineChartTenant);
        //getRatingFromDataBase();
        showMonth = view.findViewById(R.id.buttonMonthTenant);
        showYear = view.findViewById(R.id.buttonYearTenant);
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
        //mSimpleLineChart.setData(pointMap);


//        Toast.makeText(getActivity(), timelist.toString(), Toast.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), monthRatinglist.toString(), Toast.LENGTH_LONG).show();

        showYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getActivity(), timelist.toString(), Toast.LENGTH_LONG).show();
                String[] xItem = {"2017","2018","2019"};
                String[] yItem = {"5.0","","","","","4.5","","","","","4.0","","","","","3.5","","","","","3.0","","","","","2.5","","","","","2.0","","","","","1.5","","","","","1.0"};
                if(mSimpleLineChart == null)
                    Log.e("wing","null!!!!");
                mSimpleLineChart.setXItem(xItem);
                mSimpleLineChart.setYItem(yItem);
                HashMap<Integer,Integer> pointMap = new HashMap();
//                for(int i = 0;i<xnumber.size();i++){
//
//                    score  =(score+xnumber.get(i));
//
//                }
                //score =  score/monthRatinglist.size();
                pointMap.put(0,12 );
                pointMap.put(1,10 );
                pointMap.put(2,11 );
//                Toast.makeText(getActivity(), score, Toast.LENGTH_LONG).show();
                if(xnumber.size()<2||yname.size()<2){
                    Toast.makeText(getActivity(),"Not enough data to generate graph",Toast.LENGTH_SHORT).show();
                }
                else{
                    mSimpleLineChart.setData(pointMap);
                }

                score = 0;
            }
        });

        showMonth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                xnumber.clear();
                yname.clear();
                getPaymentRating();
                //Toast.makeText(getActivity(), timelist.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getActivity(), monthRatinglist.toString(), Toast.LENGTH_LONG).show();
                //String[] xItem = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
                //String[] yItem = {"5.0","4.9","4.8","4.7","4.6","4.5","4.4","4.3","4.2","4.1","4.0","3.9","3.8","3.7","3.6","3.5","3.4","3.3","3.2","3.1","3.0","2.9","2.8","2.7","2.6","2.5","2.4","2.3","2.2","2.1","2.0","1.9","1.8","1.7","1.6","1.5","1.4","1.3","1.2","1.1","1.0"};
                String[] yItem = {"5.0","","","","","4.5","","","","","4.0","","","","","3.5","","","","","3.0","","","","","2.5","","","","","2.0","","","","","1.5","","","","","1.0"};
                if(mSimpleLineChart == null)
                    Log.e("wing","null!!!!");
                String[] ynnameList = yname.toArray(new String[]{});
                mSimpleLineChart.setXItem(ynnameList);
                mSimpleLineChart.setYItem(yItem);
                HashMap<Integer,Integer> pointMap = new HashMap();
                for(int i = 0;i<ynnameList.length;i++){
                    pointMap.put(i, xnumber.get(i));
                }
                if(xnumber.size()<2||yname.size()<2){
                    Toast.makeText(getActivity(),"Not enough data to generate graph",Toast.LENGTH_SHORT).show();
                }
                else{
                    mSimpleLineChart.setData(pointMap);
                }

            }
        });


        return view;
    }
    public void getRatingFromDataBase(){
         CollectionReference Colle = FirebaseFirestore.getInstance().collection("RepairRatings/"+landlordEmail+"/Rating");
        Colle.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Log.d(TAG, document.getId() + " => " + document.getData());
                        //Toast.makeText(getActivity(), document.getData().toString(), Toast.LENGTH_LONG).show();

                        timelist.add(document.get("date").toString());
                        //Toast.makeText(getActivity(), monthRatinglist.get(1), Toast.LENGTH_LONG).show();

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Log.d(TAG, document.getId() + " => " + document.getData());
                        //Toast.makeText(getActivity(), document.getData().toString(), Toast.LENGTH_LONG).show();
                        //String rating;
                        Object rating;
                        rating =document.get("rating");
                        if(rating==null){
                            //rating =document.get("Rating").toString();
                            monthRatinglist.add(document.get("Rating").toString());
                        }else{
                            monthRatinglist.add(document.get("rating").toString());
                        }


                        //Toast.makeText(getActivity(), monthRatinglist.get(1), Toast.LENGTH_LONG).show();

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }



        });


    }
    public void formatRepairRatingMonth(String date){
        String[] strSplit = date.split(" ");
        //December 2019
        monthName = strSplit[0];


    }

    public void formatRepairRatingNumber(String rating){
        Double number = Double.parseDouble(rating);
        int number3 = 10;
        ratingNumber = (int)(number*number3);
    }

    public void getPaymentRating(){
        for (int i=0; i<timelist.size();i++){
            formatRepairRatingMonth(timelist.get(i));
            formatRepairRatingNumber(monthRatinglist.get(i));

            xnumber.add(50-ratingNumber);
            yname.add(monthName);
        }
    }

    public void setActionListener(TenantRouter actionListener) {
        this.actionListener = actionListener;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setLandlordEmail(String landlordEmail) {
        this.landlordEmail = landlordEmail;
    }
}
