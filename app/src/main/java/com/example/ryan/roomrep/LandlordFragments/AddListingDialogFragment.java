package com.example.ryan.roomrep.LandlordFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LandlordAddListingAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

public class AddListingDialogFragment extends DialogFragment implements ItemClickListener, FragmentEventListener {


    private AddListingDialogActionListener addListingDialogActionListener;
    TextView txtNoHouses;
    TextView txtClose;
    RecyclerView rcyAddresses;
    List<House> houses;
    LandlordAddListingAdapter adapter;
    House selectedHouse;



    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_add_listing_dialog, container, false);
        txtClose = view.findViewById(R.id.txtLandlordListingDialogClose);
        txtClose.setOnTouchListener(onClose);
        txtNoHouses = view.findViewById(R.id.txtLandlordListingDialogNoHouses);
        rcyAddresses = view.findViewById(R.id.rcyAddListing);
        rcyAddresses.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<House> filteredHouses = new ArrayList<>();
        for (House house : houses){
            //if is posted is false
            if (!house.isPosted()){
                filteredHouses.add(house);
            }
        }
        adapter = new LandlordAddListingAdapter(getActivity(), filteredHouses);
        adapter.setOnClickListener(this);
        if (adapter.getItemCount() != 0) {
            txtNoHouses.setVisibility(View.INVISIBLE);
            rcyAddresses.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            return view;
        }

        txtNoHouses.setVisibility(View.VISIBLE);

        return view;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }


    private View.OnTouchListener onClose = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            dismiss();
            return false;
        }
    };


    @Override
    public void onItemClick(View view, int position) {
        selectedHouse = adapter.getHouseAtPosition(position);

        Network network = Network.getInstance();
        network.registerObserver(AddListingDialogFragment.this);
        network.postListing(selectedHouse);
        if (addListingDialogActionListener != null) {
            addListingDialogActionListener.onAddListing(selectedHouse);
            getDialog().dismiss();
        }
    }



    public void setAddListingDialogActionListener(AddListingDialogActionListener addListingDialogActionListener) {
        this.addListingDialogActionListener = addListingDialogActionListener;
    }

    @Override
    public void update(String response) {

    }
}
