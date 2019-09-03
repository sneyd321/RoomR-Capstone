package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;

import java.math.RoundingMode;


public class PayRentFragment extends Fragment {


    Button Confirm;

    private TenantRouterAction actionListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_rent, container, false);

        Confirm = view.findViewById(R.id.confirm);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onNavigateToConfirmRent();
                }

            }
        });


        return view;
    }


    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
    }
}
