package com.example.ryan.roomrep.LandlordFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Activities.MainActivityLandlord;
import com.example.ryan.roomrep.Adapters.ButtonClickListener;
import com.example.ryan.roomrep.Adapters.HousesRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LongClickItemListener;
import com.example.ryan.roomrep.App.App;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.ViewModels.HomeownerViewModel;
import com.example.ryan.roomrep.ViewModels.HouseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HousesFragment extends Fragment implements ItemClickListener, LongClickItemListener, ButtonClickListener  {


    FloatingActionButton btnAddHouse;
    RecyclerView rcyHouses;
    TextView txtLandlordGreeting;
    TextView txtNoHouses;


    HomeownerViewModel homeownerViewModel;
    HouseViewModel houseViewModel;

    HousesRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        txtLandlordGreeting = view.findViewById(R.id.txtHousesGreetingName);
        btnAddHouse = view.findViewById(R.id.btnHousesAddHouse);
        btnAddHouse.setOnClickListener(onAddHouse);
        rcyHouses = view.findViewById(R.id.rcyHouses);
        txtNoHouses = view.findViewById(R.id.txtHousesNoHouses);
        rcyHouses.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeownerViewModel = ViewModelProviders.of(getActivity()).get(HomeownerViewModel.class);
        adapter = new HousesRecyclerViewAdapter(getActivity(), new ArrayList<>());
        rcyHouses.setAdapter(adapter);
        Login login = ((MainActivityLandlord)getActivity()).getLogin();
        homeownerViewModel.getHomeowner(login.getEmail()).observe(getActivity(), new Observer<Homeowner>() {
            @Override
            public void onChanged(Homeowner homeowner) {
                if (homeowner != null) {
                    txtLandlordGreeting.setText(homeowner.getFirstName());
                }
            }
        });

        houseViewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        houseViewModel.getAllHouses().observe(getActivity(), new Observer<List<House>>() {
            @Override
            public void onChanged(List<House> houses) {
                if (houses.size() == 0) {
                    return;
                }
                adapter = new HousesRecyclerViewAdapter(getActivity(), houses);
                adapter.setItemClickListener(HousesFragment.this);
                adapter.setLongClickListener(HousesFragment.this);
                adapter.setButtonClickListener(HousesFragment.this);
                rcyHouses.swapAdapter(adapter, true);
            }
        });

        return view;
    }



    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavHostFragment.findNavController(HousesFragment.this).navigate(R.id.action_housesFragment_to_addHouseFragment);

        }
    };


    @Override
    public void onItemClick(View view, int position) {
        NavHostFragment.findNavController(HousesFragment.this).navigate(R.id.action_housesFragment_to_houseDetailStatePagerFragment);
        houseViewModel.setSelected(adapter.getItemAtPosition(position));
    }


    @Override
    public boolean onLongClick(View view, final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme).create();

        alertDialog.setTitle("Remove House");
        alertDialog.setMessage("Are you sure you want to remove this property?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Don't Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        return true;
    }


    @Override
    public void onButtonClick(View view, int position) {
        NavHostFragment.findNavController(HousesFragment.this).navigate(R.id.action_housesFragment_to_leaseStatePagerFragment2);
        houseViewModel.setSelected(adapter.getItemAtPosition(position));
    }
}
