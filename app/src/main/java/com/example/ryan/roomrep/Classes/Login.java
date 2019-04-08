package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Login {
    private String userName;
    private String password;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Login(String userName, String password){
        this.setUserName(userName);
        this.setPassword(password);

    }


    public Task<QuerySnapshot> GetLandlordAccountInfo(){

        return db.collection("Landlord").get();
    }

    public Task<QuerySnapshot> GetTenantAccountInfo(String landlord){

        return db.collection("Landlord").document(landlord).collection("Tenants").get();
    }




    public void verifyAccountType(){

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
