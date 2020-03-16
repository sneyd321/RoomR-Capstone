package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.R;


public class ListingsFragment extends Fragment implements ItemClickListener, NetworkObserver {


    RecyclerView rcyTenantListings;
    TextView txtNoHouses;
    Button btnExitListings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listings, container, false);
        rcyTenantListings = view.findViewById(R.id.rcyTenantListings);
        txtNoHouses = view.findViewById(R.id.txtTenantListingsNoHouses);
        btnExitListings = view.findViewById(R.id.btnExitListings);
        btnExitListings.setOnClickListener(onExitListings);

        rcyTenantListings.setLayoutManager(new LinearLayoutManager(getActivity()));



        txtNoHouses.setVisibility(View.VISIBLE);
        return view;
    }

    View.OnClickListener onExitListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };






    @Override
    public void update(String response) {

    }

    @Override
    public void onItemClick(View view, int position) {
        
    }
}
