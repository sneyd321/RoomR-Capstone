package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.ryan.roomrep.Adapters.LandlordPaymentRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;

public class NotificationOptionsFragment extends Fragment implements FragmentEventListener {

    LandlordRouterAction routerActionListener;

    Button btnRepairsNotification;
    Button btnPaymentNotification;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_options, container, false);

        btnRepairsNotification = view.findViewById(R.id.btnNotifyRapairs);
        btnPaymentNotification = view.findViewById(R.id.btnNotifyPayments);

        btnRepairsNotification.setOnClickListener(onRepairNotification);
        btnPaymentNotification.setOnClickListener(onPaymentNotification);

        return view;

    }

    public void setActionListener(LandlordRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    public View.OnClickListener onRepairNotification = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            routerActionListener.onNavigateToNotifyRepairs();
        }
    };

    public View.OnClickListener onPaymentNotification = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            routerActionListener.onNavigateToNotifyR();
        }
    };

    @Override
    public void update(String response) {

    }
}
