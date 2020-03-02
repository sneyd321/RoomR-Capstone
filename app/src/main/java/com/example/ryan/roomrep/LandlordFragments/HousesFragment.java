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

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LongClickItemListener;
import com.example.ryan.roomrep.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HousesFragment extends Fragment implements ItemClickListener, LongClickItemListener {


    FloatingActionButton btnAddHouse;
    RecyclerView rcyHouses;
    TextView txtLandlordGreeting;
    TextView txtNoHouses;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);



        txtLandlordGreeting = view.findViewById(R.id.txtHousesGreetingName);

        btnAddHouse = view.findViewById(R.id.btnHousesAddHouse);
        rcyHouses = view.findViewById(R.id.rcyHouses);
        txtNoHouses = view.findViewById(R.id.txtHousesNoHouses);
        rcyHouses.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnAddHouse.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
        btnAddHouse.setOnClickListener(onAddHouse);




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
    }


    @Override
    public boolean onLongClick(View view, final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

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




}
