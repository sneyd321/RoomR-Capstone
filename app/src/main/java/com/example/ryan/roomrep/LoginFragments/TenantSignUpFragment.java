package com.example.ryan.roomrep.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.example.ryan.roomrep.App.Factories.AbstractFactory;
import com.example.ryan.roomrep.App.Factories.FactoryType;
import com.example.ryan.roomrep.App.Factories.TextInputFactory;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;
import com.example.ryan.roomrep.App.UI.UITextInputValidation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TenantSignUpFragment extends Fragment {


    private TextInput firstName, lastName, email, houseAddress, password;

    private UITextInputValidation uiTextInputValidation;

    private void initUI(View view) {
        TextInputFactory textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        uiTextInputValidation = new UITextInputValidation();
        firstName = textInputFactory.getTextInput(R.id.edtTSUFirstName);
        uiTextInputValidation.addTextInput(firstName);
        lastName = textInputFactory.getTextInput(R.id.edtTSULastName);
        uiTextInputValidation.addTextInput(lastName);
        email = textInputFactory.getTextInput(R.id.edtTSUEmail);
        uiTextInputValidation.addTextInput(email);
        houseAddress = textInputFactory.getTextInput(R.id.edtTSUAddress);
        uiTextInputValidation.addTextInput(houseAddress);
        password = textInputFactory.getTextInput(R.id.edtTSUPassword);
        uiTextInputValidation.addTextInput(password);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_sign_up, container, false);
        initUI(view);

        return view;
    }


    public Tenant createTenant() {
        return new Tenant(firstName.getText(),
                lastName.getText(),
                email.getText(),
                password.getText(),
                houseAddress.getText(),
                false);
    }

    public UITextInputValidation getUiTextInputValidation() {
        return uiTextInputValidation;
    }






}
