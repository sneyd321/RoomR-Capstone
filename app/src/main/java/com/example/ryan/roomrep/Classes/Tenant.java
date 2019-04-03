package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Tenant {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private boolean isSuccessful;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String bio;

    public Tenant(String firstName, String lastName, String password, String email, String bio){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.bio = bio;
    }


    public String validateTenant(){
        if (this.firstName.isEmpty()){
            return "Please enter a first name.";
        }
        if (this.lastName.isEmpty()){
            return "Please enter a last name";
        }
        if (this.password.isEmpty()){
            return "Please enter a password";
        }
        if (this.email.isEmpty()){
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
        tenant.put("FirstName", this.firstName);
        tenant.put("LastName", this.lastName);
        tenant.put("Password", this.password);
        tenant.put("Email", this.email);
        tenant.put("Bio", this.bio);

        final Tenant tenantRef = this;
        return db.collection("Tenant").document(this.email)
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



}
