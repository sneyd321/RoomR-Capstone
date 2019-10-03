package com.example.ryan.roomrep.Classes.Tenant;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Tenant {


    private String firstName;
    private String lastName;
    private String password;
    private String password2;
    private String tenantEmail;
    private String landlordEmail;


    public Tenant(String firstName, String lastName, String email, String password, String password2, String landlordEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantEmail = email;
        this.password = password;
        this.password2 = password2;
        this.landlordEmail = landlordEmail;
    }


    public Map<Integer, String> getValidator() {
        Validator validator = new TenantValidator();
        return validator.validator(this);
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return tenantEmail;
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


    public String getPassword2() {
        return password2;
    }
}
