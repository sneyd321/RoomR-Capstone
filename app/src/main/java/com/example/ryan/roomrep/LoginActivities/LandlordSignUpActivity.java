package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Landlord;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LandlordSignUpActivity extends AppCompatActivity {


    EditText firstName;
    EditText lastName;
    EditText password;
    EditText password2;
    EditText email;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landlord_sign_up);

        firstName = findViewById(R.id.edtLSUfirstName);
        lastName = findViewById(R.id.edtLSUlastName);
        password = findViewById(R.id.edtLSUpassword);
        password2 = findViewById(R.id.edtLSUpassword2);
        email = findViewById(R.id.edtLSUemail);
        signUp = findViewById(R.id.btnLSUsignup);
        signUp.setOnClickListener(onSignUp);



    }

    View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (password.getText().toString().equals(password2.getText().toString())){
                final Landlord landlord = new Landlord(firstName.getText().toString(), lastName.getText().toString(), password2.getText().toString(), email.getText().toString());
                String validate = landlord.validateLandlord();
                if (!validate.isEmpty()){
                    Toast.makeText(LandlordSignUpActivity.this, validate, Toast.LENGTH_SHORT).show();
                    return;
                }
                Task<Void> result = landlord.addValues();
                result.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //If task is successful
                        if (task.isSuccessful()) {
                            Toast.makeText(LandlordSignUpActivity.this, "Account added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LandlordSignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
        }
            else {
                Toast.makeText(LandlordSignUpActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }

        }
    };



}
