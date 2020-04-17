package com.example.ryan.roomrep.LoginFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.App.Permission;
import com.example.ryan.roomrep.App.CompoundButtonInput.CompoundButtonInput;
import com.example.ryan.roomrep.App.Dialog.Dialog;
import com.example.ryan.roomrep.App.Factories.AbstractFactory;
import com.example.ryan.roomrep.App.Factories.CompoundButtonFactory;
import com.example.ryan.roomrep.App.Factories.FactoryType;
import com.example.ryan.roomrep.App.Factories.TextInputFactory;
import com.example.ryan.roomrep.Activities.MainActivityLandlord;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;
import com.example.ryan.roomrep.App.UI.UITextInputValidation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class LoginFragment extends Fragment implements NetworkObserver {



    private Button btnLogin;
    private Button btnSignup;
    private Button btnListings;



    private TextInput email, password;
    private CompoundButtonInput userType;
    private Network network;
    private Permission permission;
    private UITextInputValidation uiTextInputValidation;


    private void initUI(View view) {
        TextInputFactory textInputFactory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
        uiTextInputValidation = new UITextInputValidation();
        email = textInputFactory.getTextInput(R.id.edtLoginEmail);
        uiTextInputValidation.addTextInput(email);
        password = textInputFactory.getTextInput(R.id.edtLoginPassword);
        uiTextInputValidation.addTextInput(password);
        CompoundButtonFactory compoundButtonFactory = (CompoundButtonFactory) AbstractFactory.getFactory(view, FactoryType.CompoundButtonInput);
        userType = compoundButtonFactory.getRadioButtonCompoundButtonInput(R.id.rdgLogin);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initUI(view);
        btnLogin = view.findViewById(R.id.btnLoginLogin);
        btnLogin.setOnClickListener(onLogin);
        btnSignup = view.findViewById(R.id.btnLoginSignup);
        btnSignup.setOnClickListener(onSignUp);
        btnListings = view.findViewById(R.id.btnLoginListings);
        btnListings.setOnClickListener(onViewListings);
        network = Network.getInstance();
        permission = new Permission(getActivity());
        return view;
    }








    View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_signUpStatePagerFragment);
        }
    };


    View.OnClickListener onViewListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "TODO: Integrate with 3rd party listings.", Toast.LENGTH_SHORT);
        }
    };

    private Login getLogin() {
        switch (userType.getText()) {
            case "Homeowner":
                return new Login(email.getText(), password.getText(), "Homeowner");
            case "Tenant":
                return new Login(email.getText(), password.getText(), "Tenant");
            default:
                return null;
        }
    }


    View.OnClickListener onLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!uiTextInputValidation.validateUI(btnLogin)){
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
            network.registerObserver(LoginFragment.this);
            network.login(getLogin());
        }
    };


    @Override
    public void update(String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response.isEmpty()){
                    Intent intent = new Intent(getActivity(), MainActivityLandlord.class);
                    intent.putExtra("LOGIN", getLogin());
                    startActivity(intent);
                    return;
                }
                Dialog dialog = new Dialog(getActivity());
                dialog.setMessage(response);
                dialog.buildErrorDialog().show();
            }
        });
    }


}
