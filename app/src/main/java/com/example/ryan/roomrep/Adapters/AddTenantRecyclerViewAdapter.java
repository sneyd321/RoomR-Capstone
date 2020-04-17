package com.example.ryan.roomrep.Adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.example.ryan.roomrep.R;

import java.util.List;

public class AddTenantRecyclerViewAdapter extends RecyclerView.Adapter<AddTenantRecyclerViewAdapter.ViewHolder> {

    private List<Tenant> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public AddTenantRecyclerViewAdapter(Context context, List<Tenant> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.add_tenant_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tenant tenant = mData.get(position);
        holder.txtName.setText(tenant.getFullName());
        holder.txtEmail.setText(tenant.getTenantEmail());
        holder.txtHouseAddress.setText(tenant.getHouseAddress());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtEmail, txtHouseAddress;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtAddTenantName);
            txtEmail = itemView.findViewById(R.id.txtAddTenantEmail);
            txtHouseAddress = itemView.findViewById(R.id.txtAddTenantHouseAddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


}