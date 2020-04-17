package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HousesRecyclerViewAdapter extends RecyclerView.Adapter<HousesRecyclerViewAdapter.ViewHolder> {


    private List<House> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;
    private LongClickItemListener longClickItemListener;
    private ButtonClickListener buttonClickListener;

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
        holder.txtPrimaryAddress.setText(house.getLease().getRentalUnitLocation().getFormattedPrimaryAddress());
        holder.txtSecondaryAddress.setText(house.getLease().getRentalUnitLocation().getFormattedSecondaryAddress());
        holder.txtUnitName.setText(house.getLease().getRentalUnitLocation().getUnitName());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    public House getItemAtPosition(int position) {
        return this.data.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView txtPrimaryAddress;
        TextView txtSecondaryAddress;
        TextView txtUnitName;
        Button btnViewLease;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrimaryAddress = itemView.findViewById(R.id.txtHouseRowAddress);
            txtSecondaryAddress = itemView.findViewById(R.id.txtHouseRowAddressSecondary);
            txtUnitName = itemView.findViewById(R.id.txtHouseRowUnitName);
            btnViewLease = itemView.findViewById(R.id.btnViewLease);
            btnViewLease.setOnClickListener(onClickListener);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonClickListener != null){
                    buttonClickListener.onButtonClick(view, getAdapterPosition());
                }
            }
        };

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (longClickItemListener != null) {
                longClickItemListener.onLongClick(view, getAdapterPosition());
            }
            return false;
        }
    }

    public void setButtonClickListener(ButtonClickListener buttonClickListener){
        this.buttonClickListener = buttonClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void setLongClickListener(LongClickItemListener longClickListener) {
        this.longClickItemListener = longClickListener;
    }

}
