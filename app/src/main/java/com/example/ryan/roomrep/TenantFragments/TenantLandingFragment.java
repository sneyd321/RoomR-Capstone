package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.example.ryan.roomrep.R;

public class TenantLandingFragment extends Fragment {


    TextView tenantName;
    TextView houseAddress;
    RecyclerView rcyTenantName;

    Tenant tenant;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_landing_page, container, false);
        tenantName = view.findViewById(R.id.txtTenantLandingName);
        houseAddress = view.findViewById(R.id.txtTenantLandingHouseAddress);
        rcyTenantName = view.findViewById(R.id.rcyTenantLandingNames);

        tenantName.setText(tenant.getFirstName() + " " + tenant.getLastName());


        rcyTenantName.setLayoutManager(new LinearLayoutManager(getActivity()));
        //TenantNameRecyclerViewAdapter adapter = new TenantNameRecyclerViewAdapter(getActivity(), house.getTenants());
        //rcyTenantName.setAdapter(adapter);
        //adapter.notifyDataSetChanged();



        return view;
    }




    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }


}
