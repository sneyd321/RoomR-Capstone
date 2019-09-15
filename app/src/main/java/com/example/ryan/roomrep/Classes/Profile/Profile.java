package com.example.ryan.roomrep.Classes.Profile;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private String firstName;
    private String lastName;
    private String email;
    private String bio;

    public Profile(String firstName, String lastName, String email, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
    }

    public Map<Integer, String> getValidator() {
        Validator validator = new ProfileValidator();
        return validator.validateProfile(this);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }
}