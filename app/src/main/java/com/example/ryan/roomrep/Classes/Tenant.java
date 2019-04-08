package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Tenant {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private String firstName;
    private String lastName;
    private String password;
    private String tenantEmail;
    private String landlordEmail;



    public Tenant(){

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return tenantEmail;
    }

    public void setEmail(String email) {
        this.tenantEmail = email;
    }

    public Task<QuerySnapshot> verifyLogin(String landlordEmail, String address){
        return db.collection("Landlord").document(landlordEmail).collection("Houses").document(address).collection("Tenants").get();
    }

    public Task<Void> addValues(){
        final Map<String, Object> tenant = new HashMap<>();
        tenant.put("FirstName", this.getFirstName());
        tenant.put("LastName", this.getLastName());
        tenant.put("Password", this.password);
        tenant.put("Email", this.getEmail());

        return db.collection("Landlord").document(this.landlordEmail).collection("Tenants").document(this.tenantEmail)
                .set(tenant)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }


    public String validateTenant(){
        if (this.firstName.isEmpty()){
            return "Please enter a first name.";
        }
        if (this.lastName.isEmpty()){
            return "Please enter a first name.";
        }

        if (this.tenantEmail.isEmpty()){
            return "Please enter your email address.";
        }
        if (this.landlordEmail.isEmpty()){
            return "Please enter your landlord's email address.";
        }
        if (this.password.isEmpty()){
            return "Please enter a password.";
        }
        return "";
    }


    public String getLandlordEmail() {
        return landlordEmail;
    }

    public void setLandlordEmail(String landlordEmail) {
        this.landlordEmail = landlordEmail;
    }
}
