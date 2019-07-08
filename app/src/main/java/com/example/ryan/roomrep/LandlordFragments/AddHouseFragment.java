package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Adapters.UtilityRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.Utility;
import com.example.ryan.roomrep.R;
import com.mysql.jdbc.Util;

import java.util.ArrayList;
import java.util.List;


public class AddHouseFragment extends Fragment implements UtilityDialogActionListener {

    Button btnAddUtility;
    RecyclerView rcyUtilities;
    List<Utility> utilities;
    UtilityRecyclerviewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);

        btnAddUtility = view.findViewById(R.id.btnAddHouseAddUtility);
        btnAddUtility.setOnClickListener(onAddUtility);
        rcyUtilities = view.findViewById(R.id.rcyUtilities);
        rcyUtilities.setLayoutManager(new LinearLayoutManager(getActivity()));


        Utility utility = new Utility("Hydro", 40.0, "Monthly");
        utilities = new ArrayList<>();
        utilities.add(utility);


        adapter = new UtilityRecyclerviewAdapter(getActivity(), utilities);
        rcyUtilities.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    private View.OnClickListener onAddUtility = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createDialogFragment();
        }
    };

    private void createDialogFragment(){
        UtilityDialogFragment dialogFragment = new UtilityDialogFragment();
        dialogFragment.setDialogActionListener(this);
        dialogFragment.show(getActivity().getFragmentManager(), null);
    }


    @Override
    public void setUtility(Utility utility) {
        utilities.add(utility);
        adapter.notifyDataSetChanged();
    }
}
