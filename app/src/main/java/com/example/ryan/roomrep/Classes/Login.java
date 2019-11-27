package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.example.ryan.roomrep.Classes.Tenant.Validator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Login {
    private String userName;
    private String password;




    public Login(String userName, String password){
        this.setUserName(userName);
        this.setPassword(password);

    }

    public Map<Integer, String> getValidator() {
        Validator validator = new LoginValidator();
        return validator.validator(this);
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
