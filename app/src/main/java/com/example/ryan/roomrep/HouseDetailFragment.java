package com.example.ryan.roomrep;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.House;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HouseDetailFragment extends Fragment {

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    Spinner bedNumber;
    Spinner bathNumber;
    EditText size;
    CheckBox washerDryer;
    CheckBox airConditioning;
    CheckBox smoking;
    CheckBox internet;
    CheckBox balcony;
    CheckBox snowRemoval;
    CheckBox parking;
    CheckBox pool;
    CheckBox pets;
    EditText description;
    Button next;

    House house;
    Map amenities;
    ArrayList<CheckBox> checkBoxes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house_detail, container, false);

        bedNumber = view.findViewById(R.id.spnBedNum);
        bathNumber = view.findViewById(R.id.spnBathNum);
        size = view.findViewById(R.id.edtSize);
        washerDryer = view.findViewById(R.id.chkWasherDryerL);
        airConditioning = view.findViewById(R.id.chkAirConditioningL);
        smoking = view.findViewById(R.id.chkSmokingL);
        internet = view.findViewById(R.id.chkInternetL);
        balcony = view.findViewById(R.id.chkBalconyL);
        snowRemoval = view.findViewById(R.id.chkSnowRemovalL);
        parking = view.findViewById(R.id.chkParkingL);
        pool = view.findViewById(R.id.chkPoolL);
        pets = view.findViewById(R.id.chkPetL);
        description = view.findViewById(R.id.edtDescriptionLHD);
        next = view.findViewById(R.id.btnNextLHD);
        next.setOnClickListener(onNext);

        bedNumber.setOnItemSelectedListener(onBedNumber);
        bathNumber.setOnItemSelectedListener(onBathNumber);

        amenities = new HashMap();
        checkBoxes = new ArrayList<>();
        checkBoxes.add(washerDryer);
        checkBoxes.add(airConditioning);
        checkBoxes.add(smoking);
        checkBoxes.add(internet);
        checkBoxes.add(balcony);
        checkBoxes.add(snowRemoval);
        checkBoxes.add(parking);
        checkBoxes.add(pool);
        checkBoxes.add(pets);

        house = ((MainActivityLandlord)getActivity()).getHouse();

        return view;

    }


    AdapterView.OnItemSelectedListener onBedNumber = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String value = parent.getItemAtPosition(position).toString();
            house.setBedNumber(Integer.parseInt(value));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            house.setBedNumber(1);
        }
    };


    AdapterView.OnItemSelectedListener onBathNumber = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String value = parent.getItemAtPosition(position).toString();
            house.setBathNumber(Integer.parseInt(value));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            house.setBathNumber(1);
        }
    };



    View.OnClickListener onNext = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (CheckBox checkBox : checkBoxes){
                amenities.put(checkBox.getText().toString(), checkBox.isChecked());
            }


            house.setCheckboxes(amenities);
            house.setSize(Integer.parseInt(size.getText().toString()));
            house.setDescription(description.getText().toString());


            insertPhotoIntoStorage();
            insertValuesIntoHouse();





        }
    };


    private void insertPhotoIntoStorage(){
        String path = "Houses/" + house.getAddress() + ".png";
        StorageReference houseRef = storage.getReference(path);

        StorageMetadata metadata = new StorageMetadata.Builder()
                .setCustomMetadata("text", "test")
                .build();

        UploadTask uploadTask = houseRef.putBytes(house.getImage(), metadata);
        uploadTask.addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageReference reference = storage.getReference();
                StorageReference pathReference = reference.child("Houses/" + house.getAddress() + ".png");
                house.setStorageReference(pathReference);

            }
        });
    }

    private void insertValuesIntoHouse(){
        Task<Void> result = house.addValues();
        result.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (house.isSuccessful()){
                    ((MainActivityLandlord)getActivity()).setViewPager(0);
                }
            }
        });
    }



}
