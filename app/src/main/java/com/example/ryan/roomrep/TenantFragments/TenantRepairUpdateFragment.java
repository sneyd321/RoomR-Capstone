package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;
import com.squareup.picasso.Picasso;

public class TenantRepairUpdateFragment extends Fragment implements FragmentEventListener {
    Repair repair;
    int position;
    ImageView img_view;
    TextView txt_date;
    TextView txt_repair_category;
    TextView txt_status;
    TextView txt_dateUpdated;
    EditText edt_description;
    Button btn_goback;
    Button btn_update;
    ProgressDialog progressDialog;

    TenantRouterAction routerActionListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.tenant_repair_update_fragment, container, false);

        txt_dateUpdated = view.findViewById(R.id.txt_dateUpdatedUpdate);
        txt_date = view.findViewById(R.id.txt_date);
        txt_repair_category = view.findViewById(R.id.txt_category);
        txt_status = view.findViewById(R.id.txt_status);
        img_view = view.findViewById(R.id.imgRepair);
        edt_description = view.findViewById(R.id.edt_descriptionTenant);
        btn_goback = view.findViewById(R.id.btn_GoBack);
        btn_update = view.findViewById(R.id.btn_Update);

        btn_goback.setOnClickListener(onGoBack);
        btn_update.setOnClickListener(onUpdate);
        setView();

        return view;
    }

    @Override
    public void update(String response) {
        if (routerActionListener != null) {
            progressDialog.dismiss();
            routerActionListener.onNavigateToTenantRepairListWithUpdatedRepair(repair, position);
        }
    }

    public View.OnClickListener onGoBack = new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                routerActionListener.onNavigateToTenantRepairsListWithNoRepair();

            }

    };

    public View.OnClickListener onUpdate = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            repair.setDescription(edt_description.getText().toString());
            repair.setStatus("Unseen");
            updateRepair();
        }

    };

    public void updateRepair(){
        Network network = new Network();
        network.registerObserver(TenantRepairUpdateFragment.this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Updating Repair...");
        progressDialog.show();
        network.updateRepair(repair);
    }
    public void setRepair(Repair repair, int position){
        this.repair = repair;
        this.position = position;
    }

    public void setActionListener(TenantRouterAction routerActionListener){
        this.routerActionListener = routerActionListener;
    }

    public void setView(){
        txt_date.setText(repair.getDate());
        txt_status.setText(repair.getStatus());
        txt_repair_category.setText(repair.getName());
        txt_dateUpdated.setText(repair.getDateUpdated());
        edt_description.setText(repair.getDescription());
        Picasso.get().load(repair.getPhotoRef()).placeholder(R.drawable.house).noFade().into(img_view);
    }
}
