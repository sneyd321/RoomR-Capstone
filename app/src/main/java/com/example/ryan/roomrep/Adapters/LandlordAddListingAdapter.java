package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;

import java.util.List;

public class LandlordAddListingAdapter extends RecyclerView.Adapter<LandlordAddListingAdapter.ViewHolder> {

    private Context context;
    private List<House> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;


    public LandlordAddListingAdapter(Context context, List<House> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public LandlordAddListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.landlord_add_listing_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        House house = data.get(position);
        //holder.txtAddress.setText(house.getLocation().getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtAddress;


        public ViewHolder(View itemView) {
            super(itemView);
            txtAddress = itemView.findViewById(R.id.txtAddListingAddress);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }

        }
    }

    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public House getHouseAtPosition(int position) {
        return data.get(position);
    }


}
