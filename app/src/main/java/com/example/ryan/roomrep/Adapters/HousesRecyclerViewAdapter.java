package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Lease.House;
import com.example.ryan.roomrep.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HousesRecyclerViewAdapter extends RecyclerView.Adapter<HousesRecyclerViewAdapter.ViewHolder> {


    private List<House> data;
    private LayoutInflater inflater;

    public HousesRecyclerViewAdapter(Context context, List<House> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.house_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        House house = data.get(position);
        holder.txtPrimaryAddress.setText(house.getRentalUnitLocation().getFormattedPrimaryAddress());
        holder.txtSecondaryAddress.setText(house.getRentalUnitLocation().getFormattedSecondaryAddress());
        holder.txtUnitName.setText(house.getRentalUnitLocation().getUnitName());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPrimaryAddress;
        TextView txtSecondaryAddress;
        TextView txtUnitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrimaryAddress = itemView.findViewById(R.id.txtHouseRowAddress);
            txtSecondaryAddress = itemView.findViewById(R.id.txtHouseRowAddressSecondary);
            txtUnitName = itemView.findViewById(R.id.txtHouseRowUnitName);
        }
    }
}
