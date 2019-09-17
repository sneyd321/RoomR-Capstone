package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;


public class SearchFragment extends Fragment {


    Button search;

    private TenantRouterAction actionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = view.findViewById(R.id.btnSearchListings);
        search.setOnClickListener(onSearchListings);
        return view;
    }

    private View.OnClickListener onSearchListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (actionListener != null) {
                actionListener.onNavigateToListings();
            }

        }
    };


    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
    }
}
