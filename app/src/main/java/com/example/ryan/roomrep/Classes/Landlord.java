package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Landlord {


    private boolean isSuccessful;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private String firstName;
    private String lastName;
    private String password;
    private String email;


    public Landlord(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Landlord(String firstName, String lastName, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }


    public void setSuccessful(boolean value){
        isSuccessful = value;
    }

    public Task<Void> addValues(){
        final Map<String, Object> landlord = new HashMap<>();
        landlord.put("FirstName", this.getFirstName());
        landlord.put("LastName", this.getLastName());
        landlord.put("Password", this.password);
        landlord.put("Email", this.getEmail());
        final Landlord landlordRef = this;
        return db.collection("Landlord").document(this.getEmail())
                .set(landlord)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        landlordRef.setSuccessful(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        landlordRef.setSuccessful(false);
                    }
                });
    }


    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }
}
