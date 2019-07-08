package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Classes.Router.RouterActionListener;
import com.example.ryan.roomrep.Classes.Router.SetRouterAction;
import com.example.ryan.roomrep.R;

public class HousesFragment extends Fragment implements SetRouterAction {


    Button btnAddHouse;
    RouterActionListener routerActionListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        btnAddHouse = view.findViewById(R.id.btnHousesAddHouse);
        btnAddHouse.setOnClickListener(onAddHouse);



        return view;
    }


    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            routerActionListener.onNavigateToHousesAdd();
        }
    };

    @Override
    public void setRouterAction(RouterActionListener routerActionListener) {
        if (routerActionListener != null) {
            this.routerActionListener = routerActionListener;
        }

    }
}
