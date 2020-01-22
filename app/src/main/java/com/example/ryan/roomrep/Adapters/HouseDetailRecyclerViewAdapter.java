package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.HouseDetail;
import com.example.ryan.roomrep.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HouseDetailRecyclerViewAdapter extends RecyclerView.Adapter<HouseDetailRecyclerViewAdapter.ViewHolder>{

    private List<HouseDetail> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;


    public HouseDetailRecyclerViewAdapter(Context context, List<HouseDetail> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.house_detail_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HouseDetail houseDetail = this.data.get(position);
        holder.txtAddress.setText(houseDetail.getTitle());
        if (houseDetail.getImgDrawable() != null) {
            holder.imgHouse.setImageDrawable(houseDetail.getImgDrawable());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgHouse;
        TextView txtAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHouse = itemView.findViewById(R.id.imgImage);
            txtAddress = itemView.findViewById(R.id.txtLabel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    public void setOnCLickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}
