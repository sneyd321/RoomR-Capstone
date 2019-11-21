package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TenantListingsRecyclerViewAdapter extends Adapter<TenantListingsRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<House> data;
    private ItemClickListener itemClickListener;


    public TenantListingsRecyclerViewAdapter(Context context, List<House> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @NonNull
    @Override
    public TenantListingsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tenant_listing_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TenantListingsRecyclerViewAdapter.ViewHolder holder, int position) {
        House house = data.get(position);
        holder.txtAddress.setText(house.getAddress());
        holder.txtRent.setText(Integer.toString(house.getRent()));
        holder.txtDescription.setText(house.getDescription());
        if (house.getUrl().isEmpty() || house.getUrl().equals("Not Empty")){
            Picasso.get().load(R.drawable.examplehouse).noFade().into(holder.imgHouse);
            return;
        }
        Picasso.get().load(house.getUrl()).placeholder(R.drawable.examplehouse).error(R.drawable.examplehouse).noFade().into(holder.imgHouse);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtAddress;
        TextView txtDescription;
        TextView txtRent;
        ImageView imgHouse;

        public ViewHolder(View itemView) {
            super(itemView);
            txtAddress = itemView.findViewById(R.id.txtTenantListingAddress);
            txtDescription = itemView.findViewById(R.id.txtTenantListingDescription);
            txtRent = itemView.findViewById(R.id.txtTenantListingRent);
            imgHouse = itemView.findViewById(R.id.imgTenantListingHouse);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}
