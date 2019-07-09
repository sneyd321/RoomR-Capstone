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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.UtilityRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.Classes.Router.RouterActionListener;
import com.example.ryan.roomrep.Classes.Router.SetRouterAction;
import com.example.ryan.roomrep.R;




public class AddHouseFragment extends Fragment implements UtilityDialogActionListener, SetRouterAction {


    private final static int MAX_RENT = 5000;

    EditText edtAddress;
    SeekBar skbRent;
    TextView skbOutput;
    Button btnAddUtility;
    RecyclerView rcyUtilities;
    Spinner spnBedNumber;
    Spinner spnBathNumber;
    EditText edtSize;
    Button btnAddHouse;

    RouterActionListener routerActionListener;

    UtilityRecyclerviewAdapter adapter;

    House house;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);

        edtAddress = view.findViewById(R.id.edtAddHouseAddress);
        skbRent = view.findViewById(R.id.skbAddHouseRent);
        skbOutput = view.findViewById(R.id.txtAddHouseSkbOutput);
        btnAddUtility = view.findViewById(R.id.btnAddHouseAddUtility);
        btnAddUtility.setOnClickListener(onAddUtility);
        rcyUtilities = view.findViewById(R.id.rcyUtilities);
        spnBedNumber = view.findViewById(R.id.spnNumberOfBedrooms);
        spnBathNumber = view.findViewById(R.id.spnNumberOfBathrooms);
        edtSize = view.findViewById(R.id.edtAddHouseSize);
        btnAddHouse = view.findViewById(R.id.btnAddHouseAddHouse);
        btnAddHouse.setOnClickListener(onAddHouse);

        skbRent.setOnSeekBarChangeListener(onRentChange);
        skbRent.setMax(MAX_RENT);
        skbRent.setProgress(MAX_RENT/2);

        rcyUtilities.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayAdapter<CharSequence> bedAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.bedNumbers, android.R.layout.simple_spinner_item);
        bedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> bathAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.bathNumbers, android.R.layout.simple_spinner_item);
        bathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spnBedNumber.setAdapter(bedAdapter);
        spnBedNumber.setOnItemSelectedListener(onBedNumberSelected);
        spnBathNumber.setAdapter(bathAdapter);
        spnBathNumber.setOnItemSelectedListener(onBathNumberSelected);

        Utility utility = new Utility("Hydro", 40.0, "Monthly");
        house = new House();
        house.add(utility);

        adapter = new UtilityRecyclerviewAdapter(getActivity(), house.getUtilities());
        rcyUtilities.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }


    private Spinner.OnItemSelectedListener onBedNumberSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            house.setBedNumber(Integer.parseInt(item));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Spinner.OnItemSelectedListener onBathNumberSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            house.setBathNumber(Integer.parseInt(item));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private SeekBar.OnSeekBarChangeListener onRentChange = new SeekBar.OnSeekBarChangeListener() {
        final int INTERVAL = 50;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progress = ((int) Math.round(progress/INTERVAL)) * INTERVAL;
            seekBar.setProgress(progress);

            skbOutput.setText(Integer.toString(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            house.setAddress(edtAddress.getText().toString());
            house.setRent(Integer.parseInt(skbOutput.getText().toString()));
            house.setSize(Integer.parseInt(edtSize.getText().toString()));

            if (routerActionListener != null){
                routerActionListener.onNavigateToHouses(house);
            }

        }
    };


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
        house.add(utility);
        UtilityRecyclerviewAdapter adapter = new UtilityRecyclerviewAdapter(getActivity(), house.getUtilities());
        rcyUtilities.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setRouterAction(RouterActionListener routerActionListener) {
        this.routerActionListener = routerActionListener;
    }
}
