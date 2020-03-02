package com.example.ryan.roomrep.LandlordFragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.ryan.roomrep.Classes.Lease.AdditionalTerms;
import com.example.ryan.roomrep.Classes.Lease.House;
import com.example.ryan.roomrep.Classes.Lease.Lease;
import com.example.ryan.roomrep.Classes.Lease.OntarioLeaseBuilder;
import com.example.ryan.roomrep.Classes.Lease.Services.AirConditioningService;
import com.example.ryan.roomrep.Classes.Lease.Services.GuestParkingService;
import com.example.ryan.roomrep.Classes.Lease.HomeOwnerLocation;
import com.example.ryan.roomrep.Classes.Lease.Services.LaundryService;
import com.example.ryan.roomrep.Classes.Lease.Services.ParkingService;
import com.example.ryan.roomrep.Classes.Lease.RentDetail;
import com.example.ryan.roomrep.Classes.Lease.RentalUnitLocation;
import com.example.ryan.roomrep.Classes.Lease.Services.Service;
import com.example.ryan.roomrep.Classes.Lease.Services.SmokingService;
import com.example.ryan.roomrep.Classes.Lease.Services.StorageService;
import com.example.ryan.roomrep.Classes.Lease.Services.TenantInsuranceService;
import com.example.ryan.roomrep.Classes.Lease.Utilities.ElectricityUtility;
import com.example.ryan.roomrep.Classes.Lease.Utilities.HeatUtility;
import com.example.ryan.roomrep.Classes.Lease.Utilities.Utility;
import com.example.ryan.roomrep.Classes.Lease.Utilities.WaterUtility;
import com.example.ryan.roomrep.Classes.Validation.InputSanitizer;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddHouseFragment extends Fragment {

    private TextInput unitAddress, unitCity, unitPostalCode, unitName, homeownerAddress, homeownerCity, homeownerPostalCode, homeownerPoBox, homeownerUnitNumber, rentMadePayable;
    private NumberTextInput parkingSpaces, parkingAmount, storageAmount, rentAmount;
    private NormalSwitchInput isCondo, swtAirConditioning, swtGuestParking, swtSmoking, swtLaundry, swtTenantInsurance, additionalTerm1, additionalTerm2;
    private RadioButtonCompoundButtonInput rentDueDate;
    private NormalCheckboxInput chkPaymentMethod1, chkPaymentMethod3;
    private AnimatedCompoundButtonInput chkPaymentMethod2, swtParking, swtStorage;
    private TenantHomeownerSwitchInput swtWater, swtElectricity, swtHeat;

    TextInputFactory textInputFactory;


    private Button btnAddHouse;


    private void initUI(View view) {
        textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        unitAddress = textInputFactory.getTextInput(R.id.edtAddHouseAddress);
        unitCity = textInputFactory.getTextInput(R.id.edtAddHouseCity);
        unitPostalCode = textInputFactory.getTextInput(R.id.edtAddHousePostalCode);
        unitName = textInputFactory.getTextInput(R.id.edtAddHouseUnitType);
        parkingSpaces = textInputFactory.getNumberTextInput(R.id.edtAddHouseParkingSpaces);
        homeownerAddress = textInputFactory.getTextInput(R.id.edtAddHouseHomeownerAddress);
        homeownerCity = textInputFactory.getTextInput(R.id.edtAddHouseHomeownerCity);
        homeownerPostalCode = textInputFactory.getTextInput(R.id.edtAddHouseHomeownerPostalCode);
        homeownerPoBox = textInputFactory.getTextInput(R.id.edtAddHousePOBox);
        homeownerUnitNumber = textInputFactory.getTextInput(R.id.edtAddHouseUnitNumber);
        rentMadePayable = textInputFactory.getTextInput(R.id.edtAddHouseRentMadePayable);
        rentAmount = textInputFactory.getNumberTextInput(R.id.edtAddHouseRentAmount);
        parkingAmount = textInputFactory.getNumberTextInput(R.id.edtAddHouseParkingAmount);
        storageAmount = textInputFactory.getNumberTextInput(R.id.edtAddHouseStorageAmount);


        CompoundButtonFactory compoundButtonFactory = (CompoundButtonFactory) AbstractFactory.getFactory(view, FactoryType.CompoundButtonInput);
        isCondo = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseIsCondo);
        rentDueDate = (RadioButtonCompoundButtonInput) compoundButtonFactory.getCompoundButtonInput(R.id.rdgAddHouseRentDueDate);
        chkPaymentMethod1 = (NormalCheckboxInput) compoundButtonFactory.getCompoundButtonInput(R.id.chkAddHouseRentPaymentMethod1);
        chkPaymentMethod2 = (AnimatedCompoundButtonInput) compoundButtonFactory.getCompoundButtonInput(R.id.chkAddHouseRentPaymentMethod2);
        chkPaymentMethod3 = (NormalCheckboxInput) compoundButtonFactory.getCompoundButtonInput(R.id.chkAddHouseRentPaymentMethod3);
        swtAirConditioning = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseAirConditioning);
        swtParking =(AnimatedCompoundButtonInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseParking);
        swtGuestParking = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseGuestParking);
        swtStorage = (AnimatedCompoundButtonInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseStorage);
        swtSmoking = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseSmoking);
        swtLaundry = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseLaundry);
        swtTenantInsurance = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseTenantInsurance);
        swtWater = (TenantHomeownerSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseWaterUtility);
        swtElectricity = (TenantHomeownerSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseElectricityUtility);
        swtHeat = (TenantHomeownerSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseHeatUtility);
        additionalTerm1 = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseAdditionalTerm1);
        additionalTerm2 = (NormalSwitchInput) compoundButtonFactory.getCompoundButtonInput(R.id.swtAddHouseAdditionalTerm2);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);
        initUI(view);
        btnAddHouse = view.findViewById(R.id.btnAddHouseAddHouse);
        btnAddHouse.setOnClickListener(onAddHouse);

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

    /*
    private HomeOwnerLocation createHomeOwnerLocation() {
        return new HomeOwnerLocation(homeownerAddress.getText(),
                homeownerCity.getText(),
                "Ontario",
                homeownerPostalCode.getText(),
                unitNumber.getText(),
                poBox.getText());
    }

    private RentDetail createRentDetail() {
        return new RentDetail(rentDueDate.getText(),
                rentAmount.getNumber(),
                rentMadePayable.getText(),
                chkPaymentMethod1.getChecked(),
                chkPaymentMethod2.getChecked(),
                chkPaymentMethod3.getChecked());
    }

    private List<Service> createServiceList() {
        List<Service> services = new ArrayList<>();
        services.add(swtAirConditioning.getChecked() ? new AirConditioningService(0) : null);
        services.add(swtParking.getChecked() ? new ParkingService(parkingAmount.getNumber()) : null);
        services.add(swtGuestParking.getChecked() ? new GuestParkingService(0) : null);
        services.add(swtStorage.getChecked() ? new StorageService(storageAmount.getNumber()) : null);
        services.add(swtSmoking.getChecked() ? new SmokingService(0) : null);
        services.add(swtLaundry.getChecked() ? new LaundryService(0) : null);
        services.add(swtTenantInsurance.getChecked() ? new TenantInsuranceService(0) : null);
        services.removeAll(Collections.singleton(null));
        return services;
    }

    private List<Utility> createUtilityList() {
        List<Utility> utilities = new ArrayList<>();
        utilities.add(swtWater.getChecked() ? new WaterUtility(swtWater.getText()) : null);
        utilities.add(swtElectricity.getChecked() ? new ElectricityUtility(swtElectricity.getText()) : null);
        utilities.add(swtHeat.getChecked() ? new HeatUtility(swtHeat.getText()) : null);
        utilities.removeAll(Collections.singleton(null));
        return utilities;
    }

    private AdditionalTerms createAdditionalTerms() {
        return new AdditionalTerms(additionalTerm1.getChecked(),
                additionalTerm2.getChecked());
    }

    private Lease buildLease() {
        OntarioLeaseBuilder leaseBuilder = new OntarioLeaseBuilder();
        return leaseBuilder.setRentalUnitLocation(createRentalUnitLocation())
                .setHomeownerLocation(createHomeOwnerLocation())
                .setRentDetail(createRentDetail())
                .setServices(createServiceList())
                .setUtilities(createUtilityList())
                .setAdditionalTerms(createAdditionalTerms())
                .build();
    }




    private boolean isTextInputValid() {
        for (TextInput textInput : requiredTextInput){
            if (textInput.getError() == null || !textInput.getError().isEmpty()) {
                return false;
            }
        }
        return true;

    }
    if (!isTextInputValid()) {
                YoYo.with(Techniques.Shake).duration(700).playOn(btnAddHouse);
                return;
            }

  */

    private View.OnClickListener onAddHouse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createRentalUnitLocation();
            //House house = new House(buildLease());

        }
    };



}
