package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import okhttp3.Request;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Activities.MainActivityLandlord;
import com.example.ryan.roomrep.Classes.House.OntarioLease;
import com.example.ryan.roomrep.Classes.House.Service;
import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.App.CompoundButtonInput.AnimatedSelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.SelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.App.Dialog.Dialog;
import com.example.ryan.roomrep.Classes.House.AdditionalTerms;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.OntarioLeaseBuilder;
import com.example.ryan.roomrep.Classes.House.RentDetail;
import com.example.ryan.roomrep.Classes.House.RentalUnitLocation;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.App.Permission;
import com.example.ryan.roomrep.App.CompoundButtonInput.AnimatedCompoundButtonInput;
import com.example.ryan.roomrep.App.Factories.CompoundButtonFactory;
import com.example.ryan.roomrep.App.CompoundButtonInput.NormalCheckboxInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.TenantHomeownerSwitchInput;
import com.example.ryan.roomrep.App.Factories.AbstractFactory;
import com.example.ryan.roomrep.App.Factories.FactoryType;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.NumberTextInput;
import com.example.ryan.roomrep.App.TextInput.TextInput;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.Factories.TextInputFactory;
import com.example.ryan.roomrep.App.UI.UITextInputValidation;
import com.example.ryan.roomrep.ViewModels.HomeownerViewModel;
import com.example.ryan.roomrep.ViewModels.HouseViewModel;


public class AddHouseFragment extends Fragment implements NetworkObserver {

    private TextInput unitAddress, unitCity, unitPostalCode, unitName, rentMadePayable;
    private NumberTextInput parkingSpaces, parkingAmount, rentAmount;
    private SelectedNotSelectedSwitchInput isCondo, swtAirConditioning, swtGuestParking, swtLaundry, swtGas, swtStorage;
    private RadioButtonCompoundButtonInput rentDueDate;
    private NormalCheckboxInput chkPaymentMethod1, chkPaymentMethod3;
    private AnimatedCompoundButtonInput chkPaymentMethod2;
    private AnimatedSelectedNotSelectedSwitchInput swtParking;
    private TenantHomeownerSwitchInput swtWater, swtElectricity, swtHeat;

    private UITextInputValidation uiTextInputValidation;
    private HouseViewModel houseViewModel;
    private HomeownerViewModel homeownerViewModel;
    private House house;
    private Network network;
    private Permission permission;
    private Button btnAddHouse;
    private Homeowner homeowner;


    private void initUI(View view) {
        uiTextInputValidation = new UITextInputValidation();
        TextInputFactory textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        unitAddress = textInputFactory.getTextInput(R.id.edtAddHouseAddress);
        uiTextInputValidation.addTextInput(unitAddress);
        unitCity = textInputFactory.getTextInput(R.id.edtAddHouseCity);
        uiTextInputValidation.addTextInput(unitCity);
        unitPostalCode = textInputFactory.getTextInput(R.id.edtAddHousePostalCode);
        uiTextInputValidation.addTextInput(unitPostalCode);
        unitName = textInputFactory.getTextInput(R.id.edtAddHouseUnitType);
        uiTextInputValidation.addTextInput(unitName);
        parkingSpaces = textInputFactory.getNumberTextInput(R.id.edtAddHouseParkingSpaces);
        uiTextInputValidation.addTextInput(parkingSpaces);
        rentMadePayable = textInputFactory.getTextInput(R.id.edtAddHouseRentMadePayable);
        uiTextInputValidation.addTextInput(rentMadePayable);
        rentAmount = textInputFactory.getNumberTextInput(R.id.edtAddHouseRentAmount);
        uiTextInputValidation.addTextInput(rentAmount);
        parkingAmount = textInputFactory.getNumberTextInput(R.id.edtAddHouseParkingAmount);
        uiTextInputValidation.addTextInput(parkingAmount);


        CompoundButtonFactory compoundButtonFactory = (CompoundButtonFactory) AbstractFactory.getFactory(view, FactoryType.CompoundButtonInput);
        isCondo = compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseIsCondo);
        rentDueDate = compoundButtonFactory.getRadioButtonCompoundButtonInput(R.id.rdgAddHouseRentDueDate);
        chkPaymentMethod1 =  compoundButtonFactory.getNormalCheckboxInput(R.id.chkAddHouseRentPaymentMethod1);
        chkPaymentMethod2 =  compoundButtonFactory.getAnimatedCompoundButtonInput(R.id.chkAddHouseRentPaymentMethod2);
        chkPaymentMethod3 =  compoundButtonFactory.getNormalCheckboxInput(R.id.chkAddHouseRentPaymentMethod3);
        swtAirConditioning =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseAirConditioning);
        swtParking = compoundButtonFactory.getAnimatedSelectedNotSelectedSwitchInput(R.id.swtAddHouseParking);
        swtGas = compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseGas);
        swtGuestParking =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseGuestParking);
        swtStorage =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseStorage);
        swtLaundry =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseLaundry);
        swtWater =  compoundButtonFactory.getTenantHomeownerSwitchInput(R.id.swtAddHouseWaterUtility);
        swtElectricity =  compoundButtonFactory.getTenantHomeownerSwitchInput(R.id.swtAddHouseElectricityUtility);
        swtHeat = compoundButtonFactory.getTenantHomeownerSwitchInput(R.id.swtAddHouseHeatUtility);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);
        initUI(view);
        btnAddHouse = view.findViewById(R.id.btnAddHouseAddHouse);
        btnAddHouse.setOnClickListener(onAddHouse);
        network = Network.getInstance();
        network.registerObserver(AddHouseFragment.this);
        permission = new Permission(getActivity());
        houseViewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        homeownerViewModel = ViewModelProviders.of(getActivity()).get(HomeownerViewModel.class);
        Login login = ((MainActivityLandlord)getActivity()).getLogin();
        homeownerViewModel.getHomeowner(login.getEmail()).observe(getActivity(), new Observer<Homeowner>() {
            @Override
            public void onChanged(Homeowner homeowner) {
                AddHouseFragment.this.homeowner = homeowner;
            }
        });
        return view;
    }

    private RentalUnitLocation createRentalUnitLocation() {

        return new RentalUnitLocation(unitAddress.getText(),
                unitCity.getText(),
                "Ontario",
                unitPostalCode.getText(),
                unitName.getText(),
                isCondo.getChecked(),
                parkingSpaces.getNumber());
    }


    private RentDetail createRentDetail() {
        return new RentDetail(rentDueDate.getText(),
                rentAmount.getNumber(),
                chkPaymentMethod2.getChecked() ? rentMadePayable.getText() : "",
                chkPaymentMethod1.getChecked(),
                chkPaymentMethod2.getChecked(),
                chkPaymentMethod3.getChecked());
    }



    private OntarioLease buildLease() {
        OntarioLeaseBuilder leaseBuilder = new OntarioLeaseBuilder();
        return leaseBuilder.setRentalUnitLocation(createRentalUnitLocation())
                .setHomeownerLocation(homeowner.getHomeownerLocation())
                .setRentDetail(createRentDetail())
                .addService(new Service("Parking", parkingAmount.getNumber(), swtParking.getChecked()))
                .addService(new Service("Gas", 0, swtGas.getChecked()))
                .addService(new Service("Air Conditioning", 0, swtAirConditioning.getChecked()))
                .addService(new Service("Storage", 0, swtStorage.getChecked()))
                .addService(new Service("On-Site Laundry", 0, swtLaundry.getChecked()))
                .addService(new Service("Guest Parking", 0, swtGuestParking.getChecked()))
                .addUtility(new Utility("Hydro", swtWater.getText()))
                .addUtility(new Utility("Electricity", swtElectricity.getText()))
                .addUtility(new Utility("Heat", swtHeat.getText()))
                .build();
    }

    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!uiTextInputValidation.validateUI(btnAddHouse)){
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

            house = new House(unitAddress.getText(), buildLease());
            Request request = network.buildPostRequest(house, "House");
            network.send(request);
        }
    };

    @Override
    public void update(String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response.equals("Success")){
                    NavHostFragment.findNavController(AddHouseFragment.this).navigate(R.id.action_addHouseFragment_to_housesFragment);
                    houseViewModel.insert(house);
                    return;
                }
                Dialog dialog = new Dialog(getActivity());
                dialog.setMessage(response);
                dialog.buildErrorDialog().show();
            }
        });
    }
}
