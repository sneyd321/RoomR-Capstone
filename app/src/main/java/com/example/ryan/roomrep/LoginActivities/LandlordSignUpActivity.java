package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Map;

public class LandlordSignUpActivity extends AppCompatActivity {


    final String LANDLORD_EMAIL_TAG = "LANDLORD_EMAIL";
    final String LANDLORD_PASSWORD_TAG = "LANDLORD_PASSWORD";

    EditText edtFirstName;
    EditText edtLastName;
    EditText edtPassword;
    EditText edtPassword2;
    EditText edtEmail;
    Button btnSignup;
    TextView txtOnGoBack;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landlord_sign_up);

        edtFirstName = findViewById(R.id.edtLSUfirstName);
        edtLastName = findViewById(R.id.edtLSUlastName);
        edtPassword = findViewById(R.id.edtLSUpassword);
        edtPassword2 = findViewById(R.id.edtLSUpassword2);
        edtEmail = findViewById(R.id.edtLSUemail);
        btnSignup = findViewById(R.id.btnLSUsignup);
        txtOnGoBack = findViewById(R.id.txtLSUback);
        txtOnGoBack.setOnTouchListener(onGoBack);
        btnSignup.setOnClickListener(onSignUp);

        auth = FirebaseAuth.getInstance();



    }

    View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String password = edtPassword.getText().toString();
            String password2 = edtPassword2.getText().toString();
            String email = edtEmail.getText().toString();
            Landlord landlord = new Landlord(firstName, lastName, password, password2, email, "");
            Map<Integer, String> validator = landlord.getLandlordValidator();
            boolean isValid = true;
            for (Map.Entry<Integer, String> entry : validator.entrySet()){
                if (!entry.getValue().isEmpty()) {
                    switch (entry.getKey()) {
                        case 0:
                            edtFirstName.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 2:
                            edtLastName.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 4:
                            edtPassword.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 5:
                            edtPassword.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 6:
                            edtPassword2.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 7:
                            edtPassword2.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 8:
                            edtEmail.setError(entry.getValue());
                            isValid = false;
                            break;

                    }
                }
            }
            if (isValid) {
                Toast.makeText(LandlordSignUpActivity.this, "Check email to verify account.", Toast.LENGTH_SHORT).show();
                Network network = new Network();
                network.signUpLandlord(landlord);
                Intent intent = new Intent(LandlordSignUpActivity.this, LoginActivity.class);
                intent.putExtra(LANDLORD_EMAIL_TAG, landlord.getEmail());
                intent.putExtra(LANDLORD_PASSWORD_TAG, landlord.getPassword());
                startActivity(intent);

            }
        }
    };


    View.OnTouchListener onGoBack = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent = new Intent(LandlordSignUpActivity.this, ChooseAccountActivity.class);
            startActivity(intent);
            return false;
        }
    };




}
