package com.example.ryan.roomrep.Factories;

import android.view.View;


import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.AutoCompleteTextView.AddressAutoCompleteTextInput;
import com.example.ryan.roomrep.TextInput.AutoCompleteTextView.CityAutoCompleteTextInput;
import com.example.ryan.roomrep.TextInput.NormalTextInput.PoBoxTextInput;
import com.example.ryan.roomrep.TextInput.NormalTextInput.PostalCodeTextInput;
import com.example.ryan.roomrep.TextInput.NormalTextInput.RentMadePayableTextInput;
import com.example.ryan.roomrep.TextInput.NormalTextInput.UnitNameTextInput;
import com.example.ryan.roomrep.TextInput.NormalTextInput.UnitNumberTextInput;
import com.example.ryan.roomrep.TextInput.NumberTextInput.NumberTextInput;
import com.example.ryan.roomrep.TextInput.NumberTextInput.ParkingAmountNumberTextInput;
import com.example.ryan.roomrep.TextInput.NumberTextInput.ParkingSpacesTextInput;
import com.example.ryan.roomrep.TextInput.NumberTextInput.RentAmountNumberInput;
import com.example.ryan.roomrep.TextInput.NumberTextInput.StorageAmountNumberTextInput;
import com.example.ryan.roomrep.TextInput.TextInput;

public class TextInputFactory extends AbstractFactory {



    TextInputFactory(View view) {
        super(view);

    }



    public TextInput getTextInput(int editTextResourceId) {

        switch (editTextResourceId) {
            case R.id.edtAddHouseAddress:
                return new AddressAutoCompleteTextInput(getView(), R.id.tilAddHouseAddress, editTextResourceId);
            case R.id.edtAddHouseCity:
                return new CityAutoCompleteTextInput(getView(), R.id.tilAddHouseCity, editTextResourceId);
            case R.id.edtAddHousePostalCode:
                return new PostalCodeTextInput(getView(), R.id.tilAddHousePostalCode, editTextResourceId);
            case R.id.edtAddHouseUnitType:
                return new UnitNameTextInput(getView(), R.id.tilAddHouseUnitName, editTextResourceId);
            case R.id.edtAddHouseHomeownerAddress:
                return new AddressAutoCompleteTextInput(getView(), R.id.tilAddHouseHomeOwnerAddress, editTextResourceId);
            case R.id.edtAddHouseHomeownerCity:
                return new CityAutoCompleteTextInput(getView(), R.id.tilAddHouseHomeownerCity, editTextResourceId);
            case R.id.edtAddHouseHomeownerPostalCode:
                return new PostalCodeTextInput(getView(), R.id.tilAddHouseHomeownerPostalCode, editTextResourceId);
            case R.id.edtAddHouseUnitNumber:
                return new UnitNumberTextInput(getView(), R.id.tilAddHouseUnitNumber, editTextResourceId);
            case R.id.edtAddHousePOBox:
                return new PoBoxTextInput(getView(), R.id.tilAddHousePOBox, editTextResourceId);
            case R.id.edtAddHouseRentMadePayable:
                return new RentMadePayableTextInput(getView(), R.id.tilAddHouseRentMadePayable, editTextResourceId);
            default:
                return null;
        }
    }


    public NumberTextInput getNumberTextInput(int editTextResourceId) {
        switch (editTextResourceId) {
            case R.id.edtAddHouseParkingSpaces:
                return new ParkingSpacesTextInput(getView(), R.id.tilAddHouseParkingSpaces, editTextResourceId);
            case R.id.edtAddHouseRentAmount:
                return new RentAmountNumberInput(getView(), R.id.tilAddHouseRentAmount, editTextResourceId);
            case R.id.edtAddHouseParkingAmount:
                return new ParkingAmountNumberTextInput(getView(), R.id.tilAddHouseParkingAmount, editTextResourceId);
            case R.id.edtAddHouseStorageAmount:
                return new StorageAmountNumberTextInput(getView(), R.id.tilAddHouseStorageAmount, editTextResourceId);
            default:
                return null;
        }
    }

}
