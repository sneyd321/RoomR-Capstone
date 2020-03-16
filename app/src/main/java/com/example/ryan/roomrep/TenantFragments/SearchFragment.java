package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Search.Search;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class SearchFragment extends Fragment implements NetworkObserver
{



    Spinner spnProvinces;
    Spinner spnCities;
    SeekBar skbRent;
    TextView txtSkbRentOutput;
    TextView txtErrorMessage;
    TextView txtExitSearch;

    Button search;
    String provinceSelected = "";
    String citySelected = "";
    int selectedPrice = 0;
    int cityResourceStringArray;
    Map<String, Boolean> selectedAmenities;

    ArrayAdapter<String> cityAdapter;

    List<CheckBox> checkBoxList;



    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        spnProvinces = view.findViewById(R.id.spnProvinces);
        spnCities = view.findViewById(R.id.spnCities);
        skbRent = view.findViewById(R.id.skbPriceRange);
        txtSkbRentOutput = view.findViewById(R.id.txtPriceRange);
        txtErrorMessage = view.findViewById(R.id.txtSearchShowError);
        txtExitSearch = view.findViewById(R.id.txtSearchExitSearch);
        txtExitSearch.setOnTouchListener(onExitSearch);
        selectedAmenities = new LinkedHashMap<>();


        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.provinces));
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvinces.setAdapter(provinceAdapter);
        spnProvinces.setOnItemSelectedListener(onProvinceSelected);

        swapAdapters(R.array.AL_Cities);

        spnCities.setEnabled(false);

        skbRent.setOnSeekBarChangeListener(onPriceSelected);


        checkBoxList = new ArrayList<>();
        checkBoxList.add(view.findViewById(R.id.chkSearchAmenity1));
        checkBoxList.add(view.findViewById(R.id.chkSearchAmenity2));
        checkBoxList.add(view.findViewById(R.id.chkSearchAmenity3));
        checkBoxList.add(view.findViewById(R.id.chkSearchAmenity4));
        checkBoxList.add(view.findViewById(R.id.chkSearchAmenity5));
        checkBoxList.add(view.findViewById(R.id.chkSearchAmenity6));

        search = view.findViewById(R.id.btnSearchListings);
        search.setOnClickListener(onSearchListings);

        progressDialog = new ProgressDialog(getActivity());

        return view;
    }


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


    SeekBar.OnSeekBarChangeListener onPriceSelected = new SeekBar.OnSeekBarChangeListener() {
        final int INTERVAL = 50;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progress = Math.round(progress/INTERVAL) * INTERVAL;
            seekBar.setProgress(progress);

            txtSkbRentOutput.setText("$0 - " + progress);
            selectedPrice = progress;

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private View.OnClickListener onSearchListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (CheckBox checkBox : checkBoxList) {
                selectedAmenities.put(checkBox.getText().toString(), checkBox.isChecked());
            }
            Search search = new Search(provinceSelected, citySelected, selectedPrice, selectedAmenities);
            Map<Integer, String> validator = search.getValidator();

            for (Map.Entry<Integer, String> entry : validator.entrySet()){
                switch (entry.getKey()){
                    case 0:
                        if (!entry.getValue().isEmpty()){
                            txtErrorMessage.setText(entry.getValue());
                            txtErrorMessage.setVisibility(View.VISIBLE);
                            return;
                        }
                    case 1:
                        if (!entry.getValue().isEmpty()){
                            txtErrorMessage.setText(entry.getValue());
                            txtErrorMessage.setVisibility(View.VISIBLE);
                            return;
                        }
                }
            }

            Network network = Network.getInstance();
            network.registerObserver(SearchFragment.this);
            progressDialog.setMessage("Searching...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
    };

    View.OnTouchListener onExitSearch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            return false;
        }
    };




    @Override
    public void update(String response) {
        progressDialog.dismiss();

    }
}
