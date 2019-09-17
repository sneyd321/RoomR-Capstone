package com.example.ryan.roomrep;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.Repair;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class RepairHistoryLandlordFragment extends Fragment {
    RepairRecyclerViewAdapter adapter;
    RecyclerView repairList;
    TextView numberofRepairs;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_history_landlord, container, false);

        numberofRepairs = view.findViewById(R.id.txtNumberOfRepairs);
        repairList = view.findViewById(R.id.recyclerRepairsView);

        repairList.setLayoutManager(new LinearLayoutManager(getActivity()));

        setUpRepairs();

        return view;
    }

    public void setUpRepairs(){
        Task<QuerySnapshot> result =  db.collection("Repair").get();
        //Request all the documents in the data collection "repair"
        result.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //populates repair from the firebase storage.
                        Repair repair = new Repair();
                        repair.setDateReported(document.get("Date").toString());
                        repair.setDescription(document.get("Description").toString());
                        repair.setProblemIdentification(document.get("Problem").toString());
                        repair.setStorageReference(document.get("StorageReference").toString());
                        //((MainActivityLandlord)getActivity()).getRepair().add(repair);
                    }

                    //adapter = new RepairRecyclerViewAdapter(getActivity(), ((MainActivityLandlord)getActivity()).getRepair());
                    //adapter.setClickListener(this);
                    repairList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //if (!((MainActivityLandlord)getActivity()).getRepair().isEmpty()){
                        //numberofRepairs.setText("Total Repairs: " +  ((MainActivityLandlord)getActivity()).getRepair().size());
                    //}

                }
            }
        });
    }

}
