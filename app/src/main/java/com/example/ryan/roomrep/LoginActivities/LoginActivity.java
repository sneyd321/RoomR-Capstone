package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Login;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements FragmentEventListener {

    Button login;
    Button listings;
    TextView signup;
    EditText edtPassword;
    EditText edtUserName;
    Button btnTempTenantLogin;
    Button btnTempLandlordLogin;



    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(onLogin);
        listings = findViewById(R.id.btnViewListings);
        listings.setOnClickListener(onViewListings);
        signup = findViewById(R.id.txtSignUp);
        signup.setOnTouchListener(onSignUp);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUsername);
        btnTempTenantLogin = findViewById(R.id.button2);
        btnTempTenantLogin.setOnClickListener(onTenantLogin);
        btnTempLandlordLogin = findViewById(R.id.button5);
        btnTempLandlordLogin.setOnClickListener(onLandlordLogin);



        auth = FirebaseAuth.getInstance();




    }

    View.OnClickListener onLandlordLogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, MainActivityLandlord.class);
            startActivity(intent);
        }
    };

    View.OnClickListener onTenantLogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, MainActivityTenant.class);
            startActivity(intent);
        }
    };


    View.OnTouchListener onSignUp = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Toast.makeText(LoginActivity.this, "Sign Up", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ChooseAccountActivity.class);
            startActivity(intent);
            return false;
        }
    };

    View.OnClickListener onViewListings = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    };


    View.OnClickListener onLogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final Network network = new Network();
            network.registerObserver(LoginActivity.this);
            final Login login = new Login(edtUserName.getText().toString(), edtPassword.getText().toString());
            auth.signInWithEmailAndPassword(login.getUserName(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        network.registerObserver(LoginActivity.this);
                        network.getLandlord(login);
                        return;
                    }
                }
            });
            //network.getTenant(login);
        }
    };




    @Override
    public void update(String response) {
        Gson gson = new Gson();
        Landlord landlord = gson.fromJson(response, Landlord.class);
        Intent intent = new Intent(LoginActivity.this, MainActivityLandlord.class);
        intent.putExtra("LANDLORD_DATA", landlord);
        startActivity(intent);
    }
}

