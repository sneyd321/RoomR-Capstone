package com.example.ryan.roomrep.LandlordFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.R;

public class LocationUnknownDialogFragment extends DialogFragment {



    TextView txtAddressLabel;
    Spinner spnProvinces;
    Spinner spnCities;
    Button btnUpdateHouse;

    ArrayAdapter<String> cityAdapter;



    String provinceSelected;
    String citySelected;



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
        View view = inflater.inflate(R.layout.fragment_dialog_unknown_location, container, false);

        txtAddressLabel = view.findViewById(R.id.txtLocationDialogAddress);
        spnProvinces = view.findViewById(R.id.spnLocationDialogProvinces);
        spnCities = view.findViewById(R.id.spnLocationDialogCities);
        btnUpdateHouse = view.findViewById(R.id.btnLocationDialogUpdateLocation);
        btnUpdateHouse.setOnClickListener(onUpdateHouse);

        //txtAddressLabel.setText("Please Select a location for " + house.getLocation().getAddress());

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.provinces));
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvinces.setAdapter(provinceAdapter);
        spnProvinces.setOnItemSelectedListener(onProvinceSelected);

        swapAdapters(R.array.AL_Cities);

        spnCities.setEnabled(false);

        return view;
    }


    View.OnClickListener onUpdateHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (provinceSelected != null && citySelected != null) {
                Network network = Network.getInstance();

                dismiss();
            }
        }
    };

    Spinner.OnItemSelectedListener onProvinceSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String province = (String) parent.getItemAtPosition(position);
            if (province.equals("- Select A Province -")){
                spnCities.setSelection(0);
                spnCities.setEnabled(false);
                return;
            }
            provinceSelected = province;
            switch (province) {
                case "Alberta":
                    swapAdapters(R.array.AL_Cities);
                    break;
                case "British Columbia":
                    swapAdapters(R.array.BC_Cities);
                    break;
                case "Manitoba":
                    swapAdapters(R.array.MB_Cities);
                    break;
                case "New Brunswick":
                    swapAdapters(R.array.NB_Cities);
                    break;
                case "Newfoundland and Labrador":
                    swapAdapters(R.array.NL_Cities);
                    break;
                case "Nova Scotia":
                    swapAdapters(R.array.NS_Cities);
                    break;
                case "Ontario":
                    swapAdapters(R.array.ON_cities);
                    break;
                case "Prince Edward Island":
                    swapAdapters(R.array.PE_Cities);
                    break;
                case "Saskatchewan":
                    swapAdapters(R.array.SA_Cities);
                    break;
                case "Yukon":
                    swapAdapters(R.array.YU_Cities);
                    break;
                case "Northwest Territories":
                    swapAdapters(R.array.NT_cities);
                    break;
                case "Nunavut":
                    swapAdapters(R.array.NU_Cities);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    Spinner.OnItemSelectedListener onCitySelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String city = (String) parent.getItemAtPosition(position);
            if (city.equals("- Select A City -")) {
                return;
            }
            else if (city.equals("- Select A Province")){
                return;
            }
            citySelected = city;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };



    private void swapAdapters(int arrayResource) {
        cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(arrayResource));
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCities.setAdapter(cityAdapter);
        spnCities.setEnabled(true);
        spnCities.setOnItemSelectedListener(onCitySelected);
    }


}
