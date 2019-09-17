package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LandlordListingsRecyclerviewAdapter extends RecyclerView.Adapter<LandlordListingsRecyclerviewAdapter.ViewHolder> {


    LayoutInflater inflater;
    List<House> data;
    ItemClickListener itemClickListener;


    public LandlordListingsRecyclerviewAdapter(Context context, List<House> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @NonNull
    @Override
    public LandlordListingsRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.landlord_listings_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandlordListingsRecyclerviewAdapter.ViewHolder holder, int position) {
        House house = data.get(position);

        holder.txtAddress.setText(house.getAddress());
        holder.txtApplicants.setText(Integer.toString(house.getApplicants()));
        Picasso.get().load(house.getUrl()).noFade().into(holder.imgHouse);

    }




    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtAddress;
        ImageView imgHouse;
        TextView txtApplicants;

        public ViewHolder(View itemView) {
            super(itemView);

            txtAddress = itemView.findViewById(R.id.txtLandlordListingsAddress);
            imgHouse = itemView.findViewById(R.id.imgLandlordListingsHouse);
            txtApplicants = itemView.findViewById(R.id.txtLandlordListingsApplicants);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
