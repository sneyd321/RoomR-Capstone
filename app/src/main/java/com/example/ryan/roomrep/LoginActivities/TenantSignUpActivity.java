package com.example.ryan.roomrep.LoginActivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

public class TenantSignUpActivity extends AppCompatActivity {


    EditText firstName;
    EditText lastName;
    EditText address;
    EditText tenantEmail;
    EditText landlordEmail;
    EditText password1;
    EditText password2;
    Button signup;

    Tenant tenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_sign_up);

        firstName = findViewById(R.id.edtTSUFirstName);
        lastName = findViewById(R.id.edtTSULastName);
        tenantEmail = findViewById(R.id.edtTSUEmail);
        landlordEmail = findViewById(R.id.edtTSULandlordEmail);
        password1 = findViewById(R.id.edtTSUPassword1);
        password2 = findViewById(R.id.edtTSUPassword2);
        signup = findViewById(R.id.btnTSUSignUp);
        signup.setOnClickListener(onSignUp);

        tenant = new Tenant();





    }

    View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            tenant.setFirstName(firstName.getText().toString());
            tenant.setLastName(firstName.getText().toString());
            tenant.setEmail(tenantEmail.getText().toString());
            tenant.setLandlordEmail(landlordEmail.getText().toString());
            if (password1.getText().toString().equals(password2.getText().toString())){
                tenant.setPassword(password2.getText().toString());
            }
            else {
                Toast.makeText(TenantSignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }



            String error = tenant.validateTenant();
            if (!error.isEmpty()){
                Toast.makeText(TenantSignUpActivity.this, error, Toast.LENGTH_SHORT).show();
                return;
            }


            Task<Void> query = tenant.addValues();
            query.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(TenantSignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                }
            });





        }
    };









}
