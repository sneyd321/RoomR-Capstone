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

           






        }
    };









}
