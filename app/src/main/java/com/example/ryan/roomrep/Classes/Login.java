package com.example.ryan.roomrep.Classes;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login {
    private String userName;
    private String password;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Login(String userName, String password){
        this.setUserName(userName);
        this.setPassword(password);

    }


    public Task<QuerySnapshot> GetAccountInfo(){

        return db.collection("Landlord").get();
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
