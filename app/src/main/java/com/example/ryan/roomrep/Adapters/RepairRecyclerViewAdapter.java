package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepairRecyclerViewAdapter extends RecyclerView.Adapter<RepairRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RepairRecyclerView";
    private List<Repair> data;
    private ItemClickListener itemClickListener;
    private LayoutInflater inflater;

    public RepairRecyclerViewAdapter(Context context, List<Repair> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RepairRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.repair_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairRecyclerViewAdapter.ViewHolder holder, int position) {
        //This allows the method to bind the values to the views.
        Repair repair = data.get(position);
        holder.txt_descriptionRow.setText(repair.getDescription());
        holder.txt_dateRow.setText(repair.getDate());
        holder.txt_problemIdRow.setText(repair.getName());
        holder.txt_status.setText(repair.getStatus());
        Picasso.get().load(repair.getPhotoRef()).placeholder(R.drawable.house).noFade().into(holder.imgViewRow);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgViewRow;
        TextView txt_descriptionRow;
        TextView txt_problemIdRow;
        TextView txt_dateRow;
        TextView txt_status;

        public ViewHolder(View itemView) {
            super(itemView);
            imgViewRow = itemView.findViewById(R.id.imgRepairRow);
            txt_dateRow = itemView.findViewById(R.id.txt_dateRow);
            txt_descriptionRow = itemView.findViewById(R.id.txt_descriptionRow);
            txt_problemIdRow = itemView.findViewById(R.id.txt_problemRow);
            txt_status = itemView.findViewById(R.id.txt_status);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
