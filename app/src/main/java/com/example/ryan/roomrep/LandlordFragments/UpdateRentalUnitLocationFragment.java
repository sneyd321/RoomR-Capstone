package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.App.App;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.RentalUnitLocation;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.App.Permission;
import com.example.ryan.roomrep.App.CompoundButtonInput.CompoundButtonInput;
import com.example.ryan.roomrep.App.Dialog.Dialog;
import com.example.ryan.roomrep.App.Factories.AbstractFactory;
import com.example.ryan.roomrep.App.Factories.CompoundButtonFactory;
import com.example.ryan.roomrep.App.Factories.FactoryType;
import com.example.ryan.roomrep.App.Factories.TextInputFactory;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.NumberTextInput;
import com.example.ryan.roomrep.App.TextInput.TextInput;
import com.example.ryan.roomrep.App.UI.UITextInputValidation;
import com.example.ryan.roomrep.ViewModels.HouseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.Request;

public class UpdateRentalUnitLocationFragment extends Fragment implements NetworkObserver {

    TextInput address, city, postalCode, unitName;
    NumberTextInput parkingSpaces;
    CompoundButtonInput isCondo;
    UITextInputValidation uiTextInputValidation;

    Button btnUpdateLocation, btnBack;
    Permission permission;
    Network network;
    House house;
    HouseViewModel houseViewModel;

    private void initUI(View view){
        uiTextInputValidation = new UITextInputValidation();
        TextInputFactory textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        address = textInputFactory.getTextInput(R.id.edtAddHouseAddress);
        address.getEditText().setText(house.getLease().getRentalUnitLocation().getFormattedPrimaryAddress());
        uiTextInputValidation.addTextInput(address);
        city = textInputFactory.getTextInput(R.id.edtAddHouseCity);
        city.getEditText().setText(house.getLease().getRentalUnitLocation().getCity());
        uiTextInputValidation.addTextInput(city);
        postalCode = textInputFactory.getTextInput(R.id.edtAddHousePostalCode);
        postalCode.getEditText().setText(house.getLease().getRentalUnitLocation().getPostalCode());
        uiTextInputValidation.addTextInput(postalCode);
        unitName = textInputFactory.getTextInput(R.id.edtAddHouseUnitType);
        unitName.getEditText().setText(house.getLease().getRentalUnitLocation().getUnitName());
        uiTextInputValidation.addTextInput(unitName);
        parkingSpaces = textInputFactory.getNumberTextInput(R.id.edtAddHouseParkingSpaces);
        parkingSpaces.getEditText().setText(Integer.toString(house.getLease().getRentalUnitLocation().getParkingSpaces()));
        uiTextInputValidation.addTextInput(parkingSpaces);

        CompoundButtonFactory compoundButtonFactory = (CompoundButtonFactory) AbstractFactory.getFactory(view, FactoryType.CompoundButtonInput);
        isCondo = compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseIsCondo);
        isCondo.getCompoundButton().setChecked(house.getLease().getRentalUnitLocation().isCondo());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail_update_location, container, false);
        houseViewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        houseViewModel.getSelected().observe(this, new Observer<House>() {
            @Override
            public void onChanged(House house) {
                UpdateRentalUnitLocationFragment.this.house = house;
                initUI(view);
            }
        });

        btnUpdateLocation = view.findViewById(R.id.btnUpdateLocation);
        btnUpdateLocation.setOnClickListener(onUpdateLocation);
        btnBack = view.findViewById(R.id.btnUpdateLocationBack);
        btnBack.setOnClickListener(onBack);
        permission = new Permission(getActivity());
        network = Network.getInstance();
        network.registerObserver(UpdateRentalUnitLocationFragment.this);



        return view;
    }

    private RentalUnitLocation createRentalUnitLocation() {
        return new RentalUnitLocation(address.getText(),
                city.getText(),
                "Ontario",
                postalCode.getText(),
                unitName.getText(),
                isCondo.getChecked(),
                parkingSpaces.getNumber());
    }


    private View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
        }
    };


    private View.OnClickListener onUpdateLocation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!uiTextInputValidation.validateUI(btnUpdateLocation)){
                return;
            }
            if (!permission.doesHaveInternetPermission()){
                permission.requestInternetPermission();
                return;
            }
            if (!network.isNetworkAvailable(getActivity().getApplication())) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setMessage("No Internet");
                dialog.buildErrorDialog().show();
                return;
            }
            house.getLease().setRentalUnitLocation(createRentalUnitLocation());
            Request request = network.buildPutRequest(house, "House/" + house.getAddress());
            network.send(request);

        }
    };

    @Override
    public void update(String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response.isEmpty()){
                    houseViewModel.update(house);
                    getActivity().onBackPressed();
                    return;
                }
                Dialog dialog = new Dialog(getActivity());
                dialog.setMessage(response);
                dialog.buildErrorDialog().show();
            }
        });
    }
}
