package com.example.ryan.roomrep.App.Factories;

import android.view.View;


import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.AutoCompleteTextView.AddressAutoCompleteTextInput;
import com.example.ryan.roomrep.App.TextInput.AutoCompleteTextView.CityAutoCompleteTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.EmailTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.FirstNameTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.LastNameTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.PasswordTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.PhoneNumberTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.PoBoxTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.PostalCodeTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.RentMadePayableTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.UnitNameTextInput;
import com.example.ryan.roomrep.App.TextInput.NormalTextInput.UnitNumberTextInput;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.NumberTextInput;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.ParkingAmountNumberTextInput;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.ParkingSpacesTextInput;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.RentAmountNumberInput;
import com.example.ryan.roomrep.App.TextInput.NumberTextInput.StorageAmountNumberTextInput;
import com.example.ryan.roomrep.App.TextInput.TextInput;

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
            case R.id.edtLSUFirstName:
                return new FirstNameTextInput(getView(), R.id.tilLSUFirstName, editTextResourceId);
            case R.id.edtLSULastName:
                return new LastNameTextInput(getView(), R.id.tilLSULastName, editTextResourceId);
            case R.id.edtLSUEmail:
                return new EmailTextInput(getView(), R.id.tilLSUEmail, editTextResourceId);
            case R.id.edtLSUPhoneNumber:
                return new PhoneNumberTextInput(getView(), R.id.tilLSUPhoneNumber, editTextResourceId);
            case R.id.edtLSUPassword:
                return new PasswordTextInput(getView(), R.id.tilLSUPassword, editTextResourceId);
            case R.id.edtLoginEmail:
                return new EmailTextInput(getView(), R.id.tilLoginEmail, editTextResourceId);
            case R.id.edtLoginPassword:
                return new PasswordTextInput(getView(), R.id.tilLoginPassword, editTextResourceId);
            case R.id.edtTSUFirstName:
                return new FirstNameTextInput(getView(), R.id.tilTSUFirstName, editTextResourceId);
            case R.id.edtTSULastName:
                return new LastNameTextInput(getView(), R.id.tilTSULastName, editTextResourceId);
            case R.id.edtTSUEmail:
                return new EmailTextInput(getView(), R.id.tilTSUEmail, editTextResourceId);
            case R.id.edtTSUAddress:
                return new AddressAutoCompleteTextInput(getView(), R.id.tilTSUAddress, editTextResourceId);
            case R.id.edtTSUPassword:
                return new PasswordTextInput(getView(), R.id.tilTSUPassword, editTextResourceId);
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
            default:
                return null;
        }
    }

}
