package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.R;

import java.util.List;

public class UtilityRecyclerviewAdapter extends RecyclerView.Adapter<UtilityRecyclerviewAdapter.ViewHolder> {

    private List<Utility> data;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public UtilityRecyclerviewAdapter(Context context, List<Utility> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public UtilityRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.utility_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UtilityRecyclerviewAdapter.ViewHolder holder, int position) {
        Utility utility = data.get(position);
        holder.txtName.setText(utility.getName());
        holder.txtAmount.setText(utility.getAmount());
        holder.txtFrequency.setText(utility.getFrequency());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView txtName;
        TextView txtAmount;
        TextView txtFrequency;
        Button btnRemove;



        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtUtilityName);
            txtAmount = itemView.findViewById(R.id.txtUtilityAmount);
            txtFrequency = itemView.findViewById(R.id.txtUtilityFrequency);
            btnRemove = itemView.findViewById(R.id.btnHouseRowRemoveUtility);
            btnRemove.setOnClickListener(onRemove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.onItemClick(v, getAdapterPosition());
            }
        }

        private View.OnClickListener onRemove = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(getAdapterPosition());
                notifyDataSetChanged();
            }
        };

    }


    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }


}
