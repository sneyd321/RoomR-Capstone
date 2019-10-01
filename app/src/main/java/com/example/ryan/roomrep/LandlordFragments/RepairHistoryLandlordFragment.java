package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class RepairHistoryLandlordFragment extends Fragment implements FragmentEventListener, ItemClickListener {

    LandlordRouterAction routerActionListener;

    RecyclerView rcyRepairsLandlord;

    TextView txtIsThereRepairs;

    private List<Repair> repairs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_history_landlord, container, false);


        rcyRepairsLandlord = view.findViewById(R.id.rcyRepairsLandlord);

        txtIsThereRepairs = view.findViewById(R.id.txtIsThereRepairsLandlord);

        rcyRepairsLandlord.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(!(repairs == null)){
            txtIsThereRepairs.setText("Repairs");
        }

        RepairRecyclerViewAdapter adapter = new RepairRecyclerViewAdapter(getActivity(), repairs);

        rcyRepairsLandlord.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();


        return view;
    }

    public void setRepairs(List<Repair> repairs){
        this.repairs = repairs;
    }


    public void setActionListener(LandlordRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    @Override
    public void onItemClick(View view, int position) {
        //i will se the repair in the other view with the routercall.
        //Repair.
        Repair repair = repairs.get(position);
        routerActionListener.onNavigateToLandlordRepairView(repair, position);
        //here i will pass to the router the repair and router will set the view on the router call!!!
    }

    @Override
    public void update(String response) {

    }
}
