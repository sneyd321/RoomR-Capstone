package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class TenantRepairFragment extends Fragment implements FragmentEventListener, ItemClickListener {
    Button btnAddRepair;

    RecyclerView rcyRepairsTenant;

    TenantRouterAction routerActionListener;

    TextView txtIsThereRepairs;

    private List<Repair> repairs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_tenant_repair, container, false);

        btnAddRepair = view.findViewById(R.id.btnAddRepair);

        rcyRepairsTenant = view.findViewById(R.id.rcyRepairsTenant);

        txtIsThereRepairs = view.findViewById(R.id.txtIsThereRepairs);

        rcyRepairsTenant.setLayoutManager(new LinearLayoutManager(getActivity()));

        RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);

        rcyRepairsTenant.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnAddRepair.setOnClickListener(onAddRepair);

        return view;
    }

    private View.OnClickListener onAddRepair = new View.OnClickListener(){

        @Override
        public void onClick(View view) {

            routerActionListener.onNavigateToRepairPicture();

        }
    };

    public void setActionListener(TenantRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    @Override
    public void update(String response) {

    }

    @Override
    public void onItemClick(View view, int position) {
        //i will se the repair in the other view with the routercall.
        //Repair.
        Repair repair = repairs.get(position);
        //here i will pass to the router the repair and router will set the view on the router call!!!
    }

    public void setRepairs(List<Repair> repairs){
        this.repairs = repairs;
    }
}
