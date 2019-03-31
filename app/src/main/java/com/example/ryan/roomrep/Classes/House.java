package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class House {


    private String address;
    private byte[] image;
    private int rent;
    private int size;
    private Map checkboxes;
    private String description;
    private StorageReference storageReference;
    private int bedNumber;
    private int bathNumber;

    private boolean isSuccessful;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public House() {


    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(Map checkboxes) {
        this.checkboxes = checkboxes;
    }

    public String getDscription() {
        return description;
    }

    public void setDescription(String descripton) {
        this.description = descripton;
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }

    public void setStorageReference(StorageReference storageReference) {
        this.storageReference = storageReference;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getBathNumber() {
        return bathNumber;
    }

    public void setBathNumber(int bathNumber) {
        this.bathNumber = bathNumber;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }


    public Task<Void> addValues(){

        final Map<String, Object> house = new HashMap<>();
        house.put("Address", this.address);
        house.put("Amenities", this.getCheckboxes());
        house.put("Bath Number", this.bathNumber);
        house.put("Bed Number", this.bedNumber);
        house.put("Description", this.description);
        house.put("Rent", this.rent);
        house.put("Size", this.size);

        final House houseRef = this;
        return db.collection("House").document(this.address)
                .set(house)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        houseRef.setSuccessful(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        houseRef.setSuccessful(false);
                    }
                });

    }



}




