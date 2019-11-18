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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Login;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
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
    RadioButton rbtnLandlord;
    RadioButton rbtnTenant;



    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(onLogin);
        listings = findViewById(R.id.btnViewListings);
        listings.setOnClickListener(onViewListings);
        rbtnLandlord = findViewById(R.id.rbtnLandlord);
        rbtnTenant = findViewById(R.id.rbtnTenant);
        signup = findViewById(R.id.txtSignUp);
        signup.setOnTouchListener(onSignUp);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUsername);

        auth = FirebaseAuth.getInstance();
    }



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
            if (rbtnLandlord.isChecked()){
                auth.signInWithEmailAndPassword(login.getUserName(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            network.getLandlord(login);
                        }
                    }
                });
            }
            else if(rbtnTenant.isChecked()){
                auth.signInWithEmailAndPassword(login.getUserName(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            network.getTenant(login);
                        }
                    }
                });
            }
        }
    };

    @Override
    public void onBackPressed() {

    }

    @Override
    public void update(String response) {
        if (rbtnLandlord.isChecked()){
            Gson gson = new Gson();
            Landlord landlord = gson.fromJson(response, Landlord.class);
            if (landlord == null){
                promptInvalidCredentials();
                return;
            }
            Intent intent = new Intent(LoginActivity.this, MainActivityLandlord.class);
            intent.putExtra("LANDLORD_DATA", landlord);
            startActivity(intent);
        }
        else if (rbtnTenant.isChecked()){
            Gson gson = new Gson();
            Tenant tenant = gson.fromJson(response, Tenant.class);
            if (tenant == null){
                promptInvalidCredentials();
                return;
            }
            Intent intent = new Intent(LoginActivity.this, MainActivityTenant.class);
            intent.putExtra("TENANT_DATA", tenant);
            startActivity(intent);
        }

    }

    private void promptInvalidCredentials() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edtPassword.setError("Invalid account credentials");
            }
        });
    }

}

