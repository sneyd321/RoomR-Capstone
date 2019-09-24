package com.example.ryan.roomrep.Classes;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Repair {
    private String description;
    private String name;
    private String date;
    private String status;
    private String photoRef;

    private File file;

    private boolean isSuccessful;

    public Repair(String description, String name, String date, String status, String photoRef){
        this.description = description;
        this.name = name;
        this.date = date;
        this.status = status;
        this.photoRef = photoRef;

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

}
