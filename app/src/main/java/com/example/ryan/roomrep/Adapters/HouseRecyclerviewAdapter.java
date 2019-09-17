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

import java.io.File;
import java.util.List;


public class HouseRecyclerviewAdapter extends RecyclerView.Adapter<HouseRecyclerviewAdapter.ViewHolder> {

    private List<House> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;

    public HouseRecyclerviewAdapter(Context context, List<House> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public HouseRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.house_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  HouseRecyclerviewAdapter.ViewHolder holder, int position) {
        House house = data.get(position);
        if (house.getUrl().isEmpty()){
           return;
        }

        Picasso.get().load(house.getUrl()).placeholder(R.drawable.house).noFade().into(holder.imgHouse);
        holder.txtAddress.setText(house.getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgHouse;
        TextView txtAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHouse = itemView.findViewById(R.id.imgHouse);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
