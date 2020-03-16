package com.example.ryan.roomrep.LoginActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Map;

public class TenantSignUpActivity extends AppCompatActivity implements NetworkObserver {


    EditText edtFirstName;
    EditText edtLastName;
    EditText edtTenantEmail;
    EditText edtLandlordEmail;
    EditText edtPassword;
    EditText edtPassword2;
    Button btnSignUp;
    TextView txtGoBack;

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
        txtGoBack = findViewById(R.id.txtTSUGoBack);
        txtGoBack.setOnTouchListener(onGoBack);

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

            Tenant tenant = new Tenant(firstName, lastName, tenantEmail, password, password2, landlordEmail, "","5.0");

            Map<Integer, String> validator = tenant.getValidator();

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
                            edtLandlordEmail.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 10:
                            edtTenantEmail.setError(entry.getValue());
                            isValid = false;
                            break;

                    }
                }
            }
            if (isValid) {
                addClientRegistrationToken(tenant);
            }

        }
    };


    private void addClientRegistrationToken(final Tenant tenant) {
        final Network network = Network.getInstance();
        network.registerObserver(TenantSignUpActivity.this);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    return;
                }

                // Get new Instance ID token
                String token = task.getResult().getToken();
                Intent intent = new Intent(TenantSignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    View.OnTouchListener onGoBack = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent = new Intent(TenantSignUpActivity.this, ChooseAccountActivity.class);
            startActivity(intent);
            return false;
        }
    };


    @Override
    public void update(String response) {

    }
}
