package com.example.ryan.roomrep;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.HousesRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.TenantsRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House;
import com.example.ryan.roomrep.Classes.Landlord;
import com.example.ryan.roomrep.Classes.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddTenantFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Landlord landlord;
    House house;

    TenantsRecyclerViewAdapter recyclerViewAdapter;
    ArrayAdapter<String> spinnerAdapter;

    Spinner tenantsSpinner;
    RecyclerView tenantList;
    ArrayList<String> spinnerProfiles;
    ArrayList<String> tenantNames;


    ArrayList<Profile> profiles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_tenant, container, false);
        tenantsSpinner = view.findViewById(R.id.spnTenants);
        tenantList = view.findViewById(R.id.rcyTenants);
        tenantsSpinner.setOnItemSelectedListener(onTenantSelected);
        landlord = ((MainActivityLandlord)getActivity()).getLandlord();
        house = ((MainActivityLandlord)getActivity()).getHouse().get(((MainActivityLandlord)getActivity()).getCurrentPosition());

        tenantList.setLayoutManager(new LinearLayoutManager(getActivity()));


        spinnerProfiles = new ArrayList<>();
        spinnerProfiles.add("-Select a tenant-");
        tenantNames = new ArrayList<>();
        profiles = new ArrayList<>();

        recyclerViewAdapter = new TenantsRecyclerViewAdapter(getActivity(), tenantNames);
        tenantList.setAdapter(recyclerViewAdapter);

        Task<QuerySnapshot> result = ((MainActivityLandlord)getActivity()).getProfiles();
        result.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Profile profile = new Profile(
                                document.get("FirstName").toString(),
                                document.get("LastName").toString(),
                                document.get("Password").toString(),
                                document.get("Email").toString(),
                                document.get("Bio").toString());
                        profiles.add(profile);
                        spinnerProfiles.add(setSpinner(profile));

                    }

                    spinnerAdapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item, spinnerProfiles);
                    spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    tenantsSpinner.setAdapter(spinnerAdapter);


                    //adapter.setClickListener(this);

                    //recyclerViewAdapter.setClickListener(onItemClick);
                    recyclerViewAdapter.notifyDataSetChanged();
                }


            }
        });

        Task<QuerySnapshot> tenantResult = ((MainActivityLandlord)getActivity()).getTenants(house.getAddress());
        tenantResult.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        tenantNames.add(document.get("FirstName").toString() + " " + document.get("LastName").toString());
                    }
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });




        return view;
    }

    private String setSpinner(Profile profile){
        String name = profile.getFirstName() + " " + profile.getLastName();
        return name;

    }



    private AdapterView.OnItemSelectedListener onTenantSelected = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
            if (position != 0){

                AlertDialog.Builder dialog = buildDialog(position);
                dialog.show();
                parent.setSelection(0);

            }



        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AlertDialog.Builder buildDialog(final int pos){
        final int position = pos;
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("Are you sure you want to add this tenant?")
                .setTitle("Add Tenant")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tenantNames.add(spinnerProfiles.get(position));
                        addTenantsToFirebase(profiles.get(position - 1));


                        spinnerProfiles.remove(position);
                        recyclerViewAdapter.notifyDataSetChanged();
                        spinnerAdapter.notifyDataSetChanged();

                    }
                });

        return dialog;
    }

    private void addTenantsToFirebase(Profile profile){



        final Map<String, Object> tenant = new HashMap<>();
        tenant.put("FirstName", profile.getFirstName());
        tenant.put("LastName", profile.getLastName());
        tenant.put("Password", profile.getPassword());
        tenant.put("Email", profile.getEmail());
        db.collection("Landlord").document(landlord.getEmail()).collection("Tenants").document(profile.getEmail())
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










}
