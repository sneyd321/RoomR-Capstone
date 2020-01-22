package com.example.ryan.roomrep.LandlordFragments;


import android.app.DatePickerDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ryan.roomrep.Adapters.PlacesAddressSuggestionAdapter;
import com.example.ryan.roomrep.Classes.House.Agreement;
import com.example.ryan.roomrep.Classes.House.Amenities.Amenity;
import com.example.ryan.roomrep.Classes.House.Amenities.AmenityOption;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Location;
import com.example.ryan.roomrep.Classes.House.Rent;
import com.example.ryan.roomrep.Classes.House.RentBuilder;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;
import com.example.ryan.roomrep.Classes.HouseViewModel;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class AddHouseFragment extends Fragment implements FragmentEventListener {


    public final static int MAX_RENT = 5000;

    private AutoCompleteTextView edtAddress;
    private EditText edtPostalCode;
    private EditText edtUnitName;
    private EditText edtRent;
    private EditText edtStartDate;
    private EditText edtEndDate;

    private Button btnBathroom1;
    private Button btnBathroom2;
    private Button btnBathroom3;
    private Button btnBathroom4;
    private Button btnBathroom5;
    private Button btnBathroom6;

    private Button btnBedroom1;
    private Button btnBedroom2;
    private Button btnBedroom3;
    private Button btnBedroom4;
    private Button btnBedroom5;
    private Button btnBedroom6;

    private EditText edtSize;
    private Button btnAddHouse;

    final Calendar startDate = Calendar.getInstance();
    final Calendar endDate = Calendar.getInstance();

    private PlacesClient placesClient;
    private AutocompleteSessionToken token;
    private RectangularBounds bounds;
    private Location location;
    private int bedNumber;
    private int bathNumber;
    private HouseViewModel viewModel;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);

        try {
            ApplicationInfo app = getActivity().getPackageManager().getApplicationInfo(getActivity().getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = app.metaData;
            Places.initialize(getActivity(), bundle.getString("com.google.android.libraries.places.API_KEY"));
            placesClient = Places.createClient(getActivity());
            token = AutocompleteSessionToken.newInstance();
            bounds = RectangularBounds.newInstance(new LatLng(41.66, -95.16), new LatLng(56.86, -74.34));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initHouseDetails(view);
        viewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        btnAddHouse = view.findViewById(R.id.btnAddHouseAddHouse);
        btnAddHouse.setOnClickListener(onAddHouse);
        location = new Location(getActivity());


        return view;
    }

    private void autoCompleteResults(String query) {

        List<AutocompletePrediction> autoCompleteResults = new ArrayList<>();

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationBias(bounds)
                .setCountry("ca")
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(query)
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                autoCompleteResults.add(prediction);
            }
            ArrayAdapter adapter = new PlacesAddressSuggestionAdapter(getActivity(), autoCompleteResults);
            edtAddress.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Location not found: " + apiException.getStatusCode());
            }
        });
    }



    private void initHouseDetails(View view) {
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPostalCode = view.findViewById(R.id.edtAddHousePostalCode);
        edtUnitName = view.findViewById(R.id.edtAddHouseUnitType);
        edtAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AutocompletePrediction prediction = (AutocompletePrediction) adapterView.getItemAtPosition(i);
                location.getLocationDetailsFromGeocoder(prediction.getPrimaryText(null).toString());
                edtPostalCode.setText(location.getPostalCode());
            }
        });
        edtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= edtAddress.getThreshold()){
                    autoCompleteResults(charSequence.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtStartDate = view.findViewById(R.id.edtAddHouseStartDate);
        edtEndDate = view.findViewById(R.id.edtAddHouseEndDate);
        edtRent = view.findViewById(R.id.edtAddHouseRentAmount);
        edtStartDate.setOnClickListener(onStartDate);
        edtEndDate.setOnClickListener(onEndDate);

        btnBathroom1 = view.findViewById(R.id.btnAddHouseBathroomSelection1);
        btnBathroom1.setOnClickListener(onBathroomNumberSelected);
        btnBathroom2 = view.findViewById(R.id.btnAddHouseBathroomSelection2);
        btnBathroom2.setOnClickListener(onBathroomNumberSelected);
        btnBathroom3 = view.findViewById(R.id.btnAddHouseBathroomSelection3);
        btnBathroom3.setOnClickListener(onBathroomNumberSelected);
        btnBathroom4 = view.findViewById(R.id.btnAddHouseBathroomSelection4);
        btnBathroom4.setOnClickListener(onBathroomNumberSelected);
        btnBathroom5 = view.findViewById(R.id.btnAddHouseBathroomSelection5);
        btnBathroom5.setOnClickListener(onBathroomNumberSelected);
        btnBathroom6 = view.findViewById(R.id.btnAddHouseBathroomSelection6);
        btnBathroom6.setOnClickListener(onBathroomNumberSelected);

        btnBedroom1 = view.findViewById(R.id.btnAddHouseBedroomSelection1);
        btnBedroom1.setOnClickListener(onBedroomNumberSelected);
        btnBedroom2 = view.findViewById(R.id.btnAddHouseBedroomSelection2);
        btnBedroom2.setOnClickListener(onBedroomNumberSelected);
        btnBedroom3 = view.findViewById(R.id.btnAddHouseBedroomSelection3);
        btnBedroom3.setOnClickListener(onBedroomNumberSelected);
        btnBedroom4 = view.findViewById(R.id.btnAddHouseBedroomSelection4);
        btnBedroom4.setOnClickListener(onBedroomNumberSelected);
        btnBedroom5 = view.findViewById(R.id.btnAddHouseBedroomSelection5);
        btnBedroom5.setOnClickListener(onBedroomNumberSelected);
        btnBedroom6 = view.findViewById(R.id.btnAddHouseBedroomSelection6);
        btnBedroom6.setOnClickListener(onBedroomNumberSelected);

        edtSize = view.findViewById(R.id.edtAddHouseSize);
        setButtonWidth(btnBathroom1, 10);
        setButtonWidth(btnBathroom2, 1);
        setButtonWidth(btnBathroom3, 1);
        setButtonWidth(btnBathroom4, 1);
        setButtonWidth(btnBathroom5, 1);
        setButtonWidth(btnBathroom6, 1);
        setButtonWidth(btnBedroom1, 10);
        setButtonWidth(btnBedroom2, 1);
        setButtonWidth(btnBedroom3, 1);
        setButtonWidth(btnBedroom4, 1);
        setButtonWidth(btnBedroom5, 1);
        setButtonWidth(btnBedroom6, 1);



    }

    View.OnClickListener onBathroomNumberSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setButtonWidth(btnBathroom1, 1);
            setButtonWidth(btnBathroom2, 1);
            setButtonWidth(btnBathroom3, 1);
            setButtonWidth(btnBathroom4, 1);
            setButtonWidth(btnBathroom5, 1);
            setButtonWidth(btnBathroom6, 1);

            switch (v.getId()){
                case R.id.btnAddHouseBathroomSelection1:
                    setButtonWidth(btnBathroom1, 10);
                    bathNumber = 1;
                    break;
                case R.id.btnAddHouseBathroomSelection2:
                    setButtonWidth(btnBathroom2, 10);
                    bathNumber = 2;
                    break;
                case R.id.btnAddHouseBathroomSelection3:
                    setButtonWidth(btnBathroom3, 10);
                    bathNumber = 3;
                    break;
                case R.id.btnAddHouseBathroomSelection4:
                    setButtonWidth(btnBathroom4, 10);
                    bathNumber = 4;
                    break;
                case R.id.btnAddHouseBathroomSelection5:
                    setButtonWidth(btnBathroom5, 10);
                    bathNumber = 5;
                    break;
                case R.id.btnAddHouseBathroomSelection6:
                    setButtonWidth(btnBathroom6, 10);
                    bathNumber = 6;
                    break;
            }
        }
    };

    View.OnClickListener onBedroomNumberSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setButtonWidth(btnBedroom1, 1);
            setButtonWidth(btnBedroom2, 1);
            setButtonWidth(btnBedroom3, 1);
            setButtonWidth(btnBedroom4, 1);
            setButtonWidth(btnBedroom5, 1);
            setButtonWidth(btnBedroom6, 1);

            switch (v.getId()){
                case R.id.btnAddHouseBedroomSelection1:
                    setButtonWidth(btnBedroom1, 10);
                    bedNumber = 1;
                    break;
                case R.id.btnAddHouseBedroomSelection2:
                    setButtonWidth(btnBedroom2, 10);
                    bedNumber = 2;
                    break;
                case R.id.btnAddHouseBedroomSelection3:
                    setButtonWidth(btnBedroom3, 10);
                    bedNumber = 3;
                    break;
                case R.id.btnAddHouseBedroomSelection4:
                    setButtonWidth(btnBedroom4, 10);
                    bedNumber = 4;
                    break;
                case R.id.btnAddHouseBedroomSelection5:
                    setButtonWidth(btnBedroom5, 10);
                    bedNumber = 5;
                    break;
                case R.id.btnAddHouseBedroomSelection6:
                    setButtonWidth(btnBedroom6, 10);
                    bedNumber = 6;
                    break;
            }
        }
    };


    private void setButtonWidth(Button button, int width){

        GradientDrawable gradientDrawable = (GradientDrawable) button.getBackground();

        gradientDrawable.setStroke(width, button.getTextColors());

        button.setBackground(null);

        button.setBackground(gradientDrawable);
    }

    EditText.OnClickListener onStartDate = new EditText.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(getActivity(), onStartDateListener, startDate
                    .get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                    startDate.get(Calendar.DAY_OF_MONTH)).show();

        }
    };

    EditText.OnClickListener onEndDate = new EditText.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(getActivity(), onEndDateListener, endDate
                    .get(Calendar.YEAR), endDate.get(Calendar.MONTH),
                    endDate.get(Calendar.DAY_OF_MONTH)).show();

        }
    };

    DatePickerDialog.OnDateSetListener onStartDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            startDate.set(Calendar.YEAR, year);
            startDate.set(Calendar.MONTH, month);
            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setStartDate();
        }
    };
    DatePickerDialog.OnDateSetListener onEndDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            endDate.set(Calendar.YEAR, year);
            endDate.set(Calendar.MONTH, month);
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setEndDate();
        }
    };

    private void setStartDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);

        edtStartDate.setText(sdf.format(startDate.getTime()));
    }
    private void setEndDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);

        edtEndDate.setText(sdf.format(endDate.getTime()));
    }




    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String address = edtAddress.getText().toString();
            String unitType = edtUnitName.getText().toString();
            int size = parseInt(edtSize.getText().toString());
            int rent = parseInt(edtRent.getText().toString());

            if (location.getAddress().isEmpty() || location.getCity().isEmpty() || location.getProvince().isEmpty() || location.getPostalCode().isEmpty()){
                location = location.getLocationDetailsFromGeocoder(address);
            }

            Agreement agreement = new Agreement(edtStartDate.getText().toString(), edtEndDate.getText().toString());
            RentBuilder builder = new RentBuilder();
            Rent rent1 = builder.addBaseRent(rent);


            House house = new House(location, rent1, size, bedNumber, bathNumber, false, unitType, agreement);


            boolean isValid = true;
            Map<Integer, String> validator = house.validateHouse();
            for (Map.Entry<Integer, String> entry : validator.entrySet()){
                if (!entry.getValue().isEmpty()){
                    switch (entry.getKey()){
                        case 0:
                            edtAddress.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 3:
                            edtSize.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 4:
                            edtUnitName.setError(entry.getValue());
                            isValid = false;
                            break;
                    }

                }
            }

            if (isValid){
                viewModel.insert(house);


                NavHostFragment.findNavController(AddHouseFragment.this).navigate(R.id.action_addHouseFragment_to_housesFragment);


                Network network = new Network();

                //progressDialog.show();
                network.registerObserver(AddHouseFragment.this);
                //network.uploadHouse(house);
            }

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







    @Override
    public void update(String response) {

        String result = parseResult(response);
        if (result.equals("Error: House already exists")){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    edtAddress.setError("House already exists");
                }
            });
            return;

        }


    }


    private String parseResult(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            return jsonObject.getString("Result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }





}
