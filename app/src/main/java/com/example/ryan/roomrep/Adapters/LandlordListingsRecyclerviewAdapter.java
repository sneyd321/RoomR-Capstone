package com.example.ryan.roomrep.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;

import java.util.List;

public class LandlordListingsRecyclerviewAdapter extends RecyclerView.Adapter<LandlordListingsRecyclerviewAdapter.ViewHolder> {


    LayoutInflater inflater;
    List<House> data;
    LongClickItemListener longClickItemListener;
    ItemClickListener itemClickListener;
    Context context;


    public LandlordListingsRecyclerviewAdapter(Context context, List<House> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
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

        //holder.txtAddress.setText(house.getLocation().getAddress());
        //holder.txtApplicants.setText(Integer.toString(house.getProfiles().size()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView txtAddress;
        ImageView imgHouse;
        TextView txtApplicants;

        public ViewHolder(View itemView) {
            super(itemView);

            txtAddress = itemView.findViewById(R.id.txtLandlordListingsAddress);
            imgHouse = itemView.findViewById(R.id.imgLandlordListingsHouse);
            txtApplicants = itemView.findViewById(R.id.txtLandlordListingsApplicants);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (longClickItemListener != null){
                longClickItemListener.onLongClick(v, getAdapterPosition());
            }
            return false;
        }
    }

    public void addHouse(House house, int position) {
        this.data.add(house);
        notifyItemInserted(position);
    }

    public void removeHouse(House house) {
        this.data.remove(house);
        notifyDataSetChanged();
    }

    public void setLongClickItemListener(LongClickItemListener longClickItemListener) {
        this.longClickItemListener = longClickItemListener;
    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


}
