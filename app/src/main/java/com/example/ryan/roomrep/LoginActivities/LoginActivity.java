package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Dialog.ErrorDialog;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.Login.UserType;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.Classes.Permission;
import com.example.ryan.roomrep.CompoundButtonInput.CompoundButtonInput;
import com.example.ryan.roomrep.Factories.AbstractFactory;
import com.example.ryan.roomrep.Factories.CompoundButtonFactory;
import com.example.ryan.roomrep.Factories.FactoryType;
import com.example.ryan.roomrep.Factories.TextInputFactory;
import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;
import com.example.ryan.roomrep.TextInput.UITextInputValidation;

import static com.example.ryan.roomrep.Classes.Permission.INTERNET_PERMISSION_REQUEST_CODE;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements NetworkObserver {

    Button btnLogin;
    Button btnSignup;
    Button btnListings;


    TextView txtShowError;

    TextInput email, password;
    CompoundButtonInput userType;
    Network<Login> network;
    Permission permission;
    UITextInputValidation uiTextInputValidation;

    private void initUI() {
        View view = getWindow().getDecorView().getRootView();
        TextInputFactory textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        uiTextInputValidation = new UITextInputValidation();
        email = textInputFactory.getTextInput(R.id.edtLoginEmail);
        uiTextInputValidation.addTextInput(email);
        password = textInputFactory.getTextInput(R.id.edtLoginPassword);
        uiTextInputValidation.addTextInput(password);
        CompoundButtonFactory compoundButtonFactory = (CompoundButtonFactory) AbstractFactory.getFactory(view, FactoryType.CompoundButtonInput);
        userType = compoundButtonFactory.getRadioButtonCompoundButtonInput(R.id.rdgLogin);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        btnLogin = findViewById(R.id.btnLoginLogin);
        btnLogin.setOnClickListener(onLogin);
        btnSignup = findViewById(R.id.btnLoginSignup);
        btnSignup.setOnClickListener(onSignUp);
        btnListings = findViewById(R.id.btnLoginListings);
        btnListings.setOnClickListener(onViewListings);
        network = Network.getInstance();
        network.registerObserver(LoginActivity.this);
        permission = new Permission(this);
    }


    View.OnClickListener onSignUp = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, ChooseAccountActivity.class);
            startActivity(intent);
        }
    };


    View.OnClickListener onViewListings = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);

            startActivity(intent);
        }
    };

    private Login createLogin() {
        if (userType.getText().equals("Landlord")){
            return new Login(email.getText(), password.getText(), UserType.Landlord);
        }
        return new Login(email.getText(), password.getText(), UserType.Tenant);
    }


    View.OnClickListener onLogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!uiTextInputValidation.validateUI(btnLogin)){
                return;
            }
            if (!permission.doesHaveInternetPermission()){
                permission.requestInternetPermission();
                return;
            }
            if (!network.isNetworkAvailable()) {
                ErrorDialog.buildAlertDialog(LoginActivity.this, "No internet.");
                return;
            }
            network.post(createLogin(), "auth/login");
            Intent intent = new Intent(LoginActivity.this, MainActivityLandlord.class);
            intent.putExtra("HOMEOWNER_EMAIL", email.getText());
            startActivity(intent);
        }
    };


    @Override
    public void update(String response) {


    }

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


}

