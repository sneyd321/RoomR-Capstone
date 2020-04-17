package com.example.ryan.roomrep.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.Classes.House.HomeownerLocation;
import com.example.ryan.roomrep.App.Factories.AbstractFactory;
import com.example.ryan.roomrep.App.Factories.FactoryType;
import com.example.ryan.roomrep.App.Factories.TextInputFactory;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;
import com.example.ryan.roomrep.App.UI.UITextInputValidation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeownerSignUpFragment extends Fragment {


    private TextInput firstName, lastName, email, phoneNumber, password, address, city, postalCode, poBox, unitNumber;

    private UITextInputValidation uiTextInputValidation;

    private void initUI(View view) {
        TextInputFactory textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        uiTextInputValidation = new UITextInputValidation();
        firstName = textInputFactory.getTextInput(R.id.edtLSUFirstName);
        uiTextInputValidation.addTextInput(firstName);
        lastName = textInputFactory.getTextInput(R.id.edtLSULastName);
        uiTextInputValidation.addTextInput(lastName);
        email = textInputFactory.getTextInput(R.id.edtLSUEmail);
        uiTextInputValidation.addTextInput(email);
        phoneNumber = textInputFactory.getTextInput(R.id.edtLSUPhoneNumber);
        uiTextInputValidation.addTextInput(phoneNumber);
        password = textInputFactory.getTextInput(R.id.edtLSUPassword);
        uiTextInputValidation.addTextInput(password);
        address = textInputFactory.getTextInput(R.id.edtAddHouseHomeownerAddress);
        uiTextInputValidation.addTextInput(address);
        city = textInputFactory.getTextInput(R.id.edtAddHouseHomeownerCity);
        uiTextInputValidation.addTextInput(city);
        postalCode = textInputFactory.getTextInput(R.id.edtAddHouseHomeownerPostalCode);
        uiTextInputValidation.addTextInput(postalCode);
        poBox = textInputFactory.getTextInput(R.id.edtAddHousePOBox);
        uiTextInputValidation.addTextInput(poBox);
        unitNumber = textInputFactory.getTextInput(R.id.edtAddHouseUnitNumber);
        uiTextInputValidation.addTextInput(unitNumber);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homeowner_sign_up, container, false);
        initUI(view);
        return view;
    }


    private HomeownerLocation createHomeownerLocation() {
        return new HomeownerLocation(address.getText(),
                city.getText(),
                "Ontario",
                postalCode.getText(),
                unitNumber.getText(),
                poBox.getText());
    }

    public Homeowner createHomeowner() {
        return new Homeowner(firstName.getText(),
                lastName.getText(),
                email.getText(),
                phoneNumber.getText(),
                password.getText(),
                createHomeownerLocation());
    }

    public UITextInputValidation getUiTextInputValidation() {
        return uiTextInputValidation;
    }



}
