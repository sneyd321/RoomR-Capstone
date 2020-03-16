package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryan.roomrep.Dialog.ErrorDialog;
import com.example.ryan.roomrep.Classes.Homeowner.Homeowner;
import com.example.ryan.roomrep.Classes.Lease.HomeownerLocation;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.Classes.Permission;
import com.example.ryan.roomrep.Factories.AbstractFactory;
import com.example.ryan.roomrep.Factories.FactoryType;
import com.example.ryan.roomrep.Factories.TextInputFactory;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;
import com.example.ryan.roomrep.TextInput.UITextInputValidation;

import static com.example.ryan.roomrep.Classes.Permission.INTERNET_PERMISSION_REQUEST_CODE;

public class LandlordSignUpActivity extends AppCompatActivity implements NetworkObserver {


    final String LANDLORD_EMAIL_TAG = "LANDLORD_EMAIL";
    final String LANDLORD_PASSWORD_TAG = "LANDLORD_PASSWORD";

    TextInput firstName, lastName, email, phoneNumber, password, address, city, postalCode, poBox, unitNumber;

    Button btnSignup;
    Button btnGoBack;

    private Network<Homeowner> network;
    private Permission permission;
    private UITextInputValidation uiTextInputValidation;


    private void initUI() {
        View view = getWindow().getDecorView().getRootView();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_sign_up);
        initUI();

        btnSignup = findViewById(R.id.btnLSUsignup);
        btnSignup.setOnClickListener(onSignUp);
        btnGoBack = findViewById(R.id.btnLSUBack);
        btnGoBack.setOnClickListener(onBack);

        network = Network.getInstance();
        network.registerObserver(LandlordSignUpActivity.this);
        permission = new Permission(this);
    }


    private HomeownerLocation createHomeownerLocation() {
        return new HomeownerLocation(address.getText(),
                city.getText(),
                "Ontario",
                postalCode.getText(),
                unitNumber.getText(),
                poBox.getText());
    }

    private Homeowner createHomeowner() {
        return new Homeowner(firstName.getText(),
                lastName.getText(),
                email.getText(),
                phoneNumber.getText(),
                password.getText(),
                createHomeownerLocation());
    }



    private View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!uiTextInputValidation.validateUI(btnSignup)){
                return;
            }
            if (!permission.doesHaveInternetPermission()){
                permission.requestInternetPermission();
                return;
            }
            if (!network.isNetworkAvailable()) {
                ErrorDialog.buildAlertDialog(LandlordSignUpActivity.this, "No internet.").show();
                return;
            }
            network.post(createHomeowner(), "api/AddTempHomeowner");
        }
    };

    private View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == INTERNET_PERMISSION_REQUEST_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Internet permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Internet permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void update(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response.equals("Temporary account create. Please check email.")){
                    Intent intent = new Intent(LandlordSignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                ErrorDialog.buildAlertDialog(LandlordSignUpActivity.this, response).show();
            }
        });
    }





}
