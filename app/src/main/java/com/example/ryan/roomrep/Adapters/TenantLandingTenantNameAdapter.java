package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TenantFragments.TenantLandingFragment;

import java.util.List;
import java.util.zip.Inflater;

public class TenantLandingTenantNameAdapter extends RecyclerView.Adapter<TenantLandingTenantNameAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<Tenant> data;

    public TenantLandingTenantNameAdapter(Context context, List<Tenant> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tenant_names_landing_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tenant tenant = data.get(position);
        holder.txtTenantName.setText(tenant.getFirstName() + " " + tenant.getLastName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenantName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenantName = itemView.findViewById(R.id.txtTenantLandingRowName);
        }
    }

}
