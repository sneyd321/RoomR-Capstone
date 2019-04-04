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
import com.example.ryan.roomrep.Classes.Tenant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AddTenantFragment extends Fragment {

    TenantsRecyclerViewAdapter recyclerViewAdapter;
    ArrayAdapter<String> spinnerAdapter;

    Spinner tenantsSpinner;
    RecyclerView tenantList;

    ArrayList<String> tenantArrayList;
    ArrayList<String> tenantNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_tenant, container, false);
        tenantsSpinner = view.findViewById(R.id.spnTenants);
        tenantList = view.findViewById(R.id.rcyTenants);
        tenantsSpinner.setOnItemSelectedListener(onTenantSelected);

        tenantList.setLayoutManager(new LinearLayoutManager(getActivity()));


        tenantArrayList = new ArrayList<>();
        tenantArrayList.add("-Select a tenant-");
        tenantNames = new ArrayList<>();

        Task<QuerySnapshot> result = ((MainActivityLandlord)getActivity()).getTenants();
        result.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Tenant tenant = new Tenant(
                                document.get("FirstName").toString(),
                                document.get("LastName").toString(),
                                document.get("Password").toString(),
                                document.get("Email").toString(),
                                document.get("Bio").toString());
                        tenantArrayList.add(tenant.getFirstName() + " " + tenant.getLastName());

                    }
                    spinnerAdapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item, tenantArrayList);
                    spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    tenantsSpinner.setAdapter(spinnerAdapter);

                    recyclerViewAdapter = new TenantsRecyclerViewAdapter(getActivity(), tenantNames);
                    //adapter.setClickListener(this);
                    tenantList.setAdapter(recyclerViewAdapter);
                    //recyclerViewAdapter.setClickListener(onItemClick);
                    recyclerViewAdapter.notifyDataSetChanged();
                }


            }
        });




        return view;
    }

    private AdapterView.OnItemSelectedListener onTenantSelected = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
            if (position != 0){
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("Are you sure you want to add this tenant?")
                        .setTitle("Add Tenant")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tenantNames.add(tenantArrayList.get(position));
                                tenantArrayList.remove(position);
                                recyclerViewAdapter.notifyDataSetChanged();
                                spinnerAdapter.notifyDataSetChanged();

                            }
                        });

                dialog.show();
                parent.setSelection(0);

            }



        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void buildDialog(){

    }




}
