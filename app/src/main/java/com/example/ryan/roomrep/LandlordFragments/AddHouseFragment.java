package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.CompoundButtonInput.AnimatedSelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.CompoundButtonInput.SelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.Dialog.ErrorDialog;
import com.example.ryan.roomrep.Classes.Lease.AdditionalTerms;
import com.example.ryan.roomrep.Classes.Lease.House;
import com.example.ryan.roomrep.Classes.Lease.Lease;
import com.example.ryan.roomrep.Classes.Lease.OntarioLeaseBuilder;
import com.example.ryan.roomrep.Classes.Lease.Services.AirConditioningService;
import com.example.ryan.roomrep.Classes.Lease.Services.GuestParkingService;
import com.example.ryan.roomrep.Classes.Lease.Services.LaundryService;
import com.example.ryan.roomrep.Classes.Lease.Services.ParkingService;
import com.example.ryan.roomrep.Classes.Lease.RentDetail;
import com.example.ryan.roomrep.Classes.Lease.RentalUnitLocation;
import com.example.ryan.roomrep.Classes.Lease.Services.SmokingService;
import com.example.ryan.roomrep.Classes.Lease.Services.StorageService;
import com.example.ryan.roomrep.Classes.Lease.Services.TenantInsuranceService;
import com.example.ryan.roomrep.Classes.Lease.Utilities.ElectricityUtility;
import com.example.ryan.roomrep.Classes.Lease.Utilities.HeatUtility;
import com.example.ryan.roomrep.Classes.Lease.Utilities.WaterUtility;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.Classes.Permission;
import com.example.ryan.roomrep.CompoundButtonInput.AnimatedCompoundButtonInput;
import com.example.ryan.roomrep.Factories.CompoundButtonFactory;
import com.example.ryan.roomrep.CompoundButtonInput.NormalCheckboxInput;
import com.example.ryan.roomrep.CompoundButtonInput.NormalSwitchInput;
import com.example.ryan.roomrep.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.TenantHomeownerSwitchInput;
import com.example.ryan.roomrep.Factories.AbstractFactory;
import com.example.ryan.roomrep.Factories.FactoryType;
import com.example.ryan.roomrep.TextInput.NumberTextInput.NumberTextInput;
import com.example.ryan.roomrep.TextInput.TextInput;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.Factories.TextInputFactory;
import com.example.ryan.roomrep.TextInput.UITextInputValidation;
import com.example.ryan.roomrep.ViewModels.HouseViewModel;


public class AddHouseFragment extends Fragment implements NetworkObserver {

    private TextInput unitAddress, unitCity, unitPostalCode, unitName, rentMadePayable;
    private NumberTextInput parkingSpaces, parkingAmount, storageAmount, rentAmount;
    private SelectedNotSelectedSwitchInput isCondo, swtAirConditioning, swtGuestParking, swtSmoking, swtLaundry, swtTenantInsurance, additionalTerm1, additionalTerm2;
    private RadioButtonCompoundButtonInput rentDueDate;
    private NormalCheckboxInput chkPaymentMethod1, chkPaymentMethod3;
    private AnimatedCompoundButtonInput chkPaymentMethod2;
    private AnimatedSelectedNotSelectedSwitchInput swtParking, swtStorage;
    private TenantHomeownerSwitchInput swtWater, swtElectricity, swtHeat;

    private UITextInputValidation uiTextInputValidation;
    private HouseViewModel viewModel;
    private House house;
    private Network<House> network;
    private Permission permission;
    private Button btnAddHouse;


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
        storageAmount = textInputFactory.getNumberTextInput(R.id.edtAddHouseStorageAmount);
        uiTextInputValidation.addTextInput(storageAmount);

        CompoundButtonFactory compoundButtonFactory = (CompoundButtonFactory) AbstractFactory.getFactory(view, FactoryType.CompoundButtonInput);
        isCondo = compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseIsCondo);
        rentDueDate = compoundButtonFactory.getRadioButtonCompoundButtonInput(R.id.rdgAddHouseRentDueDate);
        chkPaymentMethod1 =  compoundButtonFactory.getNormalCheckboxInput(R.id.chkAddHouseRentPaymentMethod1);
        chkPaymentMethod2 =  compoundButtonFactory.getAnimatedCompoundButtonInput(R.id.chkAddHouseRentPaymentMethod2);
        chkPaymentMethod3 =  compoundButtonFactory.getNormalCheckboxInput(R.id.chkAddHouseRentPaymentMethod3);
        swtAirConditioning =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseAirConditioning);
        swtParking = compoundButtonFactory.getAnimatedSelectedNotSelectedSwitchInput(R.id.swtAddHouseParking);
        swtGuestParking =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseGuestParking);
        swtStorage =  compoundButtonFactory.getAnimatedSelectedNotSelectedSwitchInput(R.id.swtAddHouseStorage);
        swtSmoking =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseSmoking);
        swtLaundry =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseLaundry);
        swtTenantInsurance =  compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseTenantInsurance);
        swtWater =  compoundButtonFactory.getTenantHomeownerSwitchInput(R.id.swtAddHouseWaterUtility);
        swtElectricity =  compoundButtonFactory.getTenantHomeownerSwitchInput(R.id.swtAddHouseElectricityUtility);
        swtHeat = compoundButtonFactory.getTenantHomeownerSwitchInput(R.id.swtAddHouseHeatUtility);
        additionalTerm1 = compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseAdditionalTerm1);
        additionalTerm2 = compoundButtonFactory.getSelectedNotSelectedSwitchInput(R.id.swtAddHouseAdditionalTerm2);
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
        viewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
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

    private AdditionalTerms createAdditionalTerms() {
        return new AdditionalTerms(additionalTerm1.getChecked(), additionalTerm2.getChecked());
    }

    private Lease buildLease() {
        OntarioLeaseBuilder leaseBuilder = new OntarioLeaseBuilder();
        return leaseBuilder.setRentalUnitLocation(createRentalUnitLocation())
                .setHomeownerLocation(null)
                .setRentDetail(createRentDetail())
                .addService(swtAirConditioning.getChecked() ? new AirConditioningService(0) : null)
                .addService(swtParking.getChecked() ? new ParkingService(parkingAmount.getNumber()) : null)
                .addService(swtGuestParking.getChecked() ? new GuestParkingService(0) : null)
                .addService(swtStorage.getChecked() ? new StorageService(storageAmount.getNumber()) : null)
                .addService(swtSmoking.getChecked() ? new SmokingService(0) : null)
                .addService(swtLaundry.getChecked() ? new LaundryService(0) : null)
                .addService(swtTenantInsurance.getChecked() ? new TenantInsuranceService(0) : null)
                .addUtility(new WaterUtility(swtWater.getText()))
                .addUtility(new ElectricityUtility(swtElectricity.getText()))
                .addUtility(new HeatUtility(swtHeat.getText()))
                .setAdditionalTerms(createAdditionalTerms())
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
            if (!network.isNetworkAvailable()) {
                ErrorDialog.buildAlertDialog(getActivity(), "No internet.").show();
                return;
            }

            house = new House(buildLease());
            network.post(house, "api/AddHouse");
        }
    };

    @Override
    public void update(String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response.equals("House added successfully.")){
                    NavHostFragment.findNavController(AddHouseFragment.this).navigate(R.id.action_addHouseFragment_to_housesFragment);
                    return;
                }
                ErrorDialog.buildAlertDialog(getActivity(), response).show();
            }
        });
    }
}
