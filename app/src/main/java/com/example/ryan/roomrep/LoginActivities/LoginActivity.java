package com.example.ryan.roomrep.LoginActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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

    TextView txtShowError;


    ProgressDialog progressDialog;
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
        txtShowError = findViewById(R.id.txtLoginShowError);

        progressDialog = new ProgressDialog(this);

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

            final Login login = new Login(edtUserName.getText().toString(), edtPassword.getText().toString());
            Intent intent = new Intent(LoginActivity.this, MainActivityLandlord.class);
            startActivity(intent);
            /*
            Map<Integer, String> validate = login.getValidator();
            boolean isValid = true;
            for (Map.Entry<Integer, String> entry : validate.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    switch (entry.getKey()) {
                        case 0:
                            edtUserName.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 1:
                            edtPassword.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 2:
                            edtPassword.setError(entry.getValue());
                            isValid = false;
                            break;
                    }
                }
            }
            if (isValid) {
                final Network network = new Network();
                network.registerObserver(LoginActivity.this);
                progressDialog.setMessage("Logging in...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                if (rbtnLandlord.isChecked()){
                    auth.signInWithEmailAndPassword(login.getUserName(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                network.getLandlord(login);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            txtShowError.setText("Invalid Username or Password");
                            progressDialog.dismiss();
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
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            txtShowError.setText("Invalid Username or Password");
                            progressDialog.dismiss();
                        }
                    });
                }
            }

             */

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
            if (landlord.getEmail() == null){
                promptInvalidCredentials(response);
                progressDialog.dismiss();
                return;
            }
            progressDialog.dismiss();
            Intent intent = new Intent(LoginActivity.this, MainActivityLandlord.class);
            intent.putExtra("LANDLORD_DATA", landlord);
            startActivity(intent);
        }
        else if (rbtnTenant.isChecked()){

            Gson gson = new Gson();
            Tenant tenant = gson.fromJson(response, Tenant.class);
            if (tenant.getTenantEmail() == null){
                promptInvalidCredentials(response);
                progressDialog.dismiss();
                return;
            }
            progressDialog.dismiss();
            Intent intent = new Intent(LoginActivity.this, MainActivityTenant.class);
            intent.putExtra("TENANT_DATA", tenant);
            startActivity(intent);
        }

    }

    private void promptInvalidCredentials(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            final String result = jsonObject.getString("Result");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtShowError.setText(result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

}

