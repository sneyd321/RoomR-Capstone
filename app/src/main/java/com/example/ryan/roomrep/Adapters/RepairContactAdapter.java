package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.RepairContact;
import com.example.ryan.roomrep.R;

import java.util.List;

public class RepairContactAdapter extends RecyclerView.Adapter<RepairContactAdapter.ViewHolder> {
    private static final String TAG = "RepairContactView";
    private List<RepairContact> data;
    private ItemClickListener itemClickListener;
    private LayoutInflater inflater;

    public RepairContactAdapter(Context context, List<RepairContact> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RepairContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.repair_company_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairContactAdapter.ViewHolder holder, int position){
        RepairContact repairContact = data.get(position);
        holder.txt_companyName.setText(repairContact.getRepairCompanyName());
        holder.txt_address.setText(repairContact.getRepairCompanyAddress());
        holder.txt_operationHours.setText(repairContact.getOperationHours());
        holder.txt_rating.setText(repairContact.getRating());
        holder.txt_phoneNumber.setText(repairContact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txt_companyName;
        TextView txt_address;
        TextView txt_operationHours;
        TextView txt_rating;
        TextView txt_phoneNumber;

        public ViewHolder(View itemView){
            super(itemView);
            txt_companyName = itemView.findViewById(R.id.txt_repairmanName);
            txt_address = itemView.findViewById(R.id.txt_repairmanAddress);
            txt_operationHours = itemView.findViewById(R.id.txt_operationHours);
            txt_rating = itemView.findViewById(R.id.txt_repairmanRating);
            txt_phoneNumber = itemView.findViewById(R.id.txt_repairmanNumber);
            itemView.setOnClickListener(this);

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
