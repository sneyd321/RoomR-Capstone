package com.example.ryan.roomrep;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Profile;

import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText password1;
    EditText password2;
    EditText email;
    EditText bio;
    Button createProfile;

    Profile profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstName = findViewById(R.id.edtProfileFirstName);
        lastName = findViewById(R.id.edtProfileLastName);
        password1 = findViewById(R.id.edtProfilePassword1);
        password2 = findViewById(R.id.edtProfilePassword2);
        email = findViewById(R.id.edtProfileEmail);
        bio = findViewById(R.id.edtProfileBio);
        createProfile = findViewById(R.id.btnCreateProfile);
        createProfile.setOnClickListener(onCreateProfile);





    }

    View.OnClickListener onCreateProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String pw1 = password1.getText().toString();
            String pw2 = password2.getText().toString();
            String mail = email.getText().toString();
            String b = bio.getText().toString();
            if (pw1.equals(pw2)){
                profile = new Profile(fName, lName, pw1, mail, b);
                String validationResult = profile.validateProfile();
                if (profile.validateProfile().isEmpty()){
                    Task<Void> result = profile.addValues();
                    result.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(ProfileActivity.this, "Tenant Profile Created", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(ProfileActivity.this, validationResult, Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(ProfileActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }


        }
    };




}
