package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Classes.Router.RouterActionListener;
import com.example.ryan.roomrep.Classes.Router.SetRouterAction;
import com.example.ryan.roomrep.R;

public class LandlordListingsFragment extends Fragment implements SetRouterAction {

    private RouterActionListener routerActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_listings, container, false);

        return view;
    }

    @Override
    public void setRouterAction(RouterActionListener routerActionListener) {
        this.routerActionListener = routerActionListener;
    }
}
