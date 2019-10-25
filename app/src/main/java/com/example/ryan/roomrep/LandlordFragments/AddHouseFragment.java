package com.example.ryan.roomrep.LandlordFragments;


import android.app.ProgressDialog;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.UtilityRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.HouseBuilder;
import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class AddHouseFragment extends Fragment implements UtilityDialogActionListener, FragmentEventListener {


    public final static int MAX_RENT = 5000;

    EditText edtAddress;
    SeekBar skbRent;
    TextView skbOutput;
    Button btnAddUtility;
    RecyclerView rcyUtilities;
    Spinner spnBedNumber;
    Spinner spnBathNumber;
    EditText edtSize;


    Button btnAddHouse;

    LandlordRouterAction routerActionListener;

    UtilityRecyclerviewAdapter adapter;

    List<CheckBox> checkBoxList = new ArrayList<>();
    List<Utility> utilities = new ArrayList<>();
    int bedNumber = 0;
    int bathNumber = 0;

    House house;
    ProgressDialog progressDialog;
    Landlord landlord;

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
        checkBoxList.add((CheckBox) view.findViewById(R.id.chkAddHouseAmenity1));
        checkBoxList.add((CheckBox) view.findViewById(R.id.chkAddHouseAmenity2));
        checkBoxList.add((CheckBox) view.findViewById(R.id.chkAddHouseAmenity3));
        checkBoxList.add((CheckBox) view.findViewById(R.id.chkAddHouseAmenity4));
        checkBoxList.add((CheckBox) view.findViewById(R.id.chkAddHouseAmenity5));
        checkBoxList.add((CheckBox) view.findViewById(R.id.chkAddHouseAmenity6));

        btnAddHouse.setOnClickListener(onAddHouse);

        skbRent.setOnSeekBarChangeListener(onRentChange);
        skbRent.setMax(MAX_RENT);
        skbRent.setProgress(MAX_RENT/2);

        rcyUtilities.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UtilityRecyclerviewAdapter(getActivity(), utilities);
        rcyUtilities.setAdapter(adapter);

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

        return view;
    }


    private Spinner.OnItemSelectedListener onBedNumberSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            bedNumber = Integer.parseInt(item);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Spinner.OnItemSelectedListener onBathNumberSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            bathNumber = Integer.parseInt(item);
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

    private Map<String, Boolean> setAmenities(){
        Map<String, Boolean> amenities = new LinkedHashMap<>();
        for (CheckBox checkBox : this.checkBoxList){
            amenities.put(checkBox.getText().toString(), checkBox.isChecked());
        }
        return amenities;
    }

    private String validateEditText(EditText editText, String errorMessage) {
        if (editText.getText().toString().length() == 0) {
            editText.setError(errorMessage);
            return errorMessage;
        }
        return "";
    }
    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            HouseBuilder houseBuilder = new HouseBuilder();
            if (!validateEditText(edtAddress, "Please enter an address.").isEmpty()){
                return;
            }

            if (!validateEditText(edtSize, "Please enter a size.").isEmpty()) {
                return;
            }

            String address = edtAddress.getText().toString();
            int rent = parseInt(skbOutput.getText().toString());
            int size = parseInt(edtSize.getText().toString());

            house = new House(address, rent, size, bedNumber, bathNumber, setAmenities(), utilities, landlord.getEmail());
            Map<Integer, String> validator = house.getValidator();
            for (Map.Entry<Integer, String> entry : validator.entrySet()){
                if (!entry.getValue().isEmpty()){
                    Toast.makeText(getActivity(), entry.getValue(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            house = houseBuilder.addAmenities(house);
            house.getAmenityDescription();



            Network network = new Network();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Add House...");
            progressDialog.show();
            network.registerObserver(AddHouseFragment.this);
            network.uploadHouse(house);


        }
    };

    private int parseInt(String text) {
        int parsedText;
        try {
            parsedText = Integer.parseInt(text);
            return parsedText;
        }
        catch (NumberFormatException ex){
            return 0;
        }
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

    public void setRouterAction(LandlordRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }


    @Override
    public void update(String response) {
        if (routerActionListener != null) {
            JSONObject jsonObject = convertStringToJSONObject(response);
            try {
                String url = jsonObject.getString("url");
                house.setUrl(url);
                progressDialog.dismiss();
                routerActionListener.onAddHouse(house);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private JSONObject convertStringToJSONObject(String response) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }



}
