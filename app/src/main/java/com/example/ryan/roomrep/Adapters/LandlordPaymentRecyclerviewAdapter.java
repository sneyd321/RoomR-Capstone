package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.R;

import java.util.List;

public class LandlordPaymentRecyclerviewAdapter extends RecyclerView.Adapter<LandlordPaymentRecyclerviewAdapter.ViewHolder> {

    List<Payment> data;
    LayoutInflater inflater;


    public LandlordPaymentRecyclerviewAdapter(Context context, List<Payment> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @NonNull
    @Override
    public LandlordPaymentRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notifyr_payment_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandlordPaymentRecyclerviewAdapter.ViewHolder holder, int position) {
        Payment payment = this.data.get(position);
        holder.txtAddress.setText(payment.getHouseAddress());
        holder.txtTenantName.setText(payment.getTenantName());
        holder.txtDatePaid.setText(payment.getDueDate());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAddress;
        TextView txtTenantName;
        TextView txtDatePaid;

        public ViewHolder(View itemView) {
            super(itemView);

            txtAddress = itemView.findViewById(R.id.txtPaymentRowAddress);
            txtTenantName = itemView.findViewById(R.id.txtPaymentRowName);
            txtDatePaid = itemView.findViewById(R.id.txtPaymentRowPaymentDate);

        }
    }


}
