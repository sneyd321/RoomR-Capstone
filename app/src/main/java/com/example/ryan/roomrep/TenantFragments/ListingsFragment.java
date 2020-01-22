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
import com.example.ryan.roomrep.Adapters.TenantListingsRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Router.ProfileRouterAction;
import com.example.ryan.roomrep.R;

import java.util.List;


public class ListingsFragment extends Fragment implements ItemClickListener, FragmentEventListener {


    RecyclerView rcyTenantListings;
    TextView txtNoHouses;
    Button btnExitListings;
    ProfileRouterAction profileRouterAction;
    List<House> houses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listings, container, false);
        rcyTenantListings = view.findViewById(R.id.rcyTenantListings);
        txtNoHouses = view.findViewById(R.id.txtTenantListingsNoHouses);
        btnExitListings = view.findViewById(R.id.btnExitListings);
        btnExitListings.setOnClickListener(onExitListings);

        rcyTenantListings.setLayoutManager(new LinearLayoutManager(getActivity()));

        TenantListingsRecyclerViewAdapter adapter = new TenantListingsRecyclerViewAdapter(getActivity(), houses);
        if (adapter.getItemCount() != 0){
            txtNoHouses.setVisibility(View.INVISIBLE);
            adapter.setItemClickListener(this);
            rcyTenantListings.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            return view;

        }

        txtNoHouses.setVisibility(View.VISIBLE);
        return view;
    }

    View.OnClickListener onExitListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (profileRouterAction != null){
                profileRouterAction.popBackStack();
            }
        }
    };


    public void setRouterAction(ProfileRouterAction profileRouterAction) {
        this.profileRouterAction = profileRouterAction;
    }

    public void setHouses(List<House> houses){
        this.houses = houses;
    }




    @Override
    public void update(String response) {

    }

    @Override
    public void onItemClick(View view, int position) {
        if (profileRouterAction != null)  {
            profileRouterAction.onNavigateToViewListings(houses.get(position));
        }
    }
}
