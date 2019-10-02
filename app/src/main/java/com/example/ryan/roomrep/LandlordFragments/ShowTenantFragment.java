package com.example.ryan.roomrep.LandlordFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LandlordShowTeantListingAdapter;
import com.example.ryan.roomrep.Classes.ChatMessage;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

import static android.support.constraint.Constraints.TAG;


public class ShowTenantFragment extends Fragment implements ItemClickListener, LandlordShowTeantListingAdapter.OnItemClickListener{

    LandlordShowTeantListingAdapter adapter;
    RecyclerView tenantList;
    Tenant teant;
    LandlordRouterAction routerActionListener;
    ArrayList<Tenant> infTeant = new ArrayList<>();
    Button btnAddTeant;
    Button btnGroupChat;


    House house;
    Landlord landlord;

    @Override
    public void onStart() {
        super.onStart();

        Tenant addTenant =  ((MainActivityLandlord)getActivity()).peopleToAdd;

//        Toast.makeText(getActivity(),"long click "+addTenant.getLastName(),Toast.LENGTH_SHORT).show();


        if (addTenant !=null) {

            infTeant.add(addTenant);
            adapter.notifyDataSetChanged();

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_tenant, container, false);
        initMsg();


//        rcyTenantProfile = view.findViewById(R.id.rcyTenantProfiles);
//        rcyTenantProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new TenantProfileRecyclerviewAdapter(getActivity(), tenantProfiles);
//
//        rcyTenantProfile.setAdapter(adapter);
        btnAddTeant = view.findViewById(R.id.btnAddTenant);
        btnGroupChat = view.findViewById(R.id.groupChat);
        tenantList = view.findViewById(R.id.rcyShowTenants);
        tenantList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LandlordShowTeantListingAdapter(getActivity(), infTeant);
        adapter.setOnItemClickListener(this);
        tenantList.setAdapter(adapter);


        adapter.notifyDataSetChanged();

        btnAddTeant.setOnClickListener(onNavigateToSearchTenant);
        btnGroupChat.setOnClickListener(onNavigateToGroupChat);



        return view;

    }

    private View.OnClickListener onNavigateToSearchTenant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Search button", Toast.LENGTH_SHORT).show();
            infTeant.clear();
            if (routerActionListener != null) {
                routerActionListener.onNaviagateToSearchTenant();
            }

        }
    };

    public  View.OnClickListener onNavigateToGroupChat = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "group chat button", Toast.LENGTH_SHORT).show();
            infTeant.clear();
            String testAddress = "136 tower drive";
            ((MainActivityLandlord)getActivity()).chatRoomNameInMainActivityLandlord = house.getAddress();
            if(((MainActivityLandlord) getActivity()).chatRoomNameInMainActivityLandlord == null){
                Toast.makeText(getActivity(),"House address is empty",Toast.LENGTH_SHORT).show();
                ((MainActivityLandlord)getActivity()).chatRoomNameInMainActivityLandlord = testAddress;}
            ((MainActivityLandlord)getActivity()).chatRoomType ="PublicChatRoom";
            if (routerActionListener != null) {

                routerActionListener.onNavigateToMessagePage();
            }

        }
    };
    public void setRouterAction(LandlordRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
    }


    private void initMsg() {
        Tenant tenant1 = new Tenant("Ziheng", "He", "GGWP@GMAIL.COM", "123456", "123456", "We all good");
        infTeant.add(tenant1);
        Tenant tenant2 = new Tenant("Mexi", "Liang", "GGWP@GMAIL.COM", "123456", "123456", "We all good");
        infTeant.add(tenant2);
        //Tenant(String firstName, String lastName, String email, String password, String password2, String bio) {
    }


    @Override
    public void onItemClick(View view, int position) {
        //((MainActivityLandlord)getActivity()).peopleToAdd = new Tenant(infTeant.get(position).getFirstName(),infTeant.get(position).getLastName(),"LOL@GMAIL.COM","123456","123456","FINALMAKE IT");
        //Toast.makeText(getActivity(),((MainActivityLandlord)getActivity()).peopleToAdd.getLastName(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),infTeant.get(position).getFirstName(),Toast.LENGTH_SHORT).show();

        ((MainActivityLandlord)getActivity()).chatRoomNameInMainActivityLandlord = infTeant.get(position).getFirstName()+" "+infTeant.get(position).getLastName();
        ((MainActivityLandlord)getActivity()).chatRoomType ="OneToOne";

        //((MainActivityLandlord)getActivity()).peopleToAdd = new Tenant(infListTeant.get(position).getFirstName(),infListTeant.get(position).getLastName(),"LOL@GMAIL.COM","123456","123456","FINALMAKE IT");
        //Tenant tenant1 = new Tenant("Ziheng", "He", "GGWP@GMAIL.COM", "123456", "123456", "We all good");
        //Toast.makeText(getActivity(),infTeant.get(position).getFirstName(),Toast.LENGTH_SHORT).show();
        infTeant.clear();
        if (routerActionListener != null) {

            routerActionListener.onNavigateToMessagePage();

        }
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

}
