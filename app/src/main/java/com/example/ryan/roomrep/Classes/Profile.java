package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private boolean isSuccessful;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String bio;

    public Profile(String firstName, String lastName, String password, String email, String bio){
        this.firstName = firstName;
        this.lastName = lastName;
        this.setPassword(password);
        this.setEmail(email);
        this.bio = bio;
    }


    public String validateProfile(){
        if (this.getFirstName().isEmpty()){
            return "Please enter a first name.";
        }
        if (this.getLastName().isEmpty()){
            return "Please enter a last name";
        }
        if (this.getPassword().isEmpty()){
            return "Please enter a password";
        }
        if (this.getEmail().isEmpty()){
            return "please enter a email";
        }
        if (this.bio.isEmpty()){
            return "Please enter a bio";
        }
        return "";
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }


    public Task<Void> addValues(){
        final Map<String, Object> tenant = new HashMap<>();
        tenant.put("FirstName", this.getFirstName());
        tenant.put("LastName", this.getLastName());
        tenant.put("Password", this.getPassword());
        tenant.put("Email", this.getEmail());
        tenant.put("Bio", this.bio);

        final Profile tenantRef = this;
        return db.collection("Profile").document(this.getEmail())
                .set(tenant)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        tenantRef.setSuccessful(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tenantRef.setSuccessful(false);
                    }
                });
    }


    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
