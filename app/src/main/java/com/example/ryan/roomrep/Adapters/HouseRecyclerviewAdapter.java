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
import com.squareup.picasso.Picasso;

import java.util.List;


public class HouseRecyclerviewAdapter extends RecyclerView.Adapter<HouseRecyclerviewAdapter.ViewHolder> {

    private List<House> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;
    private LongClickItemListener longClickItemListener;

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
        Picasso.get().load(R.drawable.examplehouse).into(holder.imgHouse);
        holder.txtAddress.setText(house.getLocation().getAddress());
        holder.txtSecondaryAddress.setText(house.getLocation().getFormattedSecondaryLocation());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ImageView imgHouse;
        TextView txtAddress;
        TextView txtSecondaryAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHouse = itemView.findViewById(R.id.imgHouse);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtSecondaryAddress = itemView.findViewById(R.id.txtAddressSecondary);
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

    public House getItemAtPostion(int position){
        return this.data.get(position);
    }


    public void setLongClickItemListener(LongClickItemListener longClickItemListener) {
        this.longClickItemListener = longClickItemListener;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
