package com.example.ryan.roomrep.LoginActivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;

import java.util.Map;

public class TenantSignUpActivity extends AppCompatActivity {


    EditText edtFirstName;
    EditText edtLastName;
    EditText edtTenantEmail;
    EditText edtLandlordEmail;
    EditText edtPassword;
    EditText edtPassword2;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_sign_up);

        edtFirstName = findViewById(R.id.edtTSUFirstName);
        edtLastName = findViewById(R.id.edtTSULastName);
        edtLandlordEmail = findViewById(R.id.edtTSULandlordEmail);
        edtTenantEmail = findViewById(R.id.edtTSUTenantEmail);
        edtPassword = findViewById(R.id.edtTSUPassword1);
        edtPassword2 = findViewById(R.id.edtTSUPassword2);
        btnSignUp = findViewById(R.id.btnTSUSignUp);
        btnSignUp.setOnClickListener(onSignUp);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        edtFirstName.setText(sharedPref.getString("ProfileFirstName", ""));
        edtFirstName.setText(sharedPref.getString("ProfileLastName", ""));
        edtFirstName.setText(sharedPref.getString("ProfileEmail", ""));



    }

    View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String landlordEmail = edtLandlordEmail.getText().toString();
            String tenantEmail = edtTenantEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String password2 = edtPassword2.getText().toString();

            Tenant tenant = new Tenant(firstName, lastName, tenantEmail, password, password2, landlordEmail);


            Map<Integer, String> validator = tenant.getValidator();

            validator.get("FirstName");






        }
    };









}
