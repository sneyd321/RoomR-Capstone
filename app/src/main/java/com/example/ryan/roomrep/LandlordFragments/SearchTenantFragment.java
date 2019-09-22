package com.example.ryan.roomrep.LandlordFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.ItemClickListener;

import com.example.ryan.roomrep.Adapters.LandlordShowTeantListingAdapter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;


public class SearchTenantFragment extends Fragment implements ItemClickListener

        //implements LandlordShowTeantListingAdapter.ItemClickListener
{
    LandlordShowTeantListingAdapter adapter;
    RecyclerView searchList;
    Tenant tenant;
    ArrayList<Tenant> infListTeant = new ArrayList<>();
    LandlordRouterAction routerActionListener;
    String receivedData;
    String receivedData2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tenant, container, false);
        initMsg();

        searchList = view.findViewById(R.id.rcySearchTenants);
        searchList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LandlordShowTeantListingAdapter(getActivity(), infListTeant);
        adapter.setOnClickListener(this);
        adapter.setOnItemLongClickListener(new LandlordShowTeantListingAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(getActivity(),"long click "+infListTeant.get(position).getLastName(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(,((MainActivityTenant)getActivity()).chatRoomNameInMainActivityTenant, Toast.LENGTH_SHORT).show();
            }
        });
        searchList.setAdapter(adapter);


        receivedData = adapter.passData;
        receivedData2 = adapter.passData2;
        //Toast.makeText(getActivity(),receivedData+"GGGGGG", Toast.LENGTH_SHORT).show();





        return view;


    }


    // TODO: Rename method, update argument and hook method into UI event





    public void setRouterAction(LandlordRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
    }


    private void initMsg() {
        Tenant tenant1 = new Tenant("King", "Sun", "GGWP@GMAIL.COM", "123456", "123456", "We all good");
        infListTeant.add(tenant1);
        Tenant tenant2 = new Tenant("Jack", "Luong", "GGWP@GMAIL.COM", "123456", "123456", "We all good");
        infListTeant.add(tenant2);
        //Tenant(String firstName, String lastName, String email, String password, String password2, String bio) {
    }

    @Override
    public void onItemClick(View view, int position) {
        if (routerActionListener != null) {
            //((MainActivityLandlord)getActivity()).peopleToAdd = new Tenant(infListTeant.get(position).getFirstName(),infListTeant.get(position).getLastName(),"LOL@GMAIL.COM","123456","123456","FINALMAKE IT");
            //Tenant tenant1 = new Tenant("Ziheng", "He", "GGWP@GMAIL.COM", "123456", "123456", "We all good");
            //Toast.makeText(getActivity(),((MainActivityLandlord)getActivity()).peopleToAdd.getLastName(),Toast.LENGTH_SHORT).show();
            routerActionListener.onNaviagateToAddTenant();
        }
    }


//    @Override
//    public void onItemClick(View view, int position) {
//        //Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
//    }
}
