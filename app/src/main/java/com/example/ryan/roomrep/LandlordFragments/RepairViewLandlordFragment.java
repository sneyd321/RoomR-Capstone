package com.example.ryan.roomrep.LandlordFragments;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.R;
import com.squareup.picasso.Picasso;

public class RepairViewLandlordFragment extends Fragment implements FragmentEventListener {
    Repair repair;
    String status;
    int position;

    ProgressDialog progressDialog;

    LandlordRouterAction routerActionListener;

    ImageView img_view;
    TextView txt_date;
    TextView txt_repair_category;
    Spinner spn_status;
    TextView txt_description;

    Button btn_goback;
    Button btn_updateRepair;
    Button btn_contactRepair;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_landlord_repair_view, container, false);


        img_view = view.findViewById(R.id.imgRepairLandlord);
        txt_date = view.findViewById(R.id.txt_date_landlord);
        txt_description = view.findViewById(R.id.txt_descriptionLandlord);
        txt_repair_category = view.findViewById(R.id.txt_category_landlord);
        spn_status = view.findViewById(R.id.status_spinner);
        btn_contactRepair = view.findViewById(R.id.btn_callRepair);
        btn_goback = view.findViewById(R.id.btn_GoBack_landlord);
        btn_updateRepair = view.findViewById(R.id.btn_UpdateLandlord);

        btn_goback.setOnClickListener(onGoBackView);
        btn_updateRepair.setOnClickListener(onUpdateRepairView);
        btn_contactRepair.setOnClickListener(onContactRepair);

        status = "pending";

        spn_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String statusArr[] = getResources().getStringArray(R.array.status);
                status = statusArr[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        setView();

        return view;
    }

    public void setView(){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.status));
        spn_status.setAdapter(arrayAdapter);
        txt_date.setText(repair.getDate());
        txt_repair_category.setText(repair.getName());
        txt_description.setText(repair.getDescription());
        Picasso.get().load(repair.getPhotoRef()).placeholder(R.drawable.house).noFade().into(img_view);
    }

    public View.OnClickListener onGoBackView = new View.OnClickListener(){

        @Override
        public void onClick(View view) {

            routerActionListener.onNavigateToLandlordRepairsWithNoUpdate();

        }

    };
    public View.OnClickListener onUpdateRepairView = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            repair.setStatus(status);
            updateRepair();
            routerActionListener.onNavigateToLandlordRepairView(repair,position);

        }

    };
    public View.OnClickListener onContactRepair = new View.OnClickListener(){

        @Override
        public void onClick(View view) {

            //TODO

        }

    };

    public void updateRepair(){
        Network network = new Network();
        network.registerObserver(RepairViewLandlordFragment.this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Updating Repair...");
        progressDialog.show();
        network.updateRepair(repair);
    }

    public void setRepair(Repair repair, int position){
        this.repair = repair;
        this.position = position;
    }

    public void setActionListener(LandlordRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    @Override
    public void update(String response) {
        if (routerActionListener != null) {
            progressDialog.dismiss();
            routerActionListener.onNavigateToLandlordRepairsWithUpdate(repair, position);
        }
    }
}
