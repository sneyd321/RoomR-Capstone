package com.example.ryan.roomrep.Classes;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Repair {
    private String description;
    private String problemIdentification;
    private String dateReported;
    private byte[] image;
    private String status;
    private String storageReference;

    private boolean isSuccessful;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Repair(){

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

    public String getProblemIdentification() {
        return problemIdentification;
    }

    public void setProblemIdentification(String problemIdentification) {
        this.problemIdentification = problemIdentification;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getStorageReference() {
        return storageReference;
    }

    public void setStorageReference(String storageReference) {
        this.storageReference = storageReference;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Task<Void> addValues(){
        final Map<String, Object> repair = new HashMap<>();
        repair.put("Problem", this.problemIdentification);
        repair.put("Description", this.description);
        repair.put("Date", this.dateReported);
        repair.put("StorageReference", this.storageReference);

        final Repair repairRef = this;
        return db.collection("Repair").document(this.dateReported)
                .set(repair)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        repairRef.setSuccessful(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        repairRef.setSuccessful(false);
                    }
                });
    }

    public Task<QuerySnapshot> getRepairsFirebase(){
        return db.collection("Repair").get();
    }
}
